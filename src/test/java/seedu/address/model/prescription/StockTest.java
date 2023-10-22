package seedu.address.model.prescription;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class StockTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Stock(null));
    }

    @Test
    public void constructor_invalidStock_throwsIllegalArgumentException() {
        String invalidStock = "";
        assertThrows(IllegalArgumentException.class, () -> new Stock(invalidStock));
    }

    @Test
    public void isValidStock() {
        // null stock
        assertThrows(NullPointerException.class, () -> Stock.isValidStock(null));

        // invalid stock
        assertFalse(Stock.isValidStock("")); // empty string
        assertFalse(Stock.isValidStock(" ")); // spaces only
        assertFalse(Stock.isValidStock("^")); // non-alphanumeric characters
        assertFalse(Stock.isValidStock("a")); // alphabets
        assertFalse((Stock.isValidStock("1.2"))); // decimal number
        assertFalse((Stock.isValidStock("-1"))); // negative number
        assertFalse((Stock.isValidStock("2147483648"))); // greater than max int value

        // valid stock
        assertTrue(Stock.isValidStock("1")); // single digit
        assertTrue(Stock.isValidStock("12")); // double digit
        assertTrue(Stock.isValidStock("02")); // trailing zero
    }

    @Test
    public void equals() {
        Stock stock = new Stock("100");

        // same values -> returns true
        assertTrue(stock.equals(new Stock("100")));

        // same object -> returns true
        assertTrue(stock.equals(stock));

        // null -> returns false
        assertFalse(stock.equals(null));

        // different types -> returns false
        assertFalse(stock.equals(5.0f));

        // different values -> returns false
        assertFalse(stock.equals(new Stock("200")));
    }
}
