package shieldcare.entity;

public class Policy {
    private int policyId;
    private String policyNumber;
    private String insuranceType;
    private String status;
    private double amount;

    public Policy(int policyId, String policyNumber, String insuranceType, String status, double amount) {
        this.policyId = policyId;
        this.policyNumber = policyNumber;
        this.insuranceType = insuranceType;
        this.status = status;
        this.amount = amount;
    }

    public int getPolicyId() {
        return policyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public String getStatus() {
        return status;
    }

    public boolean isActive() {
        return "Active".equalsIgnoreCase(status);
    }
}