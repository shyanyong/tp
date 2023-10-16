package seedu.address.storage;

// import java.util.ArrayList;
// import java.util.HashSet;
// import java.util.List;
// import java.util.Set;
// import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

/**
 * Jackson-friendly version of {@link Prescription}.
 */
class JsonAdaptedPrescription {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Prescription's %s field is missing!";

    private final String name;
    private final String dosage;
    private final String frequency;
    private final String startDate;
    private final String endDate;
    private final String expiryDate;
    private final String totalStock;
    private final String consumptionCount;
    private final Boolean isCompleted;
    private final String note;
    // private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPrescription} with the given prescription details.
     */
    @JsonCreator
    public JsonAdaptedPrescription(@JsonProperty("name") String name, @JsonProperty("dosage") String dosage,
            @JsonProperty("frequency") String frequency, @JsonProperty("startDate") String startDate,
            @JsonProperty("endDate") String endDate, @JsonProperty("expiryDate") String expiryDate,
            @JsonProperty("totalStock") String totalStock, @JsonProperty("consumptionCount") String consumptionCount,
                                   @JsonProperty("isCompleted") Boolean isCompleted,
                                   @JsonProperty("note") String note) {
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expiryDate = expiryDate;
        this.totalStock = totalStock;
        this.consumptionCount = consumptionCount;
        this.isCompleted = isCompleted;
        this.note = note;
        // if (tags != null) {
        //     this.tags.addAll(tags);
        // }
    }

    /**
     * Converts a given {@code Prescription} into this class for Jackson use.
     */
    public JsonAdaptedPrescription(Prescription source) {
        name = source.getName().fullName;
        dosage = source.getDosage().fullDosage;
        frequency = source.getFrequency().fullFrequency;
        startDate = source.getStartDate().fullDate;
        endDate = source.getEndDate().fullDate;
        expiryDate = source.getExpiryDate().fullDate;
        totalStock = source.getTotalStock().getFullStock();
        consumptionCount = source.getConsumptionCount().getConsumptionCount();
        isCompleted = source.getConsumptionCount().getIsCompleted();
        note = source.getNote().fullNote;
        // tags.addAll(source.getTags().stream()
        //         .map(JsonAdaptedTag::new)
        //         .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted prescription object into the model's {@code Prescription} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted prescription.
     */
    public Prescription toModelType() throws IllegalValueException {
        // final List<Tag> personTags = new ArrayList<>();
        // for (JsonAdaptedTag tag : tags) {
        //     personTags.add(tag.toModelType());
        // }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (dosage == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Dosage.class.getSimpleName()));
        }
        if (!Dosage.isValidDosage(dosage)) {
            throw new IllegalValueException(Dosage.MESSAGE_CONSTRAINTS);
        }
        final Dosage modelDosage = new Dosage(dosage);

        if (frequency == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Frequency.class.getSimpleName()));
        }
        if (!Frequency.isValidFrequency(frequency)) {
            throw new IllegalValueException(Frequency.MESSAGE_CONSTRAINTS);
        }
        final Frequency modelFrequency = new Frequency(frequency);

        if (startDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(startDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelStartDate = new Date(startDate);

        if (endDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(expiryDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelEndDate = new Date(endDate);

        if (expiryDate == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Date.class.getSimpleName()));
        }
        if (!Date.isValidDate(expiryDate)) {
            throw new IllegalValueException(Date.MESSAGE_CONSTRAINTS);
        }
        final Date modelExpiryDate = new Date(expiryDate);

        if (totalStock == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Stock.class.getSimpleName()));
        }
        if (!Stock.isValidStock(totalStock)) {
            throw new IllegalValueException(Stock.MESSAGE_CONSTRAINTS);
        }
        final Stock modelTotalStock = new Stock(totalStock);

        if (consumptionCount == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ConsumptionCount"));
        }

        final ConsumptionCount modelConsumptionCount = new ConsumptionCount(consumptionCount, isCompleted);

        if (note == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Note.class.getSimpleName()));
        }
        if (!Note.isValidNote(note)) {
            throw new IllegalValueException(Note.MESSAGE_CONSTRAINTS);
        }
        final Note modelNote = new Note(note);

        // final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Prescription(modelName, modelDosage, modelFrequency, modelStartDate,
                        modelEndDate, modelExpiryDate, modelTotalStock, modelConsumptionCount, modelNote);
    }

}
