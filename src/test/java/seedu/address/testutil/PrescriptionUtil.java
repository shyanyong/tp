package seedu.address.testutil;

import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_TOTAL_STOCK;

import seedu.address.logic.commands.AddPrescriptionCommand;
import seedu.address.model.prescription.Prescription;

/**
 * A utility class for Prescription.
 */
public class PrescriptionUtil {

    /**
     * Returns an add command string for adding the {@code prescription}.
     */
    public static String getAddPrescriptionCommand(Prescription prescription) {
        return AddPrescriptionCommand.COMMAND_WORD + " " + getPrescriptionDetails(prescription);
    }

    /**
     * Returns the part of command string for the given {@code prescription}'s details.
     */
    public static String getPrescriptionDetails(Prescription prescription) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_NAME + prescription.getName().fullName + " ");
        sb.append(PREFIX_DOSAGE + prescription.getDosage().fullDosage + " ");
        sb.append(PREFIX_FREQUENCY + prescription.getFrequency().fullFrequency + " ");
        sb.append(PREFIX_START_DATE + prescription.getStartDate().fullDate + " ");
        sb.append(PREFIX_END_DATE + prescription.getEndDate().fullDate + " ");
        sb.append(PREFIX_EXPIRY_DATE + prescription.getExpiryDate().fullDate + " ");
        sb.append(PREFIX_TOTAL_STOCK + prescription.getTotalStock().getFullStock() + " ");
        sb.append(PREFIX_NOTE + prescription.getNote().fullNote + " ");
        // person.getTags().stream().forEach(
        //     s -> sb.append(PREFIX_TAG + s.tagName + " ")
        // );
        return sb.toString();
    }

}
