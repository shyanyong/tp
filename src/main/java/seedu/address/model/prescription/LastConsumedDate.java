package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;


/**
 * Represents a Prescriptions last consumed date in the prescription list.
 */
public class LastConsumedDate {
    private Date lastConsumedDate;
    /**
     * Constructs a {@code LastConsumedDate object}.
     *
     * @param date The initial last consumed date.
     */
    public LastConsumedDate(Date date) {
        requireNonNull(date);
        lastConsumedDate = date;
    }

    public void setLastConsumedDate(Date date) {
        this.lastConsumedDate = date;
    }

    public Date getLastConsumedDate() {
        return this.lastConsumedDate;
    }

    @Override
    public String toString() {
        return lastConsumedDate.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof LastConsumedDate)) {
            return false;
        }

        LastConsumedDate otherDate = (LastConsumedDate) other;

        return lastConsumedDate.equals(otherDate.lastConsumedDate);
    }

    @Override
    public int hashCode() {
        return lastConsumedDate.hashCode();
    }
}
