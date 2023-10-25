package seedu.address.testutil;

import java.util.HashSet;
import java.util.Optional;

import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

/**
 * A utility class to help with building Prescription objects.
 */
public class PrescriptionBuilder {

    public static final Name DEFAULT_NAME = new Name("Aspirin");
    public static final Dosage DEFAULT_DOSAGE = new Dosage("1");
    public static final Frequency DEFAULT_FREQUENCY = new Frequency("Daily");
    public static final Date DEFAULT_START_DATE = new Date("01/10/2023");
    public static final Date DEFAULT_END_DATE = new Date("01/10/2024");
    public static final Date DEFAULT_EXPIRY_DATE = new Date("11/02/2025");
    public static final Stock DEFAULT_STOCK = new Stock("100");
    public static final ConsumptionCount DEFAULT_CONSUMPTION = new ConsumptionCount("0");
    public static final Boolean DEFAULT_ISCOMPLETED = false;
    public static final Note DEFAULT_NOTE = new Note("Take after food");

    private Name name;
    private Optional<Dosage> dosage;
    private Optional<Frequency> frequency;
    private Date startDate;
    private Optional<Date> endDate;
    private Optional<Date> expiryDate;
    private Optional<Stock> totalStock;
    private ConsumptionCount consumptionCount;
    private Boolean isCompleted;
    private Optional<Note> note;
    // private Set<Tag> tags;

    /**
     * Creates a {@code PrescriptionBuilder} with the default details.
     */
    public PrescriptionBuilder() {
        name = DEFAULT_NAME;
        dosage = Optional.ofNullable(DEFAULT_DOSAGE);
        frequency = Optional.ofNullable(DEFAULT_FREQUENCY);
        startDate = DEFAULT_START_DATE;
        endDate = Optional.ofNullable(DEFAULT_END_DATE);
        expiryDate = Optional.ofNullable(DEFAULT_EXPIRY_DATE);
        totalStock = Optional.ofNullable(DEFAULT_STOCK);
        consumptionCount = DEFAULT_CONSUMPTION;
        isCompleted = DEFAULT_ISCOMPLETED;
        note = Optional.ofNullable(DEFAULT_NOTE);
        // tags = new HashSet<>();
    }

    /**
     * Initializes the PrescriptionBuilder with the data of {@code prescriptionToCopy}.
     */
    public PrescriptionBuilder(Prescription prescriptionToCopy) {
        name = prescriptionToCopy.getName();
        dosage = prescriptionToCopy.getDosage();
        frequency = prescriptionToCopy.getFrequency();
        startDate = prescriptionToCopy.getStartDate();
        endDate = prescriptionToCopy.getEndDate();
        expiryDate = prescriptionToCopy.getExpiryDate();
        totalStock = prescriptionToCopy.getTotalStock();
        consumptionCount = prescriptionToCopy.getConsumptionCount();
        isCompleted = prescriptionToCopy.getIsCompleted();
        note = prescriptionToCopy.getNote();
        // tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    // /**
    //  * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Prescription} that we are building.
    //  */
    // public PersonBuilder withTags(String ... tags) {
    //     this.tags = SampleDataUtil.getTagSet(tags);
    //     return this;
    // }

    /**
     * Sets the {@code Dosage} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withDosage(String dosage) {
        this.dosage = Optional.ofNullable(new Dosage(dosage));
        return this;
    }

    /**
     * Sets the {@code Frequency} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withFrequency(String frequency) {
        this.frequency = Optional.ofNullable(new Frequency(frequency));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withStartDate(String startDate) {
        this.startDate = new Date(startDate);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withEndDate(String endDate) {
        this.endDate = Optional.ofNullable(new Date(endDate));
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = Optional.ofNullable(new Date(expiryDate));
        return this;
    }

    /**
     * Sets the {@code Stock} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withStock(String stock) {
        this.totalStock = Optional.ofNullable(new Stock(stock));
        return this;
    }

    /**
     * Sets the {@code Stock} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withConsumptionCount(String consumptionCount) {
        this.consumptionCount = new ConsumptionCount(consumptionCount);
        return this;
    }

    /**
     * Sets the {@code isCompleted} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withIsCompleted(Boolean isCompleted) {
        this.isCompleted = isCompleted;
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withNote(String note) {
        this.note = Optional.ofNullable(new Note(note));
        return this;
    }

    /**
     * Builds the {@code Prescription} with the fields previously set.
     */
    public Prescription build() {
        return new Prescription(name, dosage.orElse(null), frequency.orElse(null),
            startDate, endDate.orElse(null), expiryDate.orElse(null), totalStock.orElse(null),
            consumptionCount, isCompleted, note.orElse(null), new HashSet<>());
    }

}
