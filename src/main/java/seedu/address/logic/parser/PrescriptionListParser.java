package seedu.address.logic.parser;


import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.CommandPrescription;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListPrescriptionCommand;
import seedu.address.logic.commands.ListTodayPrescriptionCommand;
import seedu.address.logic.commands.TakePrescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;


/**
 * Parses user input.
 */
public class PrescriptionListParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final java.util.logging.Logger logger = LogsCenter.getLogger(PrescriptionListParser.class);

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public CommandPrescription parseCommand(String userInput) throws ParseException {
        final java.util.regex.Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);


        switch (commandWord) {
        case seedu.address.logic.commands.AddPrescriptionCommand.COMMAND_WORD:
            return new AddPrescriptionCommandParser().parse(arguments);
        case seedu.address.logic.commands.ListPrescriptionCommand.COMMAND_WORD:
            return new ListPrescriptionCommandParser().parse(arguments);
        case seedu.address.logic.commands.ListTodayPrescriptionCommand.COMMAND_WORD:
            return new ListTodayPrescriptionCommandParser().parse(arguments);
        case TakePrescriptionCommand.COMMAND_WORD:
            return new TakePrescriptionCommandParser().parse(arguments);
        case seedu.address.logic.commands.DeletePrescriptionCommand.COMMAND_WORD:
            return new DeletePrescriptionCommandParser().parse(arguments);
        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
