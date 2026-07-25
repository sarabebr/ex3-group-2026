package shieldcare.control;

import shieldcare.entity.PolicyRenewal;

public class PolicyRenewalValidationController {

    public boolean validatePolicyType(PolicyRenewal renewal) {
        if (renewal == null || renewal.getInsuranceType() == null
                || renewal.getInsuranceType().isBlank()) {

            System.out.println("Policy type is missing.");
            return false;
        }

        System.out.println("Policy type is valid: "
                + renewal.getInsuranceType());

        return true;
    }

    public boolean validateInsurerSupport(PolicyRenewal renewal) {
        if (renewal == null) {
            System.out.println("Renewal data is missing.");
            return false;
        }

        if (!renewal.isInsurerSupported()) {
            renewal.setRenewalStatus("Insurer Not Supported");
            renewal.setRemarks(
                    "The insurer does not support automatic renewal.");

            System.out.println(
                    "The insurer does not support this renewal.");

            return false;
        }

        System.out.println("The insurer supports the renewal.");
        return true;
    }

    public boolean validateCoveredItemChanges(PolicyRenewal renewal) {
        if (renewal == null) {
            System.out.println("Renewal data is missing.");
            return false;
        }

        if (renewal.isCoveredItemChanged()) {
            renewal.setRenewalStatus("Manual Review Required");
            renewal.setRemarks(
                    "The covered item was changed and requires manual review.");

            System.out.println(
                    "Covered item changes were detected.");

            return false;
        }

        System.out.println("No covered item changes were detected.");
        return true;
    }

    public boolean validateRenewalPeriod(PolicyRenewal renewal) {
        if (renewal == null) {
            System.out.println("Renewal data is missing.");
            return false;
        }

        if (!renewal.hasValidRenewalPeriod()) {
            renewal.setRenewalStatus("Invalid Renewal Period");
            renewal.setRemarks(
                    "The renewal dates are invalid or overlap with the old policy.");

            System.out.println(
                    "The renewal period is invalid.");

            return false;
        }

        System.out.println(
                "The renewal period is valid and does not overlap.");

        return true;
    }

    public boolean validateRenewal(PolicyRenewal renewal) {
        boolean validPolicyType = validatePolicyType(renewal);
        boolean insurerSupported = validateInsurerSupport(renewal);
        boolean coveredItemValid =
                validateCoveredItemChanges(renewal);
        boolean validPeriod = validateRenewalPeriod(renewal);

        boolean valid = validPolicyType
                && insurerSupported
                && coveredItemValid
                && validPeriod;

        if (valid) {
            renewal.setRenewalStatus("Validated");
            renewal.setRemarks("Renewal validation completed successfully.");

            System.out.println(
                    "Policy renewal validation completed successfully.");
        } else {
            System.out.println(
                    "Policy renewal validation failed.");
        }

        return valid;
    }
}