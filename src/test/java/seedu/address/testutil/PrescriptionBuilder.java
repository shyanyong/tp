package seedu.address.testutil;

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
    public static final Note DEFAULT_NOTE = new Note("Take after food");

    private Name name;
    private Dosage dosage;
    private Frequency frequency;
    private Date startDate;
    private Date endDate;
    private Date expiryDate;
    private Stock totalStock;
    private Note note;
    // private Set<Tag> tags;

    /**
     * Creates a {@code PrescriptionBuilder} with the default details.
     */
    public PrescriptionBuilder() {
        name = DEFAULT_NAME;
        dosage = DEFAULT_DOSAGE;
        frequency = DEFAULT_FREQUENCY;
        startDate = DEFAULT_START_DATE;
        endDate = DEFAULT_END_DATE;
        expiryDate = DEFAULT_EXPIRY_DATE;
        totalStock = DEFAULT_STOCK;
        note = DEFAULT_NOTE;
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
        this.dosage = new Dosage(dosage);
        return this;
    }

    /**
     * Sets the {@code Frequency} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withFrequency(String frequency) {
        this.frequency = new Frequency(frequency);
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
        this.endDate = new Date(endDate);
        return this;
    }

    /**
     * Sets the {@code Date} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withExpiryDate(String expiryDate) {
        this.expiryDate = new Date(expiryDate);
        return this;
    }

    /**
     * Sets the {@code Stock} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withStock(String stock) {
        this.totalStock = new Stock(stock);
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code Prescription} that we are building.
     */
    public PrescriptionBuilder withNote(String note) {
        this.note = new Note(note);
        return this;
    }

    public Prescription build() {
        return new Prescription(name, dosage, frequency, startDate, endDate, expiryDate, totalStock, note);
    }

}
