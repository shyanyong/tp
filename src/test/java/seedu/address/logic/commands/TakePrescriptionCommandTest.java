package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.logic.commands.CommandPrescriptionTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandPrescriptionTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.TakePrescriptionCommand.MESSAGE_INSUFFICIENT_STOCK;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.ModelManagerPrescription;
import seedu.address.model.ModelPrescription;
import seedu.address.model.UserPrefsPrescription;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.SameNamePredicate;
import seedu.address.testutil.PrescriptionBuilder;

public class TakePrescriptionCommandTest {
    private ModelPrescription model;
    private ModelPrescription expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManagerPrescription(getTypicalPrescriptionList(), new UserPrefsPrescription());
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());
    }

    @Test
    public void execute_validDosesToTake_success() throws CommandException {
        Prescription prescriptionToTake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());
        int initialStock = Integer.parseInt(prescriptionToTake.getTotalStock().getFullStock());
        int dosesToTake = 1; //Valid number of doses

        TakePrescriptionCommand takePrescriptionCommand = new TakePrescriptionCommand(
                prescriptionToTake.getName(), dosesToTake);

        String expectedMessage = String.format(TakePrescriptionCommand.MESSAGE_SUCCESS,
                                  prescriptionToTake.getName());
        expectedModel.takePrescription(prescriptionToTake.getName(), dosesToTake);
        expectedModel.updateFilteredPrescriptionList(new SameNamePredicate(prescriptionToTake.getName()));

        int newStock = Integer.parseInt(expectedModel.getPrescriptionByName(prescriptionToTake.getName())
                .getTotalStock().getFullStock());

        assertCommandSuccess(takePrescriptionCommand, model, expectedMessage, expectedModel);

        assertEquals(initialStock - dosesToTake, newStock);
    }

    @Test
    public void execute_invalidDosesToTake_throwsCommandException() throws CommandException {
        Prescription prescriptionToTake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialStock = Integer.parseInt(prescriptionToTake.getTotalStock().getFullStock());
        int dosesToTake = -1; // Invalid number of doses

        TakePrescriptionCommand takePrescriptionCommand = new TakePrescriptionCommand(
                prescriptionToTake.getName(), dosesToTake);

        assertCommandFailure(takePrescriptionCommand, model, TakePrescriptionCommand.MESSAGE_INVALID_DOSE);

        // Ensure that the stock is not modified
        int newStock = Integer.parseInt(model.getPrescriptionByName(prescriptionToTake.getName())
                .getTotalStock().getFullStock());
        assertEquals(initialStock, newStock);
    }

    @Test
    public void execute_insufficientStock_throwsCommandException() throws CommandException {
        Prescription prescriptionToTake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialStock = Integer.parseInt(prescriptionToTake.getTotalStock().getFullStock());
        int dosesToTake = initialStock + 1; // More than available stock

        TakePrescriptionCommand takePrescriptionCommand = new TakePrescriptionCommand(
                prescriptionToTake.getName(), dosesToTake);

        assertCommandFailure(takePrescriptionCommand, model, MESSAGE_INSUFFICIENT_STOCK);

        // Ensure that the stock is not modified
        int newStock = Integer.parseInt(model.getPrescriptionByName(prescriptionToTake.getName())
                .getTotalStock().getFullStock());
        assertEquals(initialStock, newStock);
    }

    @Test
    public void execute_invalidPrescription() {
        Prescription prescriptionToTake = new PrescriptionBuilder().withName("Invalid Name").build();
        int dosesToTake = 1; // A valid number of doses

        TakePrescriptionCommand takePrescriptionCommand = new TakePrescriptionCommand(
                prescriptionToTake.getName(), dosesToTake);

        assertCommandFailure(takePrescriptionCommand, model, TakePrescriptionCommand.MESSAGE_PRESCRIPTION_NOT_FOUND);
    }
}
