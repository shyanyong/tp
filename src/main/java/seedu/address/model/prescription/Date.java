package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import java.time.LocalDate;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Prescription's date in the prescription list.
 * Guarantees: immutable; is valid as declared in {@link #isValidDateFormat(String)}
 */
public class Date {

    public static final String MESSAGE_CONSTRAINTS =
            "Dates should be in the dd/mm/yyyy format, and it should not be blank.";

    public static final String MESSAGE_INVALID_DATE = "Invalid date. Please enter a valid date.";

    /*
     * The first character of the date must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "([0-9]{2})/([0-9]{2})/([0-9]{4})";

    private final String fullDate;

    /**
     * Constructs a {@code Date}.
     *
     * @param date A valid date.
     */
    public Date(String date) {
        requireNonNull(date);
        checkArgument(isValidDateFormat(date), MESSAGE_CONSTRAINTS);
        fullDate = date;
    }

    /**
     * Returns the date in LocalDate format.
     */
    public LocalDate getDate() {
        return LocalDate.parse(fullDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDateFormat(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns true if a given string is a valid date.
     */
    public static boolean isValidDate(int day, int month, int year) {

        if (day < 1 || day > 31) {
            return false;
        }

        if (day > 28) {
            int maxDay = 31;
            switch (month) {
            case 2:
                maxDay = (IsoChronology.INSTANCE.isLeapYear(year) ? 29 : 28);
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                maxDay = 30;
                break;
            default:
            }
            if (day > maxDay) {
                return false;
            }
        }

        if (month > 12 || month < 1) {
            return false;
        }

        if (!(year > 0000)) {
            return false;
        }

        return true;

    }

    @Override
    public String toString() {
        return fullDate;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Date)) {
            return false;
        }

        Date otherDate = (Date) other;
        return fullDate.equals(otherDate.fullDate);
    }

    @Override
    public int hashCode() {
        return fullDate.hashCode();
    }

}
