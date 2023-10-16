package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandPrescriptionParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandPrescriptionParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.MessagesPrescription;
import seedu.address.logic.commands.ListPrescriptionCommand;

public class ListPrescriptionCommandParserTest {
    private ListPrescriptionCommandParser parser = new ListPrescriptionCommandParser();

    @Test
    public void parse_emptyPreamble_success() {
        assertParseSuccess(parser, "", new ListPrescriptionCommand());
    }

    @Test
    public void parse_preambleWhitespace_success() {
        assertParseSuccess(parser, " ", new ListPrescriptionCommand());
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
            String.format(MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT, ListPrescriptionCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
            String.format(MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT, ListPrescriptionCommand.MESSAGE_USAGE));
    }
}
