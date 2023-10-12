package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's stock in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStock(String)}
 */
public class Stock {

    public static final String MESSAGE_CONSTRAINTS =
            "Stocks should only contain numeric characters, and it should not be blank";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

    public final String fullStock;

    /**
     * Constructs a {@code Stock}.
     *
     * @param stock A valid stock.
     */
    public Stock(String stock) {
        requireNonNull(stock);
        checkArgument(isValidStock(stock), MESSAGE_CONSTRAINTS);
        fullStock = stock;
    }

    /**
     * Returns true if a given string is a valid stock.
     */
    public static boolean isValidStock(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    @Override
    public String toString() {
        return fullStock;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Stock)) {
            return false;
        }

        Stock otherStock = (Stock) other;
        return fullStock.equals(otherStock.fullStock);
    }

    @Override
    public int hashCode() {
        return fullStock.hashCode();
    }

}
