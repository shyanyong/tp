package seedu.address.model.prescription;

import java.util.function.Predicate;

/**
 * Tests that a {@code Prescription}'s {@code Date} is today.
 */
public class SameNamePredicate implements Predicate<Prescription> {

    private final Name targetName;

    public SameNamePredicate(Name targetName) {
        this.targetName = targetName;
    }

    @Override
    public boolean test(Prescription prescription) {
        return prescription.getName().equals(targetName);
    }
}
