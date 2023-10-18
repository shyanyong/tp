package seedu.address.logic;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.address.logic.parser.Prefix;
import seedu.address.model.prescription.Prescription;


/**
 * Container for user visible messages.
 */
public class Messages {

    public static final String MESSAGE_UNKNOWN_COMMAND = "Unknown command";
    public static final String MESSAGE_INVALID_COMMAND_FORMAT = "Invalid command format! \n%1$s";
    public static final String MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX = "The prescription index "
            + "provided is invalid";
    public static final String MESSAGE_PRESCRIPTIONS_LISTED_OVERVIEW = "%1$d prescriptions listed!";
    public static final String MESSAGE_DUPLICATE_FIELDS =
            "Multiple values specified for the following single-valued field(s): ";

    /**
     * Returns an error message indicating the duplicate prefixes.
     */
    public static String getErrorMessageForDuplicatePrefixes(Prefix... duplicatePrefixes) {
        assert duplicatePrefixes.length > 0;

        Set<String> duplicateFields =
                Stream.of(duplicatePrefixes).map(Prefix::toString).collect(Collectors.toSet());

        return MESSAGE_DUPLICATE_FIELDS + String.join(" ", duplicateFields);
    }

    /**
     * Formats the {@code prescription} for display to the user.
     */
    public static String format(Prescription prescription) {
        final StringBuilder builder = new StringBuilder();
        builder.append(prescription.getName())
                .append("; Dosage: ")
                .append(prescription.getDosage())
                .append("; Frequency: ")
                .append(prescription.getFrequency())
                .append("; Start Date: ")
                .append(prescription.getStartDate())
                .append("; End Date: ")
                .append(prescription.getEndDate())
                .append("; Expiry Date: ")
                .append(prescription.getExpiryDate())
                .append("; Total stock: ")
                .append(prescription.getTotalStock())
                .append("; Note: ")
                .append(prescription.getNote());
        // person.getTags().forEach(builder::append);
        return builder.toString();
    }

}
