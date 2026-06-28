package shieldcare.entity;

public class Claim {
    private int claimId;
    private int policyId;
    private String incidentType;
    private String status;
    private double damageAmount;
    private boolean urgent;
    private boolean thirdPartyCompleted;
    private boolean formsApproved;

    public Claim(int claimId, int policyId, String incidentType, double damageAmount, boolean urgent) {
        this.claimId = claimId;
        this.policyId = policyId;
        this.incidentType = incidentType;
        this.damageAmount = damageAmount;
        this.urgent = urgent;
        this.status = "Initial Claim Request";
        this.thirdPartyCompleted = false;
        this.formsApproved = false;
    }

    public int getClaimId() {
        return claimId;
    }

    public int getPolicyId() {
        return policyId;
    }

    public String getStatus() {
        return status;
    }

    public double getDamageAmount() {
        return damageAmount;
    }

    public boolean isUrgent() {
        return urgent;
    }

    public boolean isThirdPartyCompleted() {
        return thirdPartyCompleted;
    }

    public boolean isFormsApproved() {
        return formsApproved;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setThirdPartyCompleted(boolean thirdPartyCompleted) {
        this.thirdPartyCompleted = thirdPartyCompleted;
    }

    public void setFormsApproved(boolean formsApproved) {
        this.formsApproved = formsApproved;
    }
}