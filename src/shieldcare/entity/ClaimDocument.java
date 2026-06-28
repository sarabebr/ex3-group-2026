package shieldcare.entity;

public class ClaimDocument {
    private int documentId;
    private int claimId;
    private String documentName;
    private String status;

    public ClaimDocument(int documentId, int claimId, String documentName, String status) {
        this.documentId = documentId;
        this.claimId = claimId;
        this.documentName = documentName;
        this.status = status;
    }

    public int getClaimId() {
        return claimId;
    }

    public String getStatus() {
        return status;
    }

    public boolean isUploaded() {
        return "Uploaded".equalsIgnoreCase(status);
    }
}