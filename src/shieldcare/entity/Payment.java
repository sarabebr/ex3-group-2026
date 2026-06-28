package shieldcare.entity;

public class Payment {
    private int paymentId;
    private int policyId;
    private double amount;
    private String status;

    public Payment(int paymentId, int policyId, double amount, String status) {
        this.paymentId = paymentId;
        this.policyId = policyId;
        this.amount = amount;
        this.status = status;
    }

    public int getPolicyId() {
        return policyId;
    }

    public double getAmount() {
        return amount;
    }

    public String getStatus() {
        return status;
    }
}