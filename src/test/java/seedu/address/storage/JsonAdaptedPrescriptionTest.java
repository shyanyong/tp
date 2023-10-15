package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPrescription.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPrescriptions.ASPIRIN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Stock;

public class JsonAdaptedPrescriptionTest {
    private static final String INVALID_NAME = "@spr!n";
    private static final String INVALID_DOSAGE = "ABCD";
    private static final String INVALID_FREQUENCY = " ";
    private static final String INVALID_START_DATE = "1/1/2023";
    private static final String INVALID_END_DATE = "01/2/2024";
    private static final String INVALID_EXPIRY_DATE = "2024/01/23";
    private static final String INVALID_STOCK = "EFGH";
    private static final String INVALID_NOTE = " ";

    private static final String VALID_NAME = ASPIRIN.getName().toString();
    private static final String VALID_DOSAGE = ASPIRIN.getDosage().toString();
    private static final String VALID_FREQUENCY = ASPIRIN.getFrequency().toString();
    private static final String VALID_START_DATE = ASPIRIN.getStartDate().toString();
    private static final String VALID_END_DATE = ASPIRIN.getEndDate().toString();
    private static final String VALID_EXPIRY_DATE = ASPIRIN.getExpiryDate().toString();
    private static final String VALID_STOCK = ASPIRIN.getTotalStock().toString();
    private static final String VALID_NOTE = ASPIRIN.getNote().toString();

//    private static final List<JsonAdaptedTag> VALID_TAGS = ASPIRIN.getTags().stream()
//        .map(JsonAdaptedTag::new)
//        .collect(Collectors.toList());

    @Test
    public void toModelType_validPrescriptionDetails_returnsPrescription() throws Exception {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(ASPIRIN);
        assertEquals(ASPIRIN, prescription.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            INVALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            null,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidDosage_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            INVALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = Dosage.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullDosage_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            null,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Dosage.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidFrequency_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            INVALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = Frequency.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullFrequency_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            null,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Frequency.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidStartDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            INVALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullStartDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            null,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidEndDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            INVALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullEndDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            null,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidExpiryDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            INVALID_EXPIRY_DATE,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = Date.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullExpiryDate_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            null,
            VALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidStock_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            INVALID_STOCK,
            VALID_NOTE
        );
        String expectedMessage = Stock.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullStock_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            null,
            VALID_NOTE
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Stock.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidNote_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            INVALID_NOTE
        );
        String expectedMessage = Note.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

    @Test
    public void toModelType_nullNote_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_NAME,
            VALID_DOSAGE,
            VALID_FREQUENCY,
            VALID_START_DATE,
            VALID_END_DATE,
            VALID_EXPIRY_DATE,
            VALID_STOCK,
            null
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, prescription::toModelType);
    }

//    @Test
//    public void toModelType_invalidTags_throwsIllegalValueException() {
//        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
//        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
//        JsonAdaptedPrescription prescription =
//            new JsonAdaptedPrescription(VALID_NAME, VALID_DOSAGE, VALID_FREQUENCY, VALID_ADDRESS, invalidTags);
//        assertThrows(IllegalValueException.class, prescription::toModelType);
//    }

}
