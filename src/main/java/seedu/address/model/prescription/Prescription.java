package seedu.address.model.prescription;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

// import java.util.Collections;
// import java.util.HashSet;
import java.util.Objects;
// import java.util.Set;

import seedu.address.commons.util.ToStringBuilder;
// import seedu.address.model.tag.Tag;

/**
 * Represents a Prescription in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Prescription {

    // Identity fields
    private final Name name;

    // Data fields
    private final Dosage dosage;
    private final Frequency frequency;
    private final Date startDate;
    private final Date endDate;
    private final Date expiryDate;
    private final Stock totalStock;
    private final ConsumptionCount consumptionCount;
    private final Note note;
    // private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Prescription(Name name, Dosage dosage, Frequency frequency, Date startDate,
                        Date endDate, Date expiryDate, Stock totalStock, Note note) {
        requireAllNonNull(name);
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.startDate = startDate;
        this.endDate = endDate;
        this.expiryDate = expiryDate;
        this.totalStock = totalStock;
        this.consumptionCount = new ConsumptionCount("0", false);
        this.note = note;
    }

    public Name getName() {
        return name;
    }

    public Dosage getDosage() {
        return dosage;
    }

    public Frequency getFrequency() {
        return frequency;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public Stock getTotalStock() {
        return totalStock;
    }
    public ConsumptionCount getConsumptionCount() {
        return consumptionCount;
    }

    public Note getNote() {
        return note;
    }

    // /
    //  * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
    //  * if modification is attempted.
    //  */
    // public Set<Tag> getTags() {
    //     return Collections.unmodifiableSet(tags);
    // }

    /**
     * Returns true if both prescriptions have the same name.
     * This defines a weaker notion of equality between two prescriptions.
     */
    public boolean isSamePrescription(Prescription otherPrescription) {
        if (otherPrescription == this) {
            return true;
        }

        return otherPrescription != null
                && otherPrescription.getName().equals(getName())
                && otherPrescription.getStartDate().equals(getStartDate());
    }

    /**
     * Returns true if both prescriptions have the same identity and data fields.
     * This defines a stronger notion of equality between two prescriptions.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Prescription)) {
            return false;
        }

        Prescription otherPrescription = (Prescription) other;
        return name.equals(otherPrescription.name)
                && dosage.equals(otherPrescription.dosage)
                && frequency.equals(otherPrescription.frequency)
                && startDate.equals(otherPrescription.startDate)
                && endDate.equals(otherPrescription.endDate)
                && expiryDate.equals(otherPrescription.expiryDate)
                && totalStock.equals(otherPrescription.totalStock)
                && consumptionCount.equals(otherPrescription.consumptionCount)
                && note.equals(otherPrescription.note);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, dosage, frequency, startDate,
                endDate, expiryDate, totalStock, consumptionCount, note);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("dosage", dosage)
                .add("frequency", frequency)
                .add("startDate", startDate)
                .add("endDate", endDate)
                .add("expiryDate", expiryDate)
                .add("totalStock", totalStock)
                .add("consumptionCount", consumptionCount)
                .add("note", note)
                .toString();
    }
}