package seedu.address.model.drug;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Drug in the address book.
 * Guarantees: immutable; name is valid as declared in {@link #isValidDrugName(String)}
 */
public class Drug {

    public static final String MESSAGE_CONSTRAINTS = "Drugs names should be words with only alphabetical characters";
    // regex for only alphabetical characters

    public static final String VALIDATION_REGEX = "[a-zA-Z]+";

    public final String drugName;

    /**
     * Constructs a {@code Drug}.
     *
     * @param drugName A valid Drug name.
     */
    public Drug(String drugName) {
        requireNonNull(drugName);
        checkArgument(isValidDrugName(drugName), MESSAGE_CONSTRAINTS);
        this.drugName = drugName;
    }

    /**
     * Returns true if a given string is a valid Drug name.
     */
    public static boolean isValidDrugName(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Drug)) {
            return false;
        }

        Drug otherDrug = (Drug) other;
        return drugName.equals(otherDrug.drugName);
    }

    @Override
    public int hashCode() {
        return drugName.hashCode();
    }

    /**
     * Format state as text for viewing.
     */
    public String toString() {
        return drugName;
    }

}
