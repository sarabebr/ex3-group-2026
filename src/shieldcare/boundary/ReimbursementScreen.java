package shieldcare.boundary;

import shieldcare.control.ReimbursementController;
import shieldcare.entity.Claim;
import shieldcare.entity.Reimbursement;

public class ReimbursementScreen {

    public void displayReimbursementScreen() {
        System.out.println("\n[Boundary] Reimbursement Screen");

        Claim claim = new Claim(
                1,
                1,
                "Vehicle Damage",
                15000.0,
                true
        );

        claim.setStatus("Approved");
        claim.setFormsApproved(true);
        claim.setThirdPartyCompleted(true);

        ReimbursementController controller = new ReimbursementController();

        boolean eligible = controller.checkEligibility(claim);

        if (eligible) {
            Reimbursement reimbursement =
                    controller.createReimbursement(1, claim, 12000.0);

            controller.processPayment(reimbursement, claim);
            controller.updateReimbursementStatus(reimbursement);
        }
    }
}