package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSUMPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.UntakeCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Name;

/**
 * Parses input arguments and creates a new UntakePrescriptionCommand object
 */
public class UntakeCommandParser implements Parser<UntakeCommand> {

    /**
     * Parses the given arguments to create an UntakePrescriptionCommand.
     *
     * @param args User input representing the command.
     * @return An UntakePrescriptionCommand for untaking a specified number of doses from a prescription.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public UntakeCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CONSUMPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CONSUMPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    UntakeCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_CONSUMPTION);
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        ConsumptionCount consumptionCount = ParserUtil
                .parseConsumptionCount(argMultimap.getValue(PREFIX_CONSUMPTION).get());
        int dosesToUntake = Integer.parseInt(consumptionCount.getConsumptionCount());

        return new UntakeCommand(name, dosesToUntake);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
