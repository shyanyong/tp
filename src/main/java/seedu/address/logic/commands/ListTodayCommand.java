package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.IsTodayPredicate;
import seedu.address.model.prescription.Prescription;

/**
 * Lists all remaining medications to be taken for the day to the user.
 */
public class ListTodayCommand extends Command {

    public static final String COMMAND_WORD = "listToday";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all medications to be taken today.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listing all medications to be taken today.";
    public static final String MESSAGE_EMPTY_LIST = "No medications to be taken today.";

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Predicate<Prescription> isToday = new IsTodayPredicate();
        model.updateFilteredPrescriptionList(isToday);

        ObservableList<Prescription> todayPrescriptions = model.getFilteredPrescriptionList();
        if (todayPrescriptions.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST, COMMAND_WORD);
        } else {
            return new CommandResult(MESSAGE_SUCCESS, COMMAND_WORD);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other instanceof ListTodayCommand;
    }
}
