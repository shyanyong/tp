package seedu.address.logic.parser;

import static seedu.address.logic.parser.CommandPrescriptionParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandPrescriptionParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.MessagesPrescription;
import seedu.address.logic.commands.ListTodayPrescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class ListTodayPrescriptionCommandParserTest {
    private ListTodayPrescriptionCommandParser parser = new ListTodayPrescriptionCommandParser();

    @Test
    public void parse_validInput_success() throws ParseException {
        assertParseSuccess(parser, "", new ListTodayPrescriptionCommand());
    }

    @Test
    public void parse_invalidInput_failure() {
        assertParseFailure(parser, "invalid input",
                String.format(MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListTodayPrescriptionCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_extraValues_failure() {
        // Random values
        assertParseFailure(parser, "ABCDEFGH",
                String.format(MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListTodayPrescriptionCommand.MESSAGE_USAGE));

        // Random prefixes
        assertParseFailure(parser, "mn/ABCD d/2",
                String.format(MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT,
                        ListTodayPrescriptionCommand.MESSAGE_USAGE));
    }

}
