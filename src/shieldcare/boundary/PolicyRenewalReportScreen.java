package shieldcare.boundary;

import net.sf.jasperreports.engine.JRException;
import shieldcare.control.JasperReportGenerator;
import shieldcare.control.PolicyRenewalReportController;
import shieldcare.entity.PolicyRenewal;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class PolicyRenewalReportScreen extends JFrame {

    private final PolicyRenewalReportController controller;
    private final JasperReportGenerator jasperReportGenerator;
    private final List<PolicyRenewal> displayedRenewals;

    private JTextField agentIdField;
    private JTextField expirationFromField;
    private JTextField expirationToField;

    private JTable policyTable;
    private DefaultTableModel tableModel;

    private JTextField policyNumberField;
    private JTextField clientIdField;
    private JTextField insuranceTypeField;
    private JTextField insurerIdField;
    private JTextField oldExpirationDateField;
    private JTextField newStartDateField;
    private JTextField newExpirationDateField;
    private JTextField oldAmountField;
    private JTextField newAmountField;
    private JTextField renewalStatusField;

    private JCheckBox insurerSupportedCheckBox;
    private JCheckBox coveredItemChangedCheckBox;

    public PolicyRenewalReportScreen() {
        controller = new PolicyRenewalReportController();
        jasperReportGenerator = new JasperReportGenerator();
        displayedRenewals = new ArrayList<>();

        initializeFrame();
        initializeComponents();
        refreshTable(displayedRenewals);
    }

    private void initializeFrame() {
        setTitle("ShieldCare - Policy Renewal Report");
        setSize(1200, 760);
        setMinimumSize(new Dimension(1000, 700));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
    }

    private void initializeComponents() {
        JPanel mainPanel = new JPanel(
                new BorderLayout(10, 10)
        );

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        8,
                        8,
                        8,
                        8
                )
        );

        mainPanel.add(
                createSearchPanel(),
                BorderLayout.NORTH
        );

        mainPanel.add(
                createTablePanel(),
                BorderLayout.CENTER
        );

        mainPanel.add(
                createRenewalDetailsPanel(),
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    private JPanel createSearchPanel() {
        JPanel panel = new JPanel(
                new GridBagLayout()
        );

        panel.setBorder(
                BorderFactory.createTitledBorder(
                        "Policy Renewal Search"
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets = new Insets(6, 8, 6, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        panel.add(
                new JLabel("Agent ID:"),
                gbc
        );

        gbc.gridx = 1;
        gbc.weightx = 0.15;

        agentIdField = new JTextField("1", 10);
        panel.add(agentIdField, gbc);

        gbc.gridx = 2;
        gbc.weightx = 0;

        panel.add(
                new JLabel("Expiration From:"),
                gbc
        );

        gbc.gridx = 3;
        gbc.weightx = 0.2;

        expirationFromField = new JTextField(
                LocalDate.now().toString(),
                12
        );

        panel.add(expirationFromField, gbc);

        gbc.gridx = 4;
        gbc.weightx = 0;

        panel.add(
                new JLabel("Expiration To:"),
                gbc
        );

        gbc.gridx = 5;
        gbc.weightx = 0.2;

        expirationToField = new JTextField(
                LocalDate.now()
                        .plusMonths(3)
                        .toString(),
                12
        );

        panel.add(expirationToField, gbc);

        gbc.gridx = 6;
        gbc.weightx = 0;

        JButton searchButton =
                new JButton("Search");

        searchButton.addActionListener(
                event -> searchPolicies()
        );

        panel.add(searchButton, gbc);

        return panel;
    }

    private JScrollPane createTablePanel() {
        String[] columns = {
                "Policy ID",
                "Policy Number",
                "Client ID",
                "Agent ID",
                "Insurance Type",
                "Insurer ID",
                "Expiration Date",
                "Old Amount",
                "Status"
        };

        tableModel =
                new DefaultTableModel(columns, 0) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };

        policyTable = new JTable(tableModel);

        policyTable.setSelectionMode(
                ListSelectionModel.SINGLE_SELECTION
        );

        policyTable.setRowHeight(24);

        policyTable.getTableHeader()
                .setReorderingAllowed(false);

        policyTable.getSelectionModel()
                .addListSelectionListener(event -> {

                    if (!event.getValueIsAdjusting()) {
                        displaySelectedRenewal();
                    }
                });

        JScrollPane scrollPane =
                new JScrollPane(policyTable);

        scrollPane.setBorder(
                BorderFactory.createTitledBorder(
                        "Policies Eligible for Renewal"
                )
        );

        scrollPane.setPreferredSize(
                new Dimension(1150, 360)
        );

        return scrollPane;
    }

    private JPanel createRenewalDetailsPanel() {
        JPanel mainPanel =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        mainPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Renewal Details"
                )
        );

        mainPanel.setPreferredSize(
                new Dimension(1150, 250)
        );

        JPanel fieldsPanel =
                new JPanel(
                        new GridLayout(
                                4,
                                6,
                                10,
                                8
                        )
                );

        policyNumberField =
                createReadOnlyTextField();

        clientIdField =
                createReadOnlyTextField();

        insuranceTypeField =
                createReadOnlyTextField();

        insurerIdField =
                createReadOnlyTextField();

        oldExpirationDateField =
                createReadOnlyTextField();

        newStartDateField =
                createReadOnlyTextField();

        newExpirationDateField =
                createReadOnlyTextField();

        oldAmountField =
                createReadOnlyTextField();

        newAmountField =
                createReadOnlyTextField();

        renewalStatusField =
                createReadOnlyTextField();

        fieldsPanel.add(
                new JLabel("Policy Number:")
        );
        fieldsPanel.add(policyNumberField);

        fieldsPanel.add(
                new JLabel("Client ID:")
        );
        fieldsPanel.add(clientIdField);

        fieldsPanel.add(
                new JLabel("Insurance Type:")
        );
        fieldsPanel.add(insuranceTypeField);

        fieldsPanel.add(
                new JLabel("Insurer ID:")
        );
        fieldsPanel.add(insurerIdField);

        fieldsPanel.add(
                new JLabel("Old Expiration Date:")
        );
        fieldsPanel.add(oldExpirationDateField);

        fieldsPanel.add(
                new JLabel("New Start Date:")
        );
        fieldsPanel.add(newStartDateField);

        fieldsPanel.add(
                new JLabel("New Expiration Date:")
        );
        fieldsPanel.add(newExpirationDateField);

        fieldsPanel.add(
                new JLabel("Old Amount:")
        );
        fieldsPanel.add(oldAmountField);

        fieldsPanel.add(
                new JLabel("New Amount:")
        );
        fieldsPanel.add(newAmountField);

        fieldsPanel.add(
                new JLabel("Renewal Status:")
        );
        fieldsPanel.add(renewalStatusField);

        insurerSupportedCheckBox =
                new JCheckBox(
                        "Insurer Supported"
                );

        coveredItemChangedCheckBox =
                new JCheckBox(
                        "Covered Item Changed"
                );

        fieldsPanel.add(
                insurerSupportedCheckBox
        );

        fieldsPanel.add(
                coveredItemChangedCheckBox
        );

        JPanel buttonsPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                8
                        )
                );

        JButton startRenewalButton =
                new JButton(
                        "Start Selected Renewal"
                );

        startRenewalButton.addActionListener(
                event -> startSelectedRenewal()
        );

        JButton defaultPeriodButton =
                new JButton(
                        "Use Default Period"
                );

        defaultPeriodButton.addActionListener(
                event -> applyDefaultPeriod()
        );

        JButton insurerSupportButton =
                new JButton(
                        "Check Insurer Support"
                );

        insurerSupportButton.addActionListener(
                event -> checkInsurerSupport()
        );

        JButton recalculateButton =
                new JButton(
                        "Recalculate Amount"
                );

        recalculateButton.addActionListener(
                event -> recalculateAmount()
        );

        JButton summaryButton =
                new JButton(
                        "Open Summary"
                );

        summaryButton.addActionListener(
                event -> openSummary()
        );

        buttonsPanel.add(startRenewalButton);
        buttonsPanel.add(defaultPeriodButton);
        buttonsPanel.add(insurerSupportButton);
        buttonsPanel.add(recalculateButton);
        buttonsPanel.add(summaryButton);

        mainPanel.add(
                fieldsPanel,
                BorderLayout.CENTER
        );

        mainPanel.add(
                buttonsPanel,
                BorderLayout.SOUTH
        );

        return mainPanel;
    }

    private JTextField createReadOnlyTextField() {
        JTextField textField =
                new JTextField();

        textField.setEditable(false);
        textField.setBackground(Color.WHITE);

        return textField;
    }

    private void searchPolicies() {
        try {
            int agentId =
                    Integer.parseInt(
                            agentIdField
                                    .getText()
                                    .trim()
                    );

            LocalDate expirationFrom =
                    LocalDate.parse(
                            expirationFromField
                                    .getText()
                                    .trim()
                    );

            LocalDate expirationTo =
                    LocalDate.parse(
                            expirationToField
                                    .getText()
                                    .trim()
                    );

            List<PolicyRenewal> renewals =
                    controller.loadPoliciesForRenewal(
                            agentId,
                            expirationFrom,
                            expirationTo
                    );

            displayedRenewals.clear();
            displayedRenewals.addAll(renewals);

            refreshTable(displayedRenewals);
            clearRenewalDetails();

            JOptionPane.showMessageDialog(
                    this,
                    displayedRenewals.size()
                            + " policies were found.",
                    "Search Results",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (NumberFormatException exception) {
            showError(
                    "Agent ID must be a valid number."
            );

        } catch (DateTimeParseException exception) {
            showError(
                    "Dates must be entered "
                            + "in YYYY-MM-DD format."
            );

        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());

        } catch (SQLException exception) {
            showError(
                    "The policies could not be loaded "
                            + "from the database.\n"
                            + exception.getMessage()
            );
        }
    }

    private void refreshTable(
            List<PolicyRenewal> renewals) {

        tableModel.setRowCount(0);

        for (PolicyRenewal renewal : renewals) {

            tableModel.addRow(
                    new Object[]{
                            renewal.getPolicyId(),
                            renewal.getPolicyNumber(),
                            renewal.getClientId(),
                            renewal.getAgentId(),
                            renewal.getInsuranceType(),
                            renewal.getInsurerId(),
                            renewal.getOldExpirationDate(),
                            renewal.getOldAmount(),
                            renewal.getRenewalStatus()
                    }
            );
        }
    }

    private PolicyRenewal getSelectedRenewal() {
        int selectedRow =
                policyTable.getSelectedRow();

        if (selectedRow < 0) {
            showError(
                    "Please select a policy "
                            + "from the table."
            );

            return null;
        }

        String selectedPolicyNumber =
                tableModel.getValueAt(
                        selectedRow,
                        1
                ).toString();

        for (PolicyRenewal renewal :
                displayedRenewals) {

            if (renewal.getPolicyNumber()
                    .equals(selectedPolicyNumber)) {

                return renewal;
            }
        }

        showError(
                "The selected policy "
                        + "could not be found."
        );

        return null;
    }

    private void displaySelectedRenewal() {
        int selectedRow =
                policyTable.getSelectedRow();

        if (selectedRow < 0) {
            return;
        }

        String selectedPolicyNumber =
                tableModel.getValueAt(
                        selectedRow,
                        1
                ).toString();

        for (PolicyRenewal renewal :
                displayedRenewals) {

            if (renewal.getPolicyNumber()
                    .equals(selectedPolicyNumber)) {

                insurerSupportedCheckBox
                        .setSelected(
                                renewal
                                        .isInsurerSupported()
                        );

                coveredItemChangedCheckBox
                        .setSelected(
                                renewal
                                        .isCoveredItemChanged()
                        );

                updateRenewalDetails(renewal);
                return;
            }
        }
    }

    private void updateRenewalDetails(
            PolicyRenewal renewal) {

        policyNumberField.setText(
                renewal.getPolicyNumber()
        );

        clientIdField.setText(
                String.valueOf(
                        renewal.getClientId()
                )
        );

        insuranceTypeField.setText(
                renewal.getInsuranceType()
        );

        insurerIdField.setText(
                String.valueOf(
                        renewal.getInsurerId()
                )
        );

        oldExpirationDateField.setText(
                formatValue(
                        renewal.getOldExpirationDate()
                )
        );

        newStartDateField.setText(
                formatValue(
                        renewal.getNewStartDate()
                )
        );

        newExpirationDateField.setText(
                formatValue(
                        renewal.getNewExpirationDate()
                )
        );

        oldAmountField.setText(
                String.valueOf(
                        renewal.getOldAmount()
                )
        );

        newAmountField.setText(
                String.valueOf(
                        renewal.getNewAmount()
                )
        );

        renewalStatusField.setText(
                renewal.getRenewalStatus()
        );
    }

    private String formatValue(Object value) {
        return value == null
                ? ""
                : value.toString();
    }

    private void clearRenewalDetails() {
        policyNumberField.setText("");
        clientIdField.setText("");
        insuranceTypeField.setText("");
        insurerIdField.setText("");
        oldExpirationDateField.setText("");
        newStartDateField.setText("");
        newExpirationDateField.setText("");
        oldAmountField.setText("");
        newAmountField.setText("");
        renewalStatusField.setText("");

        insurerSupportedCheckBox
                .setSelected(false);

        coveredItemChangedCheckBox
                .setSelected(false);
    }

    private void applyDefaultPeriod() {
        PolicyRenewal renewal =
                getSelectedRenewal();

        if (renewal == null) {
            return;
        }

        try {
            controller.applyDefaultPeriod(
                    renewal
            );

            updateRenewalDetails(renewal);

            JOptionPane.showMessageDialog(
                    this,
                    "Default renewal period "
                            + "was applied.",
                    "Renewal Period",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException
                 | IllegalStateException exception) {

            showError(exception.getMessage());
        }
    }

    private void checkInsurerSupport() {
        PolicyRenewal renewal =
                getSelectedRenewal();

        if (renewal == null) {
            return;
        }

        renewal.setInsurerSupported(
                insurerSupportedCheckBox
                        .isSelected()
        );

        boolean supported =
                controller.checkInsurerSupport(
                        renewal
                );

        updateRenewalDetails(renewal);
        refreshTable(displayedRenewals);

        JOptionPane.showMessageDialog(
                this,
                supported
                        ? "The insurer supports "
                        + "the renewal."
                        : "The insurer does not "
                        + "support the renewal.",
                "Insurer Support",
                supported
                        ? JOptionPane.INFORMATION_MESSAGE
                        : JOptionPane.WARNING_MESSAGE
        );
    }

    private void recalculateAmount() {
        PolicyRenewal renewal =
                getSelectedRenewal();

        if (renewal == null) {
            return;
        }

        renewal.setCoveredItemChanged(
                coveredItemChangedCheckBox
                        .isSelected()
        );

        try {
            double amount =
                    controller.recalculateAmount(
                            renewal
                    );

            updateRenewalDetails(renewal);

            JOptionPane.showMessageDialog(
                    this,
                    "The new renewal amount is: "
                            + amount,
                    "Renewal Amount",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IllegalArgumentException exception) {
            showError(exception.getMessage());
        }
    }

    private void startSelectedRenewal() {
        PolicyRenewal renewal =
                getSelectedRenewal();

        if (renewal == null) {
            return;
        }

        renewal.setInsurerSupported(
                insurerSupportedCheckBox
                        .isSelected()
        );

        renewal.setCoveredItemChanged(
                coveredItemChangedCheckBox
                        .isSelected()
        );

        try {
            boolean started =
                    controller.startRenewal(
                            renewal
                    );

            updateRenewalDetails(renewal);
            refreshTable(displayedRenewals);

            JOptionPane.showMessageDialog(
                    this,
                    started
                            ? "The selected policy "
                            + "is ready for renewal."
                            : "The renewal could "
                            + "not be completed.",
                    "Policy Renewal",
                    started
                            ? JOptionPane.INFORMATION_MESSAGE
                            : JOptionPane.WARNING_MESSAGE
            );

        } catch (IllegalArgumentException
                 | IllegalStateException exception) {

            showError(exception.getMessage());
        }
    }

    private void openSummary() {

        PolicyRenewal renewal =
                getSelectedRenewal();

        if (renewal == null) {
            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Opening Jasper report...",
                "Policy Renewal Report",
                JOptionPane.INFORMATION_MESSAGE
        );

        try {
            jasperReportGenerator
                    .displayPolicyRenewalReport(
                            renewal
                    );

        } catch (Throwable exception) {

            exception.printStackTrace();

            showError(
                    "The Jasper report could not be generated.\n\n"
                            + "Error type: "
                            + exception.getClass().getName()
                            + "\n\nMessage: "
                            + exception.getMessage()
            );
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }

    public void displayPolicyRenewalReportScreen() {
        SwingUtilities.invokeLater(() -> {
            setVisible(true);
            toFront();
        });
    }
}