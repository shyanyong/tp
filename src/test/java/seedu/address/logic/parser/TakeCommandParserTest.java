package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSUMPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.TakeCommand;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Name;


public class TakeCommandParserTest {

    private TakeCommandParser parser = new TakeCommandParser();

    @Test
    public void parse_validArgs_success() {
        // Valid arguments with a valid name and dosage
        TakeCommand expectedCommand = new TakeCommand(new Name("Aspirin"), 2);
        assertParseSuccess(parser, " " + PREFIX_NAME + "Aspirin "
                + PREFIX_CONSUMPTION + "2", expectedCommand);
    }

    @Test
    public void parse_notIntegerDosage_failure() {
        // Invalid dosage (not an integer)
        assertParseFailure(parser, " " + PREFIX_NAME + "Aspirin "
                + PREFIX_CONSUMPTION + "abc", ConsumptionCount.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_negativeIntegerDosage_failure() {
        // Invalid dosage (not an integer)
        assertParseFailure(parser, " " + PREFIX_NAME + "Aspirin "
                + PREFIX_CONSUMPTION + "-1", ConsumptionCount.MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_emptyPreamble_failure() {
        // Empty input
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_whitespacePreamble_failure() {
        // Whitespace input
        assertParseFailure(parser, " ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingName_failure() {
        // Missing Name
        assertParseFailure(parser, " " + PREFIX_CONSUMPTION + "2", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingDosage_failure() {
        // Missing dosage
        assertParseFailure(parser, " " + PREFIX_NAME + "Aspirin", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TakeCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                        TakeCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            TakeCommand.MESSAGE_USAGE));
    }
}
