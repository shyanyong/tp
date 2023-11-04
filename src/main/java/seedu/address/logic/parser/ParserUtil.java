package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.drug.Drug;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Stock;



/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index is not a non-zero unsigned integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String Dosage} into a {@code Dosage}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code dosage} is invalid.
     */
    public static Dosage parseDosage(String dosage) throws ParseException {
        requireNonNull(dosage);
        String trimmedDosage = dosage.trim();
        if (!Dosage.isValidDosage(trimmedDosage)) {
            throw new ParseException(Dosage.MESSAGE_CONSTRAINTS);
        }
        return new Dosage(trimmedDosage);
    }

    /**
     * Parses a {@code String Frequency} into a {@code Frequency}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code frequency} is invalid.
     */
    public static Frequency parseFrequency(String frequency) throws ParseException {
        requireNonNull(frequency);
        String trimmedFrequency = frequency.trim();
        if (!Frequency.isValidFrequency(trimmedFrequency)) {
            throw new ParseException(Frequency.MESSAGE_CONSTRAINTS);
        }
        return new Frequency(trimmedFrequency);
    }

    /**
     * Parses a {@code String StartDate} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code startDate} is invalid.
     */
    public static Date parseStartDate(String startDate) throws ParseException {
        requireNonNull(startDate);
        String trimmedStartDate = startDate.trim();
        if (!Date.isValidDate(trimmedStartDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedStartDate);
    }

    /**
     * Parses a {@code String EndDate} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code endDate} is invalid.
     */
    public static Date parseEndDate(String endDate) throws ParseException {
        requireNonNull(endDate);
        String trimmedEndDate = endDate.trim();
        if (!Date.isValidDate(trimmedEndDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedEndDate);
    }

    /**
     * Parses a {@code String ExpiryDate} into a {@code Date}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code expiryDate} is invalid.
     */
    public static Date parseExpiryDate(String expiryDate) throws ParseException {
        requireNonNull(expiryDate);
        String trimmedExpiryDate = expiryDate.trim();
        if (!Date.isValidDate(trimmedExpiryDate)) {
            throw new ParseException(Date.MESSAGE_CONSTRAINTS);
        }
        return new Date(trimmedExpiryDate);
    }

    /**
     * Parses a {@code String TotalStock} into a {@code Stock}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code totalStock} is invalid.
     */
    public static Stock parseTotalStock(String totalStock) throws ParseException {
        requireNonNull(totalStock);
        String trimmedTotalStock = totalStock.trim();
        if (!Stock.isValidStock(trimmedTotalStock)) {
            throw new ParseException(Stock.MESSAGE_CONSTRAINTS);
        }
        return new Stock(trimmedTotalStock);
    }

    /**
     * Parses a {@code String ConsumptionCount} into a {@code ConsumptionCount}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code consumptionCount} is invalid.
     */
    public static ConsumptionCount parseConsumptionCount(String consumptionCount) throws ParseException {
        requireNonNull(consumptionCount);
        String trimmedConsumptionCount = consumptionCount.trim();
        if (!ConsumptionCount.isValidConsumptionCount(trimmedConsumptionCount)) {
            throw new ParseException(ConsumptionCount.MESSAGE_CONSTRAINTS);
        }
        return new ConsumptionCount(trimmedConsumptionCount);
    }

    /**
     * Parses a {@code String Note} into a {@code Note}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code note} is invalid.
     */
    public static Note parseNote(String note) throws ParseException {
        requireNonNull(note);
        String trimmedNote = note.trim();
        if (!Note.isValidNote(trimmedNote)) {
            throw new ParseException(Note.MESSAGE_CONSTRAINTS);
        }
        return new Note(trimmedNote);
    }

    /**
     * Parses a {@code String Drug} into a {@code Drug}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code Drug} is invalid.
     */
    public static Drug parseDrug(String drug) throws ParseException {
        requireNonNull(drug);
        String trimmedDrug = drug.trim();
        if (!Drug.isValidDrugName(trimmedDrug)) {
            throw new ParseException(Drug.MESSAGE_CONSTRAINTS);
        }
        return new Drug(trimmedDrug);
    }

    /**
     * Parses {@code Collection<String> Drugs} into a {@code Set<Drug>}.
     */
    public static Set<Drug> parseDrugs(Collection<String> drugs) throws ParseException {
        requireNonNull(drugs);
        final Set<Drug> drugSet = new HashSet<>();
        for (String drugList : drugs) {
            for (String drugName : drugList.split(" ")) {
                drugSet.add(parseDrug(drugName.trim()));
            }
        }
        return drugSet;
    }
}
