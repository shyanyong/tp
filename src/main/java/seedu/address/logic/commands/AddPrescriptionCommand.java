package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_END_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_EXPIRY_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_NOTE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_START_DATE;
import static seedu.address.logic.parser.CliSyntaxPrescription.PREFIX_TOTAL_STOCK;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.MessagesPrescription;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelPrescription;
import seedu.address.model.prescription.Prescription;

/**
 * Adds a person to the address book.
 */
public class AddPrescriptionCommand extends CommandPrescription {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a prescription to the prescription list. \n"
            + "Parameters: "
            + PREFIX_NAME + "medication_name "
            + PREFIX_DOSAGE + "dosage "
            + PREFIX_FREQUENCY + "frequency "
            + PREFIX_START_DATE + "start_date "
            + PREFIX_END_DATE + "end_date "
            + PREFIX_EXPIRY_DATE + "expiry_date "
            + PREFIX_TOTAL_STOCK + "total_stock "
            + PREFIX_NOTE + "note \n"
            // + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Aspirin "
            + PREFIX_DOSAGE + "1 "
            + PREFIX_FREQUENCY + "Daily "
            + PREFIX_START_DATE + "01/08/2023 "
            + PREFIX_END_DATE + "25/12/2023 "
            + PREFIX_EXPIRY_DATE + "01/01/2024 "
            + PREFIX_TOTAL_STOCK + "100 "
            + PREFIX_NOTE + "Test note";

    public static final String MESSAGE_SUCCESS = "New prescription added: %1$s";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "This prescription already "
            + "exists in the prescription list";

    private final Prescription toAdd;

    /**
     * Creates an AddPrescriptionCommand to add the specified {@code Prescription}
     */
    public AddPrescriptionCommand(Prescription prescription) {
        requireNonNull(prescription);
        toAdd = prescription;
    }

    @Override
    public CommandResult execute(ModelPrescription model) throws CommandException {
        requireNonNull(model);

        if (model.hasPrescription(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRESCRIPTION);
        }

        model.addPrescription(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, MessagesPrescription.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddPrescriptionCommand)) {
            return false;
        }

        AddPrescriptionCommand otherAddCommand = (AddPrescriptionCommand) other;
        return toAdd.equals(otherAddCommand.toAdd);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("toAdd", toAdd)
                .toString();
    }
}
