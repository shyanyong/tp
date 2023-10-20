package seedu.address.model.prescription;

import java.time.LocalDate;
import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription}'s {@code Date} is valid.
 */
public class IsValidDatesPredicate implements Predicate<Prescription> {
    @Override
    public boolean test(Prescription prescription) {
        LocalDate startDate = prescription.getStartDate().getDate();
        LocalDate endDate = prescription.getEndDate().getDate();
        LocalDate expiryDate = prescription.getExpiryDate().getDate();

        return startDate.isBefore(endDate) && endDate.isBefore(expiryDate);

    }
}
