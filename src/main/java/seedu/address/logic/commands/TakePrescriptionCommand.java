package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_CONSUMPTION;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NAME;

import java.util.function.Predicate;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelPrescription;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.SameNamePredicate;

/**
 * Represents a command to take a specified number of doses of a prescription.
 */
public class TakePrescriptionCommand extends CommandPrescription {
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
    public static final String MESSAGE_INVALID_DOSE = "Please specify a valid number of doses to take.";
    public static final String MESSAGE_INSUFFICIENT_STOCK = "There is insufficient stock for this prescription.";

    private final Name prescriptionName;
    private final int dosesToTake;

    /**
     * Creates a TakePrescriptionCommand to take the specified number of doses from a prescription.
     *
     * @param prescriptionName Name of the prescription.
     * @param dosesToTake      Number of doses to take.
     */
    public TakePrescriptionCommand(Name prescriptionName, int dosesToTake) {
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
    public CommandResult execute(ModelPrescription model) throws CommandException {
        requireNonNull(model);

        Prescription prescription = model.getPrescriptionByName(prescriptionName);
        int totalStock = Integer.parseInt(prescription.getTotalStock().getFullStock());

        if (dosesToTake <= 0) {
            throw new CommandException(MESSAGE_INVALID_DOSE);
        }

        if (totalStock - dosesToTake < 0) {
            throw new CommandException(TakePrescriptionCommand.MESSAGE_INSUFFICIENT_STOCK);
        }

        Predicate<Prescription> isSameName = new SameNamePredicate(prescriptionName);

        model.takePrescription(prescriptionName, dosesToTake);
        model.updateFilteredPrescriptionList(isSameName);

        return new CommandResult(String.format(MESSAGE_SUCCESS, prescriptionName));
    }
}
