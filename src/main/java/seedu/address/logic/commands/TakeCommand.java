package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CONSUMPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.Optional;
import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.SameNamePredicate;
import seedu.address.model.prescription.Stock;

/**
 * Represents a command to take a specified number of doses of a prescription.
 */
public class TakeCommand extends Command {
    public static final String COMMAND_WORD = "take";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Takes a specified number of doses of a prescription.\n"
            + "Parameters: "
            + PREFIX_NAME + "medication_name "
            + PREFIX_CONSUMPTION + "number_of_doses \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Aspirin "
            + PREFIX_CONSUMPTION + "2";

    public static final String MESSAGE_SUCCESS = "Doses taken from: %1$s";
    public static final String MESSAGE_PRESCRIPTION_NOT_FOUND = "The specified prescription does not exist.";
    public static final String MESSAGE_INSUFFICIENT_STOCK = "There is insufficient stock for this prescription.";

    private final Name prescriptionName;
    private final int dosesToTake;

    /**
     * Creates a TakePrescriptionCommand to take the specified number of doses from a prescription.
     *
     * @param prescriptionName Name of the prescription.
     * @param dosesToTake      Number of doses to take.
     */
    public TakeCommand(Name prescriptionName, int dosesToTake) {
        this.prescriptionName = prescriptionName;
        this.dosesToTake = dosesToTake;
    }

    /**
     * Executes the TakePrescriptionCommand to take the specified doses from the prescription.
     *
     * @param model The model of the prescription list.
     * @return A CommandResult with the result of the execution.
     * @throws CommandException If there are errors in executing the command.
     */

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Prescription prescription = model.getPrescriptionByName(prescriptionName);
        Optional<Stock> totalStock = prescription.getTotalStock();

        if (totalStock.isPresent() && (Integer.parseInt(totalStock.get().toString()) - dosesToTake < 0)) {
            throw new CommandException(TakeCommand.MESSAGE_INSUFFICIENT_STOCK);
        }

        if (totalStock.isPresent()) {
            totalStock.get().decrementCount(dosesToTake);
        }
        prescription.getConsumptionCount().incrementCount(dosesToTake);

        if (prescription.getDosage().isPresent()
            && Integer.parseInt(prescription.getConsumptionCount().toString())
                >= Integer.parseInt(prescription.getDosage().get().toString())) {
            prescription.setIsCompleted(true);
        }

        Predicate<Prescription> isSameName = new SameNamePredicate(prescriptionName);
        model.updateFilteredPrescriptionList(isSameName);

        return new CommandResult(String.format(MESSAGE_SUCCESS, prescriptionName));
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }

        if (!(other instanceof TakeCommand)) {
            return false;
        }

        TakeCommand otherCommand = (TakeCommand) other;
        return prescriptionName.equals(otherCommand.prescriptionName)
                && dosesToTake == otherCommand.dosesToTake;
    }
}
