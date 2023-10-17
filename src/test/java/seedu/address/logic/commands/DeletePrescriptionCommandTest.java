package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandPrescriptionTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandPrescriptionTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandPrescriptionTestUtil.showPrescriptionAtIndex;
import static seedu.address.logic.commands.DeletePrescriptionCommand.MESSAGE_DELETE_PRESCRIPTION_SUCCESS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRESCRIPTION;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePrescriptionCommand}.
 */
public class DeletePrescriptionCommandTest {
    private seedu.address.model.ModelPrescription model;
    private seedu.address.model.ModelPrescription expectedModel;

    @BeforeEach
    public void setUp() {
        model = new seedu.address.model.ModelManagerPrescription(getTypicalPrescriptionList(),
                new seedu.address.model.UserPrefsPrescription());
        expectedModel = new seedu.address.model.ModelManagerPrescription(model.getPrescriptionList(),
                new seedu.address.model.UserPrefsPrescription());
    }
    @Test
    public void execute_validIndexUnfilteredList_success() {
        seedu.address.model.prescription.Prescription prescriptionToDelete = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
                seedu.address.logic.MessagesPrescription.format(prescriptionToDelete));

        expectedModel.deletePrescription(prescriptionToDelete);

        assertCommandSuccess(deletePrescriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPrescriptionList().size() + 1);
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(outOfBoundIndex);

        assertCommandFailure(deletePrescriptionCommand, model,
                seedu.address.logic.MessagesPrescription.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        showPrescriptionAtIndex(model, INDEX_FIRST_PRESCRIPTION);

        seedu.address.model.prescription.Prescription prescriptionToDelete = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(INDEX_FIRST_PRESCRIPTION);

        String expectedMessage = String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
                seedu.address.logic.MessagesPrescription.format(prescriptionToDelete));

        expectedModel.deletePrescription(prescriptionToDelete);
        showNoPrescription(expectedModel);

        assertCommandSuccess(deletePrescriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPrescriptionAtIndex(model, INDEX_FIRST_PRESCRIPTION);

        Index outOfBoundIndex = INDEX_SECOND_PRESCRIPTION;
        // ensures that outOfBoundIndex is still in bounds of address book list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getPrescriptionList().getPrescriptionList().size());

        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(outOfBoundIndex);

        assertCommandFailure(deletePrescriptionCommand, model,
                seedu.address.logic.MessagesPrescription.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeletePrescriptionCommand deleteFirstPrescriptionCommand = new DeletePrescriptionCommand(INDEX_FIRST_PERSON);
        DeletePrescriptionCommand deleteSecondPrescriptionCommand = new DeletePrescriptionCommand(INDEX_SECOND_PERSON);

        // same object -> returns true
        assertTrue(deleteFirstPrescriptionCommand.equals(deleteFirstPrescriptionCommand));

        // same values -> returns true
        DeletePrescriptionCommand deleteFirstPrescriptionCommandCp = new DeletePrescriptionCommand(INDEX_FIRST_PERSON);
        assertTrue(deleteFirstPrescriptionCommand.equals(deleteFirstPrescriptionCommandCp));

        // different types -> returns false
        assertFalse(deleteFirstPrescriptionCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstPrescriptionCommand.equals(null));

        // different person -> returns false
        assertFalse(deleteFirstPrescriptionCommand.equals(deleteSecondPrescriptionCommand));
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(targetIndex);
        String expected = DeletePrescriptionCommand.class.getCanonicalName() + "{toDelete=" + targetIndex + "}";
        assertEquals(expected, deletePrescriptionCommand.toString());
    }

    /**
     * Updates {@code model}'s filtered list to show no one.
     */
    private void showNoPrescription(seedu.address.model.ModelPrescription model) {
        model.updateFilteredPrescriptionList(p -> false);

        assertTrue(model.getFilteredPrescriptionList().isEmpty());
    }
}
