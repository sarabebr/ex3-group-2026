package shieldcare.control;

import shieldcare.database.DBConnection;
import shieldcare.entity.PolicyRenewal;
import shieldcare.entity.PolicyRenewalReport;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PolicyRenewalReportController {

    private final PolicyRenewalValidationController validationController;
    private final RenewalAmountController amountController;

    public PolicyRenewalReportController() {
        this.validationController =
                new PolicyRenewalValidationController();

        this.amountController =
                new RenewalAmountController();
    }

    public List<PolicyRenewal> loadPoliciesForRenewal(
            int agentId,
            LocalDate expirationFrom,
            LocalDate expirationTo) throws SQLException {

        validateSearchDetails(
                agentId,
                expirationFrom,
                expirationTo
        );

        List<PolicyRenewal> renewals = new ArrayList<>();

        String sql =
                "SELECT "
                        + "[policy_number], "
                        + "[version_number], "
                        + "[insurance_type], "
                        + "[start_date], "
                        + "[expiration_date], "
                        + "[amount], "
                        + "[payment_frequency], "
                        + "[coverage_details], "
                        + "[status], "
                        + "[client_id], "
                        + "[assigned_agent_id], "
                        + "[insurer_id] "
                        + "FROM [Policy] "
                        + "WHERE [assigned_agent_id] = ? "
                        + "AND [expiration_date] BETWEEN ? AND ? "
                        + "ORDER BY [expiration_date]";

        try (Connection connection =
                     DBConnection.getConnection();

             PreparedStatement statement =
                     connection.prepareStatement(sql)) {

            statement.setInt(1, agentId);
            statement.setDate(
                    2,
                    Date.valueOf(expirationFrom)
            );
            statement.setDate(
                    3,
                    Date.valueOf(expirationTo)
            );

            try (ResultSet resultSet =
                         statement.executeQuery()) {

                int generatedId = 1;

                while (resultSet.next()) {

                    Date databaseStartDate =
                            resultSet.getDate("start_date");

                    Date databaseExpirationDate =
                            resultSet.getDate("expiration_date");

                    LocalDate startDate =
                            databaseStartDate == null
                                    ? null
                                    : databaseStartDate.toLocalDate();

                    LocalDate expirationDate =
                            databaseExpirationDate == null
                                    ? null
                                    : databaseExpirationDate.toLocalDate();

                    int insurerId =
                            resultSet.getInt("insurer_id");

                    PolicyRenewal renewal =
                            new PolicyRenewal(
                                    generatedId,
                                    generatedId,
                                    resultSet.getString(
                                            "policy_number"
                                    ),
                                    resultSet.getInt(
                                            "version_number"
                                    ),
                                    resultSet.getInt(
                                            "client_id"
                                    ),
                                    resultSet.getInt(
                                            "assigned_agent_id"
                                    ),
                                    resultSet.getString(
                                            "insurance_type"
                                    ),
                                    insurerId,
                                    resultSet.getString(
                                            "payment_frequency"
                                    ),
                                    startDate,
                                    expirationDate,
                                    resultSet.getDouble(
                                            "amount"
                                    ),
                                    resultSet.getString(
                                            "coverage_details"
                                    )
                            );

                    String policyStatus =
                            resultSet.getString("status");

                    if (policyStatus != null
                            && !policyStatus.isBlank()) {

                        renewal.setRenewalStatus(
                                policyStatus
                        );
                    }

                    renewal.setInsurerSupported(
                            insurerId > 0
                    );

                    renewal.setCoveredItemChanged(false);

                    renewals.add(renewal);
                    generatedId++;
                }
            }
        }

        return renewals;
    }

    public PolicyRenewalReport createReport(
            int reportId,
            int agentId,
            LocalDate expirationFrom,
            LocalDate expirationTo,
            List<PolicyRenewal> renewals) {

        validateSearchDetails(
                agentId,
                expirationFrom,
                expirationTo
        );

        PolicyRenewalReport report =
                new PolicyRenewalReport(
                        reportId,
                        agentId,
                        expirationFrom,
                        expirationTo
                );

        if (renewals != null) {
            for (PolicyRenewal renewal : renewals) {
                report.addRenewal(renewal);
            }
        }

        if (report.hasRenewals()) {
            report.setReportStatus("Ready");
        } else {
            report.setReportStatus("Empty");
        }

        return report;
    }

    public boolean startRenewal(
            PolicyRenewal renewal) {

        if (renewal == null) {
            System.out.println(
                    "No policy was selected for renewal."
            );

            return false;
        }

        renewal.setRenewalStatus("In Progress");

        if (renewal.getNewStartDate() == null
                || renewal.getNewExpirationDate() == null) {

            renewal.applyDefaultRenewalPeriod();
        }

        boolean valid =
                validationController.validateRenewal(
                        renewal
                );

        if (!valid) {
            return false;
        }

        amountController.recalculateRenewalAmount(
                renewal
        );

        renewal.setRenewalStatus(
                "Ready for Renewal"
        );

        renewal.setRemarks(
                "The renewal was validated and "
                        + "the amount was recalculated."
        );

        return true;
    }

    public void applyDefaultPeriod(
            PolicyRenewal renewal) {

        if (renewal == null) {
            throw new IllegalArgumentException(
                    "Renewal data cannot be null."
            );
        }

        renewal.applyDefaultRenewalPeriod();

        System.out.println(
                "Default renewal period was applied."
        );
    }

    public boolean checkInsurerSupport(
            PolicyRenewal renewal) {

        return validationController
                .validateInsurerSupport(renewal);
    }

    public double recalculateAmount(
            PolicyRenewal renewal) {

        return amountController
                .recalculateRenewalAmount(renewal);
    }

    public String createRenewalSummary(
            PolicyRenewal renewal) {

        if (renewal == null) {
            return "No renewal was selected.";
        }

        return "Policy number: "
                + renewal.getPolicyNumber()
                + System.lineSeparator()
                + "Policy version: "
                + renewal.getRenewalVersion()
                + System.lineSeparator()
                + "Client ID: "
                + renewal.getClientId()
                + System.lineSeparator()
                + "Agent ID: "
                + renewal.getAgentId()
                + System.lineSeparator()
                + "Insurance type: "
                + renewal.getInsuranceType()
                + System.lineSeparator()
                + "Insurer ID: "
                + renewal.getInsurerId()
                + System.lineSeparator()
                + "Old expiration date: "
                + renewal.getOldExpirationDate()
                + System.lineSeparator()
                + "New start date: "
                + renewal.getNewStartDate()
                + System.lineSeparator()
                + "New expiration date: "
                + renewal.getNewExpirationDate()
                + System.lineSeparator()
                + "Old amount: "
                + renewal.getOldAmount()
                + System.lineSeparator()
                + "New amount: "
                + renewal.getNewAmount()
                + System.lineSeparator()
                + "Renewal status: "
                + renewal.getRenewalStatus();
    }

    private void validateSearchDetails(
            int agentId,
            LocalDate expirationFrom,
            LocalDate expirationTo) {

        if (agentId <= 0) {
            throw new IllegalArgumentException(
                    "Agent ID must be greater than zero."
            );
        }

        if (expirationFrom == null
                || expirationTo == null) {

            throw new IllegalArgumentException(
                    "Expiration dates are required."
            );
        }

        if (expirationTo.isBefore(expirationFrom)) {
            throw new IllegalArgumentException(
                    "The end date cannot be before "
                            + "the start date."
            );
        }
    }
}