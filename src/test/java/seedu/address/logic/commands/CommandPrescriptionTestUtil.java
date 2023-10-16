package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_TOTAL_STOCK;
import static seedu.address.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelPrescription;
import seedu.address.model.PrescriptionList;
import seedu.address.model.prescription.NameContainsKeywordsPredicate;
import seedu.address.model.prescription.Prescription;

/**
 * Contains helper methods for testing commands.
 */
public class CommandPrescriptionTestUtil {

    public static final String VALID_NAME_ASPIRIN = "Aspirin";
    public static final String VALID_NAME_PROPRANOLOL = "Propranolol";
    public static final String VALID_DOSAGE_ASPIRIN = "1";
    public static final String VALID_DOSAGE_PROPRANOLOL = "4";
    public static final String VALID_FREQUENCY_ASPIRIN = "Daily";
    public static final String VALID_FREQUENCY_PROPRANOLOL = "Weekly";
    public static final String VALID_START_DATE_ASPIRIN = "01/10/2023";
    public static final String VALID_START_DATE_PROPRANOLOL = "01/08/2023";
    public static final String VALID_END_DATE_ASPIRIN = "23/02/2024";
    public static final String VALID_END_DATE_PROPRANOLOL = "20/12/2024";
    public static final String VALID_EXPIRY_DATE_ASPIRIN = "12/12/2024";
    public static final String VALID_EXPIRY_DATE_PROPRANOLOL = "22/07/2024";
    public static final String VALID_STOCK_ASPIRIN = "100";
    public static final String VALID_STOCK_PROPRANOLOL = "500";
    public static final String VALID_NOTE_ASPIRIN = "Take after food";
    public static final String VALID_NOTE_PROPRANOLOL = "Take after food";
    // public static final String VALID_TAG_HUSBAND = "husband";
    // public static final String VALID_TAG_FRIEND = "friend";

    public static final String NAME_DESC_ASPIRIN = " " + PREFIX_NAME + VALID_NAME_ASPIRIN;
    public static final String NAME_DESC_PROPRANOLOL = " " + PREFIX_NAME + VALID_NAME_PROPRANOLOL;
    public static final String DOSAGE_DESC_ASPIRIN = " " + PREFIX_DOSAGE + VALID_DOSAGE_ASPIRIN;
    public static final String DOSAGE_DESC_PROPRANOLOL = " " + PREFIX_DOSAGE + VALID_DOSAGE_PROPRANOLOL;
    public static final String FREQUENCY_DESC_ASPIRIN = " " + PREFIX_FREQUENCY + VALID_FREQUENCY_ASPIRIN;
    public static final String FREQUENCY_DESC_PROPRANOLOL = " " + PREFIX_FREQUENCY + VALID_FREQUENCY_PROPRANOLOL;
    public static final String START_DATE_DESC_ASPIRIN = " " + PREFIX_START_DATE + VALID_START_DATE_ASPIRIN;
    public static final String START_DATE_DESC_PROPRANOLOL = " " + PREFIX_START_DATE + VALID_START_DATE_PROPRANOLOL;
    public static final String END_DATE_DESC_ASPIRIN = " " + PREFIX_END_DATE + VALID_END_DATE_ASPIRIN;
    public static final String END_DATE_DESC_PROPRANOLOL = " " + PREFIX_END_DATE + VALID_END_DATE_PROPRANOLOL;
    public static final String EXPIRY_DATE_DESC_ASPIRIN = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_ASPIRIN;
    public static final String EXPIRY_DATE_DESC_PROPRANOLOL = " " + PREFIX_EXPIRY_DATE + VALID_EXPIRY_DATE_PROPRANOLOL;
    public static final String STOCK_DESC_ASPIRIN = " " + PREFIX_TOTAL_STOCK + VALID_STOCK_ASPIRIN;
    public static final String STOCK_DESC_PROPRANOLOL = " " + PREFIX_TOTAL_STOCK + VALID_STOCK_PROPRANOLOL;
    public static final String NOTE_DESC_ASPIRIN = " " + PREFIX_NOTE + VALID_NOTE_ASPIRIN;
    public static final String NOTE_DESC_PROPRANOLOL = " " + PREFIX_NOTE + VALID_NOTE_PROPRANOLOL;

    // public static final String TAG_DESC_FRIEND = " " + PREFIX_TAG + VALID_TAG_FRIEND;
    // public static final String TAG_DESC_HUSBAND = " " + PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String NAME_DESC = " " + PREFIX_NAME + "@sp!r!n"; // non-alphanumeric not allowed
    public static final String DOSAGE_DESC = " " + PREFIX_DOSAGE + "a"; // alphabets not allowed
    public static final String FREQUENCY_DESC = " " + PREFIX_FREQUENCY + "Forever"; // invalid value
    public static final String START_DATE_DESC = " " + PREFIX_START_DATE + "1/1/2023"; // invalid date format
    public static final String END_DATE_DESC = " " + PREFIX_END_DATE + "1/2/24"; // invalid date format
    public static final String EXPIRY_DATE_DESC = " " + PREFIX_EXPIRY_DATE + "2024/1/1"; // invalid date format
    public static final String STOCK_DESC = " " + PREFIX_TOTAL_STOCK + "a"; // alphabets not allowed
    public static final String NOTE_DESC = " " + PREFIX_NOTE; // empty string not allowed
    // public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "hubby*"; // '*' not allowed in tags

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    /*
    public static final EditCommand.EditPersonDescriptor DESC_AMY;
    public static final EditCommand.EditPersonDescriptor DESC_BOB;

    static {
        DESC_AMY = new EditPersonDescriptorBuilder().withName(VALID_NAME_AMY)
            .withPhone(VALID_PHONE_AMY).withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
            .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditPersonDescriptorBuilder().withName(VALID_NAME_BOB)
            .withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
            .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }
    */

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(CommandPrescription command, ModelPrescription actualModel,
        CommandResult expectedCommandResult, ModelPrescription expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(CommandPrescription, ModelPrescription,
     * CommandResult, ModelPrescription)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(CommandPrescription command, ModelPrescription actualModel,
        String expectedMessage, ModelPrescription expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the prescription list, filtered prescription list and selected prescription in {@code actualModel}
     *   remain unchanged
     */
    public static void assertCommandFailure(CommandPrescription command, ModelPrescription actualModel,
        String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        PrescriptionList expectedPrescriptionList = new PrescriptionList(actualModel.getPrescriptionList());
        List<Prescription> expectedFilteredList = new ArrayList<>(actualModel.getFilteredPrescriptionList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedPrescriptionList, actualModel.getPrescriptionList());
        assertEquals(expectedFilteredList, actualModel.getFilteredPrescriptionList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the prescription at the given {@code targetIndex} in the
     * {@code model}'s prescription list.
     */
    public static void showPrescriptionAtIndex(ModelPrescription model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredPrescriptionList().size());

        Prescription prescription = model.getFilteredPrescriptionList().get(targetIndex.getZeroBased());
        final String[] splitName = prescription.getName().fullName.split("\\s+");
        model.updateFilteredPrescriptionList(new NameContainsKeywordsPredicate(Arrays.asList(splitName[0])));

        assertEquals(1, model.getFilteredPrescriptionList().size());
    }

}
