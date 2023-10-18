package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.EditCommand.EditPrescriptionDescriptor;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PrescriptionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.prescription.Prescription;
import seedu.address.testutil.EditPrescriptionDescriptorBuilder;
import seedu.address.testutil.PrescriptionBuilder;

public class EditCommandTest {
    private Model model = new ModelManager(getTypicalPrescriptionList(), new UserPrefs());

    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Prescription editedPrescription = new PrescriptionBuilder().build();
        EditPrescriptionDescriptor descriptor = new EditPrescriptionDescriptorBuilder(editedPrescription).build();
        EditCommand editCommand = new EditCommand(INDEX_FIRST_PRESCRIPTION, descriptor);

        String expectedMessage = String.format(EditCommand.MESSAGE_EDIT_PRESCRIPTION_SUCCESS, Messages.format(editedPrescription));

        Model expectedModel = new ModelManager(new PrescriptionList(model.getPrescriptionList()), new UserPrefs());
        expectedModel.setPrescription(model.getFilteredPrescriptionList().get(0), editedPrescription);

        assertCommandSuccess(editCommand, model, expectedMessage, expectedModel);
    }
}
