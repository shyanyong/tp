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
 * Represents a command to untake a specified number of doses of a prescription.
 */
public class UntakeCommand extends Command {
    public static final String COMMAND_WORD = "untake";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Untakes a specified number of doses of a prescription.\n"
            + "Parameters: "
            + PREFIX_NAME + "medication_name "
            + PREFIX_CONSUMPTION + "number_of_doses \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Aspirin "
            + PREFIX_CONSUMPTION + "2";

    public static final String MESSAGE_SUCCESS = "Doses untaken from: %1$s";
    public static final String MESSAGE_INSUFFICIENT_CONSUMPTION = "Cannot untake more than what you have consumed.";

    private final Name prescriptionName;
    private final int dosesToUntake;

    /**
     * Creates an UntakePrescriptionCommand to untake the specified number of doses from a prescription.
     *
     * @param prescriptionName Name of the prescription.
     * @param dosesToTake      Number of doses to untake.
     */
    public UntakeCommand(Name prescriptionName, int dosesToTake) {
        this.prescriptionName = prescriptionName;
        this.dosesToUntake = dosesToTake;
    }

    /**
     * Executes the UntakePrescriptionCommand to untake the specified doses from the prescription.
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

        if (Integer.parseInt(prescription.getConsumptionCount().getConsumptionCount()) - dosesToUntake < 0) {
            throw new CommandException(MESSAGE_INSUFFICIENT_CONSUMPTION);
        }

        if (totalStock.isPresent()) {
            totalStock.get().incrementCount(dosesToUntake);
        }
        prescription.getConsumptionCount().decrementCount(dosesToUntake);

        if (prescription.getDosage().isPresent()
                && Integer.parseInt(prescription.getConsumptionCount().toString())
                >= Integer.parseInt(prescription.getDosage().get().toString())) {
            prescription.setIsCompleted(true);
        } else {
            prescription.setIsCompleted(false);
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

        if (!(other instanceof UntakeCommand)) {
            return false;
        }

        UntakeCommand otherCommand = (UntakeCommand) other;
        return prescriptionName.equals(otherCommand.prescriptionName)
                && dosesToUntake == otherCommand.dosesToUntake;
    }
}
