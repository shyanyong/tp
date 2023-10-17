package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ConsumptionCountTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConsumptionCount(null, false));
    }

    @Test
    public void constructor_invalidConsumptionCount_throwsIllegalArgumentException() {
        String invalidConsumptionCount = "";
        assertThrows(IllegalArgumentException.class, () ->
                new ConsumptionCount(invalidConsumptionCount, false));
    }

    @Test
    public void constructor_invalidIsCompleted_throwsIllegalArgumentException() {
        String invalidIsCompleted = "";
        assertThrows(IllegalArgumentException.class, () -> new ConsumptionCount(invalidIsCompleted, false));
    }

    @Test
    public void isValidConsumptionCount() {
        // null dosage
        assertThrows(NullPointerException.class, () -> ConsumptionCount.isValidConsumptionCount(null));

        // invalid dosage
        assertFalse(ConsumptionCount.isValidConsumptionCount("")); // empty string
        assertFalse(ConsumptionCount.isValidConsumptionCount(" ")); // spaces only
        assertFalse(ConsumptionCount.isValidConsumptionCount("^")); // non-alphanumeric characters
        assertFalse(ConsumptionCount.isValidConsumptionCount("a")); // alphabets

        // valid dosage
        assertTrue(ConsumptionCount.isValidConsumptionCount("1")); // single digit
        assertTrue(ConsumptionCount.isValidConsumptionCount("12")); // double digit
        assertTrue(ConsumptionCount.isValidConsumptionCount("02")); // trailing zero
    }

    @Test
    public void incrementCount() {
        ConsumptionCount consumptionCount = new ConsumptionCount("0", false);

        // Test incrementing
        consumptionCount.incrementCount(1);
        assertTrue(consumptionCount.getConsumptionCount().equals("1"));

        // Test incrementing with a larger number
        consumptionCount.incrementCount(5);
        assertTrue(consumptionCount.getConsumptionCount().equals("6"));

    }

    @Test
    public void setIsCompleted() {
        ConsumptionCount consumptionCount = new ConsumptionCount("0", false);

        // Test setIsCompleted
        consumptionCount.setIsCompleted(true);
        assertTrue(consumptionCount.getIsCompleted());
    }

    @Test
    public void equals() {
        ConsumptionCount consumptionCount = new ConsumptionCount("0", false);

        // same values -> returns true
        assertTrue(consumptionCount.equals(new ConsumptionCount("0", false)));

        // same object -> returns true
        assertTrue(consumptionCount.equals(consumptionCount));

        // null -> returns false
        assertFalse(consumptionCount.equals(null));

        // different types -> returns false
        assertFalse(consumptionCount.equals(5.0f));

        // different values -> returns false
        assertFalse(consumptionCount.equals(new ConsumptionCount("1", true)));

        // Add more test cases for equals as needed
    }
}
