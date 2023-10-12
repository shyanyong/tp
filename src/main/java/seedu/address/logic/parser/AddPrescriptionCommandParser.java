package seedu.address.logic.parser;

import static seedu.address.logic.MessagesPrescription.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_TOTAL_STOCK;

// import java.util.Set;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddPrescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;
// import seedu.address.model.tag.Tag;

/**
 * Parses input arguments and creates a new AddCommand object
 */
public class AddPrescriptionCommandParser implements ParserPrescription<AddPrescriptionCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddCommand
     * and returns an AddCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddPrescriptionCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_NAME, PREFIX_DOSAGE, PREFIX_FREQUENCY,
                    PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_EXPIRY_DATE, PREFIX_TOTAL_STOCK, PREFIX_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DOSAGE, PREFIX_FREQUENCY, PREFIX_START_DATE,
                PREFIX_END_DATE, PREFIX_EXPIRY_DATE, PREFIX_TOTAL_STOCK, PREFIX_NOTE)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddPrescriptionCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME, PREFIX_DOSAGE, PREFIX_FREQUENCY,
                PREFIX_START_DATE, PREFIX_END_DATE, PREFIX_EXPIRY_DATE, PREFIX_TOTAL_STOCK, PREFIX_NOTE);
        Name name = ParserUtilPrescription.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Dosage dosage = ParserUtilPrescription.parseDosage(argMultimap.getValue(PREFIX_DOSAGE).get());
        Frequency frequency = ParserUtilPrescription.parseFrequency(argMultimap.getValue(PREFIX_FREQUENCY).get());
        Date startDate = ParserUtilPrescription.parseStartDate(argMultimap.getValue(PREFIX_START_DATE).get());
        Date endDate = ParserUtilPrescription.parseEndDate(argMultimap.getValue(PREFIX_END_DATE).get());
        Date expiryDate = ParserUtilPrescription.parseExpiryDate(argMultimap.getValue(PREFIX_EXPIRY_DATE).get());
        Stock totalStock = ParserUtilPrescription.parseTotalStock(argMultimap.getValue(PREFIX_TOTAL_STOCK).get());
        Note note = ParserUtilPrescription.parseNote(argMultimap.getValue(PREFIX_NOTE).get());
        // Set<Tag> tagList = ParserUtilPrescription.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Prescription prescription = new Prescription(name, dosage, frequency, startDate, endDate,
                expiryDate, totalStock, note);

        return new AddPrescriptionCommand(prescription);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }

}
