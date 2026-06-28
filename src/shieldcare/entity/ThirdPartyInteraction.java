package shieldcare.entity;

public class ThirdPartyInteraction {
    private int interactionId;
    private int claimId;
    private String partyName;
    private String status;

    public ThirdPartyInteraction(int interactionId, int claimId, String partyName, String status) {
        this.interactionId = interactionId;
        this.claimId = claimId;
        this.partyName = partyName;
        this.status = status;
    }

    public boolean isCompleted() {
        return "Completed".equalsIgnoreCase(status);
    }
}