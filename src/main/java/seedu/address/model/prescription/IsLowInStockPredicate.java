package seedu.address.model.prescription;

import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription}'s {@code Stock} is low in stock.
 */
public class IsLowInStockPredicate implements Predicate<Prescription> {
    @Override
    public boolean test(Prescription prescription) {
        if (!prescription.getTotalStock().isPresent()) {
            return false;
        }
        String prescriptionTotalStock = prescription.getTotalStock().get().getFullStock();
        return Integer.parseInt(prescriptionTotalStock) < 10;
    }
}
