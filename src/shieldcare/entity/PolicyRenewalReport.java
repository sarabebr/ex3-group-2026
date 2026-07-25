package shieldcare.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PolicyRenewalReport {

    private int reportId;
    private LocalDate generationDate;
    private int requestedByAgentId;

    private LocalDate expirationFrom;
    private LocalDate expirationTo;

    private List<PolicyRenewal> renewals;
    private String reportStatus;
    private String outputFilePath;

    public PolicyRenewalReport() {
        this.generationDate = LocalDate.now();
        this.renewals = new ArrayList<>();
        this.reportStatus = "Created";
    }

    public PolicyRenewalReport(
            int reportId,
            int requestedByAgentId,
            LocalDate expirationFrom,
            LocalDate expirationTo) {

        this.reportId = reportId;
        this.generationDate = LocalDate.now();
        this.requestedByAgentId = requestedByAgentId;
        this.expirationFrom = expirationFrom;
        this.expirationTo = expirationTo;
        this.renewals = new ArrayList<>();
        this.reportStatus = "Created";
    }

    public void addRenewal(PolicyRenewal renewal) {
        if (renewal != null) {
            renewals.add(renewal);
        }
    }

    public boolean removeRenewal(PolicyRenewal renewal) {
        return renewals.remove(renewal);
    }

    public int getRenewalCount() {
        return renewals.size();
    }

    public boolean hasRenewals() {
        return !renewals.isEmpty();
    }

    public int getReportId() {
        return reportId;
    }

    public void setReportId(int reportId) {
        this.reportId = reportId;
    }

    public LocalDate getGenerationDate() {
        return generationDate;
    }

    public void setGenerationDate(LocalDate generationDate) {
        this.generationDate = generationDate;
    }

    public int getRequestedByAgentId() {
        return requestedByAgentId;
    }

    public void setRequestedByAgentId(int requestedByAgentId) {
        this.requestedByAgentId = requestedByAgentId;
    }

    public LocalDate getExpirationFrom() {
        return expirationFrom;
    }

    public void setExpirationFrom(LocalDate expirationFrom) {
        this.expirationFrom = expirationFrom;
    }

    public LocalDate getExpirationTo() {
        return expirationTo;
    }

    public void setExpirationTo(LocalDate expirationTo) {
        this.expirationTo = expirationTo;
    }

    public List<PolicyRenewal> getRenewals() {
        return renewals;
    }

    public void setRenewals(List<PolicyRenewal> renewals) {
        this.renewals = renewals != null
                ? renewals
                : new ArrayList<>();
    }

    public String getReportStatus() {
        return reportStatus;
    }

    public void setReportStatus(String reportStatus) {
        this.reportStatus = reportStatus;
    }

    public String getOutputFilePath() {
        return outputFilePath;
    }

    public void setOutputFilePath(String outputFilePath) {
        this.outputFilePath = outputFilePath;
    }
}