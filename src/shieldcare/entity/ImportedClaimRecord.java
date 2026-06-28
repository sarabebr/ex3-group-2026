package shieldcare.entity;

public class ImportedClaimRecord {
    private int claimId;
    private String status;

    public ImportedClaimRecord(int claimId, String status) {
        this.claimId = claimId;
        this.status = status;
    }

    public int getClaimId() {
        return claimId;
    }

    public String getStatus() {
        return status;
    }
}