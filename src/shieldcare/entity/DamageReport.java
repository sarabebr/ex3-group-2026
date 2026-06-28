package shieldcare.entity;

public class DamageReport {
    private int reportId;
    private int claimId;
    private double damageAmount;
    private String severity;
    private String appraiser;

    public DamageReport(int reportId, int claimId, double damageAmount, String severity, String appraiser) {
        this.reportId = reportId;
        this.claimId = claimId;
        this.damageAmount = damageAmount;
        this.severity = severity;
        this.appraiser = appraiser;
    }
}