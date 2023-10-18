package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a prescription's dosage in the prescription list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDosage(String)}
 */
public class Dosage {

    public static final String MESSAGE_CONSTRAINTS =
            "Dosages should only contain numeric characters, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

    private final String fullDosage;

    /**
     * Constructs a {@code Dosage}.
     *
     * @param dosage A valid dosage.
     */
    public Dosage(String dosage) {
        requireNonNull(dosage);
        checkArgument(isValidDosage(dosage), MESSAGE_CONSTRAINTS);
        fullDosage = dosage;
    }

    /**
     * Returns true if a given string is a valid dosage.
     */
    public static boolean isValidDosage(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullDosage;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Dosage)) {
            return false;
        }

        Dosage otherDosage = (Dosage) other;
        return fullDosage.equals(otherDosage.fullDosage);
    }

    @Override
    public int hashCode() {
        return fullDosage.hashCode();
    }

}
