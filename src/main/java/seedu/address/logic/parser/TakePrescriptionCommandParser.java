package seedu.address.logic.parser;

import static seedu.address.logic.MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_CONSUMPTION;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.TakePrescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Name;

/**
 * Parses input arguments and creates a new TakePrescriptionCommand object
 */
public class TakePrescriptionCommandParser implements ParserPrescription<TakePrescriptionCommand> {

    /**
     * Parses the given arguments to create a TakePrescriptionCommand.
     *
     * @param args User input representing the command.
     * @return A TakePrescriptionCommand for taking a specified number of doses from a prescription.
     * @throws ParseException If the user input does not conform to the expected format.
     */
    @Override
    public TakePrescriptionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_CONSUMPTION);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_CONSUMPTION)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    TakePrescriptionCommand.MESSAGE_USAGE));
        }

        Name name = ParserUtilPrescription.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Dosage dosesToTake = ParserUtilPrescription.parseDosage(argMultimap.getValue(PREFIX_CONSUMPTION).get());

        return new TakePrescriptionCommand(name, Integer.parseInt(dosesToTake.fullDosage));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}
