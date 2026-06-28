package shieldcare.control;

import shieldcare.entity.Claim;
import shieldcare.entity.Reimbursement;

public class ReimbursementController {

    public boolean checkEligibility(Claim claim) {
        boolean eligible =
                "Approved".equalsIgnoreCase(claim.getStatus())
                        && claim.isFormsApproved()
                        && claim.isThirdPartyCompleted();

        if (eligible) {
            System.out.println("Claim is eligible for reimbursement.");
        } else {
            System.out.println("Claim is NOT eligible for reimbursement.");
            System.out.println("Required: Approved claim, approved forms, completed third-party interactions.");
        }

        return eligible;
    }

    public Reimbursement createReimbursement(int reimbursementId, Claim claim, double amount) {
        Reimbursement reimbursement = new Reimbursement(reimbursementId, claim.getClaimId(), amount);
        System.out.println("Reimbursement record created.");
        return reimbursement;
    }

    public void processPayment(Reimbursement reimbursement, Claim claim) {
        reimbursement.processPayment();
        claim.setStatus("Reimbursed");

        System.out.println("Payment processed.");
        System.out.println("Reimbursement status: " + reimbursement.getStatus());
        System.out.println("Claim status: " + claim.getStatus());
    }

    public void updateReimbursementStatus(Reimbursement reimbursement) {
        System.out.println("Reimbursement status updated: " + reimbursement.getStatus());
    }
}