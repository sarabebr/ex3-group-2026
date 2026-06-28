package shieldcare.control;

import shieldcare.entity.Claim;
import shieldcare.entity.Policy;

public class ClaimHandlingController {
    private static final double VEHICLE_DAMAGE_THRESHOLD = 10000.0;

    public Claim createClaim(int claimId, int policyId, String incidentType, double damageAmount, boolean urgent) {
        Claim claim = new Claim(claimId, policyId, incidentType, damageAmount, urgent);
        System.out.println("Claim created with status: " + claim.getStatus());
        return claim;
    }

    public boolean checkPolicyStatus(Policy policy) {
        boolean active = policy.isActive();

        if (active) {
            System.out.println("Policy is active.");
        } else {
            System.out.println("Policy is not active. Claim rejected.");
        }

        return active;
    }

    public boolean checkDocuments(boolean allDocumentsUploaded) {
        if (allDocumentsUploaded) {
            System.out.println("All required documents were uploaded.");
            return true;
        }

        System.out.println("Missing documents. Claim moved to Waiting For Documents.");
        return false;
    }

    public void classifyClaim(Claim claim) {
        if (claim.isUrgent()) {
            claim.setStatus("Prioritized");
            assignCoordinator(claim);
        } else {
            claim.setStatus("Regular Claim");
        }

        System.out.println("Claim classified. Current status: " + claim.getStatus());
    }

    public void assignCoordinator(Claim claim) {
        System.out.println("Available claims coordinator assigned to claim " + claim.getClaimId());
    }

    public void evaluateDamage(Claim claim) {
        if (claim.getDamageAmount() > VEHICLE_DAMAGE_THRESHOLD) {
            assignAppraiser(claim);
        } else {
            claim.setStatus("Under Review");
            System.out.println("No appraiser required. Claim moved to Under Review.");
        }
    }

    public void assignAppraiser(Claim claim) {
        claim.setStatus("Appraiser Assigned");
        System.out.println("Appraiser assigned for claim " + claim.getClaimId());
        claim.setStatus("Under Review");
    }

    public void updateClaimStatus(Claim claim, String newStatus) {
        claim.setStatus(newStatus);
        System.out.println("Claim status updated to: " + newStatus);
    }

    public void suspendClaim(Claim claim) {
        claim.setStatus("Suspended");
        System.out.println("Claim suspended for up to 30 days.");
    }

    public void reactivateClaim(Claim claim) {
        claim.setStatus("Under Review");
        System.out.println("Claim reactivated before cancellation.");
    }
}