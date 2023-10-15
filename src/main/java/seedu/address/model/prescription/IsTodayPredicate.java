package seedu.address.model.prescription;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription}'s {@code Date} is today.
 */
public class IsTodayPredicate implements Predicate<Prescription> {

    @Override
    public boolean test(Prescription prescription) {
        LocalDate today = LocalDate.now();
        LocalDate startDate = prescription.getStartDate().getDate();
        LocalDate endDate = prescription.getEndDate().getDate();
        long daysBetween = ChronoUnit.DAYS.between(startDate, today);

        if (!(today.isAfter(startDate) && today.isBefore(endDate))) {
            return false;
        }
        Frequency frequency = prescription.getFrequency();

        switch (frequency.getFrequency()) {
        case "Daily":
            return true;
        case "Weekly":
            return daysBetween % 7 == 0;
        case "Monthly":
            return daysBetween % 30 == 0;
        default:
            return false;
        }
    }
}
