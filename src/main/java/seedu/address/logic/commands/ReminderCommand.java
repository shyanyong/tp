package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.IsAboutToExpirePredicate;
import seedu.address.model.prescription.IsLowInStockPredicate;
import seedu.address.model.prescription.Prescription;

/**
 * Lists all medications that are about to expire and are low in stock.
 */
public class ReminderCommand extends Command {
    public static final String COMMAND_WORD = "reminder";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Lists all medications that are about to expire and are low in stock.\n"
            + "Example: " + COMMAND_WORD;
    public static final String MESSAGE_SUCCESS =
            "Listing all medications that are about to expire and are low in stock.";
    public static final String MESSAGE_EMPTY_LIST = "No reminders.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Predicate<Prescription> isAboutToExpire = new IsAboutToExpirePredicate();
        Predicate<Prescription> isLowInStock = new IsLowInStockPredicate();
        Predicate<Prescription> isAboutToExpireAndLowInStock = isAboutToExpire.or(isLowInStock);
        model.updateFilteredPrescriptionList(isAboutToExpireAndLowInStock);

        ObservableList<Prescription> reminderPrescriptions = model.getFilteredPrescriptionList();
        if (reminderPrescriptions.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        } else {
            return new CommandResult(MESSAGE_SUCCESS);
        }
    }
}
