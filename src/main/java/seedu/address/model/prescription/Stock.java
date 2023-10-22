package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's stock in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidStock(String)}
 */
public class Stock {

    public static final String MESSAGE_CONSTRAINTS = "Stocks should be a non-blank, "
            + "positive integer with numeric characters only, "
            + "and smaller than the maximum possible integer value (2,147,483,647).";

    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[0-9]+";

    private String fullStock;

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
        if (!test.matches(VALIDATION_REGEX)) {
            return false;
        }

        try {
            int stockValue = Integer.parseInt(test);
            return (stockValue >= 0);
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void setFullStock(String fullStock) {
        this.fullStock = fullStock;
    }

    /**
     * increment the count of stock.
     * @param count The count to increment by.
     */
    public void incrementCount(int count) {
        int newStock = Integer.parseInt(this.fullStock) + count;
        this.fullStock = Integer.toString(newStock);
    }

    /**
     * Decrements the count of stock.
     * @param count The count to decrement by.
     */
    public void decrementCount(int count) {
        int newStock = Integer.parseInt(this.fullStock) - count;
        this.fullStock = Integer.toString(newStock);
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
