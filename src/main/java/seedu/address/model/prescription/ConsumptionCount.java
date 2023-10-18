package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Prescription's consumption count in the prescription list.
 */
public class ConsumptionCount {

    public static final String MESSAGE_CONSTRAINTS =
            "ConsumptionCount should only contain numeric characters, and it should not be blank.";

    public static final String VALIDATION_REGEX = "[0-9]+";
    private String consumptionCount;
    private Boolean isCompleted;

    /**
     * Constructs a {@code ConsumptionCount object}.
     *
     * @param consumptionCount The initial consumption count as a string.
     * @param isCompleted      A flag indicating if the prescription is completed.
     */
    public ConsumptionCount(String consumptionCount, Boolean isCompleted) {
        requireNonNull(isCompleted);
        checkArgument(isValidConsumptionCount(consumptionCount), MESSAGE_CONSTRAINTS);
        this.consumptionCount = consumptionCount;
        this.isCompleted = isCompleted;
    }

    /**
     * Increments the consumption count by the specified amount.
     *
     * @param count The amount to increment the consumption count.
     */
    public void incrementCount(int count) {
        int currentCount = Integer.parseInt(this.consumptionCount);
        currentCount += count;
        this.consumptionCount = Integer.toString(currentCount);
    }

    public void setIsCompleted(Boolean completed) {
        this.isCompleted = completed;
    }
    public void setConsumptionCount(String count) {
        this.consumptionCount = count;
    }

    public String getConsumptionCount() {
        return this.consumptionCount;
    }

    public Boolean getIsCompleted() {
        return this.isCompleted;
    }

    /**
     * Returns true if a given string is a valid dosage.
     */
    public static boolean isValidConsumptionCount(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return consumptionCount;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ConsumptionCount)) {
            return false;
        }

        ConsumptionCount otherConsumptionCount = (ConsumptionCount) other;
        return consumptionCount.equals(otherConsumptionCount.consumptionCount);
    }

    @Override
    public int hashCode() {
        return consumptionCount.hashCode();
    }

}
