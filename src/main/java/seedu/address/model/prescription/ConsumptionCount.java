package seedu.address.model.prescription;

/**
 * Represents a Prescription's consumption count in the prescription list.
 */
public class ConsumptionCount {

    public String consumptionCount;
    public Boolean isCompleted;

    /**
     * Constructs a {@code ConsumptionCount object}.
     *
     * @param consumptionCount The initial consumption count as a string.
     * @param isCompleted      A flag indicating if the prescription is completed.
     */
    public ConsumptionCount(String consumptionCount, Boolean isCompleted) {
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