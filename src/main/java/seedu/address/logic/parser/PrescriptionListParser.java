package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Pattern;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllConflictsCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListCompletedCommand;
import seedu.address.logic.commands.ListConflictsCommand;
import seedu.address.logic.commands.ListTodayCommand;
import seedu.address.logic.commands.ReminderCommand;
import seedu.address.logic.commands.TakeCommand;
import seedu.address.logic.commands.UntakeCommand;
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
    public Command parseCommand(String userInput) throws ParseException {
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
        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);
        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);
        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);
        case ListTodayCommand.COMMAND_WORD:
            return new ListTodayCommandParser().parse(arguments);
        case ListConflictsCommand.COMMAND_WORD:
            return new ListConflictsCommandParser().parse(arguments);
        case ListAllConflictsCommand.COMMAND_WORD:
            return new ListAllConflictsCommandParser().parse(arguments);
        case TakeCommand.COMMAND_WORD:
            return new TakeCommandParser().parse(arguments);
        case UntakeCommand.COMMAND_WORD:
            return new UntakeCommandParser().parse(arguments);
        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);
        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);
        case ExitCommand.COMMAND_WORD:
            return new ExitCommandParser().parse(arguments);
        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);
        case ReminderCommand.COMMAND_WORD:
            return new ReminderCommandParser().parse(arguments);
        case ListCompletedCommand.COMMAND_WORD:
            return new ListCompletedCommandParser().parse(arguments);
        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

}
