package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.ModelPrescription.PREDICATE_SHOW_ALL_PRESCRIPTIONS;

import seedu.address.model.ModelPrescription;

/**
 * Lists all prescriptions stored to the user.
 */
public class ListPrescriptionCommand extends CommandPrescription {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all prescriptions stored.\n"
        + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all prescriptions";

    @Override
    public CommandResult execute(ModelPrescription model) {
        requireNonNull(model);
        model.updateFilteredPrescriptionList(PREDICATE_SHOW_ALL_PRESCRIPTIONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
