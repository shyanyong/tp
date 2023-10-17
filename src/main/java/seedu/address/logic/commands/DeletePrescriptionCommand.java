package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_INDEX;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.MessagesPrescription;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelPrescription;
import seedu.address.model.prescription.Prescription;

/**
 * Adds a person to the address book.
 */
public class DeletePrescriptionCommand extends CommandPrescription {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a prescription from the prescription list. \n"
            + "Parameters: "
            + PREFIX_INDEX + "index \n"
            // + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_INDEX + "1 ";


    public static final String MESSAGE_PRESCRIPTION_NOT_FOUND = "This prescription does not exist in the prescription "
            + "list";
    public static final String MESSAGE_DELETE_PRESCRIPTION_SUCCESS = "Prescription deleted: %1$s";


    private final Index targetIndex;

    /**
     * Creates an DeletePrescriptionCommand to delete the specified {@code Prescription}
     */
    public DeletePrescriptionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(ModelPrescription model) throws CommandException {
        requireNonNull(model);
        List<Prescription> lastShownList = model.getFilteredPrescriptionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MessagesPrescription.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX);
        }

        Prescription prescriptionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePrescription(prescriptionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
                MessagesPrescription.format(prescriptionToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePrescriptionCommand)) {
            return false;
        }

        DeletePrescriptionCommand otherAddCommand = (DeletePrescriptionCommand) other;
        return targetIndex.equals(otherAddCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toDelete", targetIndex)
                .toString();
    }
}
