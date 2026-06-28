package shieldcare.entity;

public class ImportedPolicyRecord {
    private String policyNumber;
    private String insuranceType;
    private String status;
    private double amount;

    public ImportedPolicyRecord(String policyNumber, String insuranceType, String status, double amount) {
        this.policyNumber = policyNumber;
        this.insuranceType = insuranceType;
        this.status = status;
        this.amount = amount;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getInsuranceType() {
        return insuranceType;
    }

    public String getStatus() {
        return status;
    }

    public double getAmount() {
        return amount;
    }
}