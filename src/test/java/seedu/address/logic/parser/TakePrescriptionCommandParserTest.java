package seedu.address.logic.parser;

import static seedu.address.logic.MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandPrescriptionParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandPrescriptionParserTestUtil.assertParseSuccess;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_CONSUMPTION;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NAME;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TakePrescriptionCommand;
import seedu.address.model.prescription.Name;


public class TakePrescriptionCommandParserTest {

    private TakePrescriptionCommandParser parser = new TakePrescriptionCommandParser();

    @Test
    public void parse_validArgs_success() {
        // Valid arguments with a valid name and dosage
        TakePrescriptionCommand expectedCommand = new TakePrescriptionCommand(new Name("Aspirin"), 2);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Aspirin "
                + PREFIX_CONSUMPTION + "2", expectedCommand);
    }

    @Test
    public void parse_notIntegerDosage_failure() {
        // Invalid dosage (not an integer)
        assertParseFailure(parser, " " + PREFIX_NAME + "Aspirin "
                + PREFIX_CONSUMPTION + "abc", TakePrescriptionCommand.MESSAGE_INVALID_DOSE);
    }

    @Test
    public void parse_negativeIntegerDosage_failure() {
        // Invalid dosage (not an integer)
        assertParseFailure(parser, " " + PREFIX_NAME + "Aspirin "
                + PREFIX_CONSUMPTION + "-1", TakePrescriptionCommand.MESSAGE_INVALID_DOSE);
    }

    @Test
    public void parse_emptyPreamble_failure() {
        // Empty input
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TakePrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespacePreamble_failure() {
        // Whitespace input
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TakePrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingName_failure() {
        // Missing Name
        assertParseFailure(parser, " " + PREFIX_CONSUMPTION + "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TakePrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingDosage_failure() {
        // Missing dosage
        assertParseFailure(parser, " " + PREFIX_NAME + "Aspirin", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TakePrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TakePrescriptionCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TakePrescriptionCommand.MESSAGE_USAGE));
    }
}
