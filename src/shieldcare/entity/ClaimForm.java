package shieldcare.entity;

public class ClaimForm {
    private int formId;
    private int claimId;
    private String formType;
    private String status;

    public ClaimForm(int formId, int claimId, String formType, String status) {
        this.formId = formId;
        this.claimId = claimId;
        this.formType = formType;
        this.status = status;
    }

    public int getClaimId() {
        return claimId;
    }

    public boolean isApproved() {
        return "Approved".equalsIgnoreCase(status);
    }
}