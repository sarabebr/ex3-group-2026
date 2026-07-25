package shieldcare.control;

import shieldcare.entity.PolicyRenewal;

public class RenewalAmountController {

    private static final double STANDARD_INCREASE_RATE = 0.05;
    private static final double ITEM_CHANGE_RATE = 0.10;

    public double recalculateRenewalAmount(PolicyRenewal renewal) {

        if (renewal == null) {
            throw new IllegalArgumentException(
                    "Renewal data cannot be null.");
        }

        if (renewal.getOldAmount() < 0) {
            throw new IllegalArgumentException(
                    "Old policy amount cannot be negative.");
        }

        double newAmount = renewal.getOldAmount();

        // Standard renewal increase of 5%
        newAmount += renewal.getOldAmount()
                * STANDARD_INCREASE_RATE;

        // Additional increase if the covered item was changed
        if (renewal.isCoveredItemChanged()) {
            newAmount += renewal.getOldAmount()
                    * ITEM_CHANGE_RATE;
        }

        newAmount = Math.round(newAmount * 100.0) / 100.0;

        renewal.setNewAmount(newAmount);

        System.out.println(
                "Renewal amount recalculated: " + newAmount);

        return newAmount;
    }

    public double calculateIncreaseAmount(PolicyRenewal renewal) {

        if (renewal == null) {
            return 0;
        }

        return renewal.getNewAmount()
                - renewal.getOldAmount();
    }
}