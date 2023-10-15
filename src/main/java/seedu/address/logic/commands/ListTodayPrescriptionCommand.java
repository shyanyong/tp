package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelPrescription;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Prescription;

/**
 * Lists all remaining medications to be taken for the day to the user.
 */
public class ListTodayPrescriptionCommand extends CommandPrescription {

    public static final String COMMAND_WORD = "listToday";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all medications to be taken today.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listing all medications to be taken today";
    public static final String MESSAGE_EMPTY_LIST = "No medications to be taken today";

    @Override
    public CommandResult execute(ModelPrescription model) throws CommandException {

        requireNonNull(model);

        Predicate<Prescription> isToday = prescription -> {
            LocalDate today = LocalDate.now();
            LocalDate startDate = LocalDate.parse(
                    prescription.getStartDate().fullDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            LocalDate endDate = LocalDate.parse(
                    prescription.getEndDate().fullDate, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            long daysBetween = ChronoUnit.DAYS.between(startDate, today);

            if (!(today.isAfter(startDate) && today.isBefore(endDate))) {
                return false;
            }
            Frequency frequency = prescription.getFrequency();

            switch (frequency.getFrequency()) {
            case "Daily":
                return true;
            case "Weekly":
                return daysBetween % 7 == 0;
            case "Monthly":
                return daysBetween % 30 == 0;
            default:
                return false;
            }
        };

        model.updateFilteredPrescriptionList(isToday);

        ObservableList<Prescription> todayPrescriptions = model.getFilteredPrescriptionList().filtered(isToday);

        if (todayPrescriptions.isEmpty()) {
            return new CommandResult(MESSAGE_EMPTY_LIST);
        } else {
            return new CommandResult(String.format("%s\n", MESSAGE_SUCCESS));
        }
    }
}
