package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.parser.ParserUtil.MESSAGE_INVALID_INDEX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Stock;

public class ParserUtilTest {
    private static final String INVALID_NAME = "@sp!r!n";
    private static final String INVALID_DOSAGE = "abc";
    private static final String INVALID_FREQUENCY = "Yearly";
    private static final String INVALID_START_DATE = "1/1/2023";
    private static final String INVALID_END_DATE = "1/1/2024";
    private static final String INVALID_EXPIRY_DATE = "1/1/2025";
    private static final String INVALID_TOTAL_STOCK = "a";
    private static final String INVALID_CONSUMPTION = "a";
    private static final String INVALID_NOTE = "***";

    private static final String VALID_NAME = "Aspirin";
    private static final String VALID_DOSAGE = "1";
    private static final String VALID_FREQUENCY = "Daily";
    private static final String VALID_START_DATE = "01/10/2023";
    private static final String VALID_END_DATE = "23/02/2024";
    private static final String VALID_EXPIRY_DATE = "12/12/2024";
    private static final String VALID_TOTAL_STOCK = "100";
    private static final String VALID_CONSUMPTION = "1";
    private static final String VALID_NOTE = "Take after food";

    private static final String WHITESPACE = " \t\r\n";

    @Test
    public void parseIndex_invalidInput_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseIndex("10 a"));
    }

    @Test
    public void parseIndex_outOfRangeInput_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_INVALID_INDEX, ()
            -> ParserUtil.parseIndex(Long.toString(Integer.MAX_VALUE + 1)));
    }

    @Test
    public void parseIndex_validInput_success() throws Exception {
        // No whitespaces
        assertEquals(INDEX_FIRST_PRESCRIPTION, ParserUtil.parseIndex("1"));

        // Leading and trailing whitespaces
        assertEquals(INDEX_FIRST_PRESCRIPTION, ParserUtil.parseIndex("  1  "));
    }

    @Test
    public void parseName_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseName((String) null));
    }

    @Test
    public void parseName_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseName(INVALID_NAME));
    }

    @Test
    public void parseName_validValueWithoutWhitespace_returnsName() throws Exception {
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(VALID_NAME));
    }

    @Test
    public void parseName_validValueWithWhitespace_returnsTrimmedName() throws Exception {
        String nameWithWhitespace = WHITESPACE + VALID_NAME + WHITESPACE;
        Name expectedName = new Name(VALID_NAME);
        assertEquals(expectedName, ParserUtil.parseName(nameWithWhitespace));
    }

    @Test
    public void parseDosage_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDosage((String) null));
    }

    @Test
    public void parseDosage_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDosage(INVALID_DOSAGE));
    }

    @Test
    public void parseDosage_validValueWithoutWhitespace_returnsDosage() throws Exception {
        Dosage expectedDosage = new Dosage(VALID_DOSAGE);
        assertEquals(expectedDosage, ParserUtil.parseDosage(VALID_DOSAGE));
    }

    @Test
    public void parseDosage_validValueWithWhitespace_returnsTrimmedDosage() throws Exception {
        String dosageWithWhitespace = WHITESPACE + VALID_DOSAGE + WHITESPACE;
        Dosage expectedDosage = new Dosage(VALID_DOSAGE);
        assertEquals(expectedDosage, ParserUtil.parseDosage(dosageWithWhitespace));
    }

    @Test
    public void parseFrequency_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseFrequency((String) null));
    }

    @Test
    public void parseFrequency_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseFrequency(INVALID_FREQUENCY));
    }

    @Test
    public void parseFrequency_validValueWithoutWhitespace_returnsFrequency() throws Exception {
        Frequency expectedFrequency = new Frequency(VALID_FREQUENCY);
        assertEquals(expectedFrequency, ParserUtil.parseFrequency(VALID_FREQUENCY));
    }

    @Test
    public void parseFrequency_validValueWithWhitespace_returnsTrimmedFrequency() throws Exception {
        String frequencyWithWhitespace = WHITESPACE + VALID_FREQUENCY + WHITESPACE;
        Frequency expectedFrequency = new Frequency(VALID_FREQUENCY);
        assertEquals(expectedFrequency, ParserUtil.parseFrequency(frequencyWithWhitespace));
    }

    @Test
    public void parseStartDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseStartDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_START_DATE));
    }

    @Test
    public void parseStartDate_validValueWithoutWhitespace_returnsStartDate() throws Exception {
        Date expectedStartDate = new Date(VALID_START_DATE);
        assertEquals(expectedStartDate, ParserUtil.parseDate(VALID_START_DATE));
    }

    @Test
    public void parseStartDate_validValueWithWhitespace_returnsTrimmedStartDate() throws Exception {
        String startDateWithWhitespace = WHITESPACE + VALID_START_DATE + WHITESPACE;
        Date expectedStartDate = new Date(VALID_START_DATE);
        assertEquals(expectedStartDate, ParserUtil.parseDate(startDateWithWhitespace));
    }

    @Test
    public void parseEndDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseEndDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_END_DATE));
    }

    @Test
    public void parseEndDate_validValueWithoutWhitespace_returnsEndDate() throws Exception {
        Date expectedEndDate = new Date(VALID_END_DATE);
        assertEquals(expectedEndDate, ParserUtil.parseDate(VALID_END_DATE));
    }

    @Test
    public void parseEndDate_validValueWithWhitespace_returnsTrimmedEndDate() throws Exception {
        String endDateWithWhitespace = WHITESPACE + VALID_END_DATE + WHITESPACE;
        Date expectedEndDate = new Date(VALID_END_DATE);
        assertEquals(expectedEndDate, ParserUtil.parseDate(endDateWithWhitespace));
    }

    @Test
    public void parseExpiryDate_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseDate((String) null));
    }

    @Test
    public void parseExpiryDate_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseDate(INVALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithoutWhitespace_returnsExpiryDate() throws Exception {
        Date expectedExpiryDate = new Date(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseDate(VALID_EXPIRY_DATE));
    }

    @Test
    public void parseExpiryDate_validValueWithWhitespace_returnsTrimmedExpiryDate() throws Exception {
        String expiryDateWithWhitespace = WHITESPACE + VALID_EXPIRY_DATE + WHITESPACE;
        Date expectedExpiryDate = new Date(VALID_EXPIRY_DATE);
        assertEquals(expectedExpiryDate, ParserUtil.parseDate(expiryDateWithWhitespace));
    }

    @Test
    public void parseTotalStock_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseTotalStock((String) null));
    }

    @Test
    public void parseTotalStock_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseTotalStock(INVALID_TOTAL_STOCK));
    }

    @Test
    public void parseTotalStock_validValueWithoutWhitespace_returnsTotalStock() throws Exception {
        Stock expectedTotalStock = new Stock(VALID_TOTAL_STOCK);
        assertEquals(expectedTotalStock, ParserUtil.parseTotalStock(VALID_TOTAL_STOCK));
    }

    @Test
    public void parseTotalStock_validValueWithWhitespace_returnsTrimmedTotalStock() throws Exception {
        String totalStockWithWhitespace = WHITESPACE + VALID_TOTAL_STOCK + WHITESPACE;
        Stock expectedTotalStock = new Stock(VALID_TOTAL_STOCK);
        assertEquals(expectedTotalStock, ParserUtil.parseTotalStock(totalStockWithWhitespace));
    }

    @Test
    public void parseConsumptionCount_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseConsumptionCount((String) null));
    }

    @Test
    public void parseConsumptionCount_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseConsumptionCount(INVALID_CONSUMPTION));
    }

    @Test
    public void parseConsumptionCount_validValueWithoutWhitespace_returnsConsumptionCount() throws Exception {
        ConsumptionCount expectedConsumptionCount = new ConsumptionCount(VALID_CONSUMPTION);
        assertEquals(expectedConsumptionCount, ParserUtil.parseConsumptionCount(VALID_CONSUMPTION));
    }

    @Test
    public void parseConsumptionCount_validValueWithWhitespace_returnsTrimmedConsumptionCount() throws Exception {
        String consumptionCountWithWhitespace = WHITESPACE + VALID_CONSUMPTION + WHITESPACE;
        ConsumptionCount expectedConsumptionCount = new ConsumptionCount(VALID_CONSUMPTION);
        assertEquals(expectedConsumptionCount, ParserUtil.parseConsumptionCount(consumptionCountWithWhitespace));
    }

    @Test
    public void parseNote_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtil.parseNote((String) null));
    }

    @Test
    public void parseNote_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtil.parseNote(INVALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithoutWhitespace_returnsNote() throws Exception {
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(VALID_NOTE));
    }

    @Test
    public void parseNote_validValueWithWhitespace_returnsTrimmedNote() throws Exception {
        String noteWithWhitespace = WHITESPACE + VALID_NOTE + WHITESPACE;
        Note expectedNote = new Note(VALID_NOTE);
        assertEquals(expectedNote, ParserUtil.parseNote(noteWithWhitespace));
    }

    /*
    @Test
    public void parseTag_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtilPrescription.parseTag(null));
    }

    @Test
    public void parseTag_invalidValue_throwsParseException() {
        assertThrows(ParseException.class, () -> ParserUtilPrescription.parseTag(INVALID_TAG));
    }

    @Test
    public void parseTag_validValueWithoutWhitespace_returnsTag() throws Exception {
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtilPrescription.parseTag(VALID_TAG_1));
    }

    @Test
    public void parseTag_validValueWithWhitespace_returnsTrimmedTag() throws Exception {
        String tagWithWhitespace = WHITESPACE + VALID_TAG_1 + WHITESPACE;
        Tag expectedTag = new Tag(VALID_TAG_1);
        assertEquals(expectedTag, ParserUtilPrescription.parseTag(tagWithWhitespace));
    }

    @Test
    public void parseTags_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> ParserUtilPrescription.parseTags(null));
    }

    @Test
    public void parseTags_collectionWithInvalidTags_throwsParseException() {
        assertThrows(ParseException.class,
            () -> ParserUtilPrescription.parseTags(Arrays.asList(VALID_TAG_1, INVALID_TAG)));
    }

    @Test
    public void parseTags_emptyCollection_returnsEmptySet() throws Exception {
        assertTrue(ParserUtilPrescription.parseTags(Collections.emptyList()).isEmpty());
    }

    @Test
    public void parseTags_collectionWithValidTags_returnsTagSet() throws Exception {
        Set<Tag> actualTagSet = ParserUtilPrescription.parseTags(Arrays.asList(VALID_TAG_1, VALID_TAG_2));
        Set<Tag> expectedTagSet = new HashSet<Tag>(Arrays.asList(new Tag(VALID_TAG_1), new Tag(VALID_TAG_2)));

        assertEquals(expectedTagSet, actualTagSet);
    }
    */
}
