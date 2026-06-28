package shieldcare.entity;

public class Reimbursement {
    private int reimbursementId;
    private int claimId;
    private double amount;
    private String status;

    public Reimbursement(int reimbursementId, int claimId, double amount) {
        this.reimbursementId = reimbursementId;
        this.claimId = claimId;
        this.amount = amount;
        this.status = "Pending";
    }

    public int getClaimId() {
        return claimId;
    }

    public String getStatus() {
        return status;
    }

    public void processPayment() {
        this.status = "Reimbursed";
    }
}