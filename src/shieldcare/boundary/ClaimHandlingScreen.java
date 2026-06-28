package shieldcare.boundary;

import shieldcare.control.ClaimHandlingController;
import shieldcare.entity.Claim;
import shieldcare.entity.Policy;

public class ClaimHandlingScreen {

    public void displayClaimScreen() {
        System.out.println("\n[Boundary] Claim Handling Screen");

        ClaimHandlingController controller = new ClaimHandlingController();

        Policy policy = new Policy(
                1,
                "POL-1001",
                "Vehicle",
                "Active",
                5000.0
        );

        Claim claim = controller.createClaim(
                1,
                policy.getPolicyId(),
                "Vehicle Damage",
                15000.0,
                true
        );

        boolean policyActive = controller.checkPolicyStatus(policy);

        if (!policyActive) {
            controller.updateClaimStatus(claim, "Rejected");
            return;
        }

        boolean documentsComplete = controller.checkDocuments(true);

        if (!documentsComplete) {
            controller.updateClaimStatus(claim, "Waiting For Documents");
            return;
        }

        controller.classifyClaim(claim);
        controller.evaluateDamage(claim);

        claim.setFormsApproved(true);
        claim.setThirdPartyCompleted(true);

        controller.updateClaimStatus(claim, "Approved");
    }
}