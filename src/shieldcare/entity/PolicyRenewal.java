package shieldcare.entity;

import java.time.LocalDate;

public class PolicyRenewal {

    private int renewalId;
    private int policyId;
    private String policyNumber;
    private int renewalVersion;
    private int clientId;
    private int agentId;
    private String insuranceType;
    private int insurerId;
    private String paymentFrequency;

    private LocalDate oldStartDate;
    private LocalDate oldExpirationDate;
    private double oldAmount;

    private LocalDate newStartDate;
    private LocalDate newExpirationDate;
    private double newAmount;

    private String coverageDetails;
    private boolean insurerSupported;
    private boolean coveredItemChanged;
    private String renewalStatus;
    private String remarks;

    public PolicyRenewal() {
        this.renewalStatus = "Pending";
    }

    public PolicyRenewal(
            int renewalId,
            int policyId,
            String policyNumber,
            int renewalVersion,
            int clientId,
            int agentId,
            String insuranceType,
            int insurerId,
            String paymentFrequency,
            LocalDate oldStartDate,
            LocalDate oldExpirationDate,
            double oldAmount,
            String coverageDetails) {

        this.renewalId = renewalId;
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.renewalVersion = renewalVersion;
        this.clientId = clientId;
        this.agentId = agentId;
        this.insuranceType = insuranceType;
        this.insurerId = insurerId;
        this.paymentFrequency = paymentFrequency;
        this.oldStartDate = oldStartDate;
        this.oldExpirationDate = oldExpirationDate;
        this.oldAmount = oldAmount;
        this.newAmount = oldAmount;
        this.coverageDetails = coverageDetails;
        this.renewalStatus = "Pending";
    }

    public void applyDefaultRenewalPeriod() {
        if (oldExpirationDate == null) {
            throw new IllegalStateException("Old expiration date is required.");
        }

        newStartDate = oldExpirationDate.plusDays(1);
        newExpirationDate = newStartDate.plusYears(1).minusDays(1);
    }

    public boolean hasValidRenewalPeriod() {
        return oldExpirationDate != null
                && newStartDate != null
                && newExpirationDate != null
                && newStartDate.isAfter(oldExpirationDate)
                && !newExpirationDate.isBefore(newStartDate);
    }

    public int getRenewalId() {
        return renewalId;
    }

    public void setRenewalId(int renewalId) {
        this.renewalId = renewalId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public void setPolicyId(int policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public int getRenewalVersion() {
        return renewalVersion;
    }

    public void setRenewalVersion(int renewalVersion) {
        this.renewalVersion = renewalVersion;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAgentId() {
        return agentId;
    }

    public void setAgentId(int agentId) {
        this.agentId = agentId;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public void setInsuranceType(String insuranceType) {
        this.insuranceType = insuranceType;
    }

    public int getInsurerId() {
        return insurerId;
    }

    public void setInsurerId(int insurerId) {
        this.insurerId = insurerId;
    }

    public String getPaymentFrequency() {
        return paymentFrequency;
    }

    public void setPaymentFrequency(String paymentFrequency) {
        this.paymentFrequency = paymentFrequency;
    }

    public LocalDate getOldStartDate() {
        return oldStartDate;
    }

    public void setOldStartDate(LocalDate oldStartDate) {
        this.oldStartDate = oldStartDate;
    }

    public LocalDate getOldExpirationDate() {
        return oldExpirationDate;
    }

    public void setOldExpirationDate(LocalDate oldExpirationDate) {
        this.oldExpirationDate = oldExpirationDate;
    }

    public double getOldAmount() {
        return oldAmount;
    }

    public void setOldAmount(double oldAmount) {
        this.oldAmount = oldAmount;
    }

    public LocalDate getNewStartDate() {
        return newStartDate;
    }

    public void setNewStartDate(LocalDate newStartDate) {
        this.newStartDate = newStartDate;
    }

    public LocalDate getNewExpirationDate() {
        return newExpirationDate;
    }

    public void setNewExpirationDate(LocalDate newExpirationDate) {
        this.newExpirationDate = newExpirationDate;
    }

    public double getNewAmount() {
        return newAmount;
    }

    public void setNewAmount(double newAmount) {
        this.newAmount = newAmount;
    }

    public String getCoverageDetails() {
        return coverageDetails;
    }

    public void setCoverageDetails(String coverageDetails) {
        this.coverageDetails = coverageDetails;
    }

    public boolean isInsurerSupported() {
        return insurerSupported;
    }

    public void setInsurerSupported(boolean insurerSupported) {
        this.insurerSupported = insurerSupported;
    }

    public boolean isCoveredItemChanged() {
        return coveredItemChanged;
    }

    public void setCoveredItemChanged(boolean coveredItemChanged) {
        this.coveredItemChanged = coveredItemChanged;
    }

    public String getRenewalStatus() {
        return renewalStatus;
    }

    public void setRenewalStatus(String renewalStatus) {
        this.renewalStatus = renewalStatus;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}