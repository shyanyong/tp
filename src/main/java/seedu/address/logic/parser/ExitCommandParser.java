package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new ExitPrescriptionCommand object
 */
public class ExitCommandParser implements Parser<ExitCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the ExitPrescriptionCommand
     * and returns a ExitPrescriptionCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ExitCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args);

        if (!argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ExitCommand.MESSAGE_USAGE));
        }

        return new ExitCommand();
    }

}
