package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
import seedu.address.model.PrescriptionList;
import seedu.address.model.UserPrefsPrescription;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.SameNamePredicate;
import seedu.address.testutil.PrescriptionBuilder;

public class TakePrescriptionCommandTest {
    private ModelPrescription model;

    @BeforeEach
    public void setUp() {
        model = new ModelManagerPrescription(getTypicalPrescriptionList(), new UserPrefsPrescription());
    }

    @Test
    public void execute_validDosesToTake_success() throws CommandException {
        PrescriptionList prescriptionList = new PrescriptionList();
        Prescription prescriptionToTake = new PrescriptionBuilder()
                .withConsumptionCount("0")
                .withStock("100")
                .build();
        prescriptionList.addPrescription(prescriptionToTake);
        ModelPrescription model = new ModelManagerPrescription(prescriptionList, new UserPrefsPrescription());
        ModelPrescription expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());

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
    public void execute_invalidPrescription_throwsCommandException() {
        Prescription prescriptionToTake = new PrescriptionBuilder().withName("Invalid Name").build();
        int dosesToTake = 1; // A valid number of doses

        TakePrescriptionCommand takePrescriptionCommand = new TakePrescriptionCommand(
                prescriptionToTake.getName(), dosesToTake);

        assertCommandFailure(takePrescriptionCommand, model, TakePrescriptionCommand.MESSAGE_PRESCRIPTION_NOT_FOUND);
    }

    @Test
    public void equals() {
        Name name1 = new Name("Aspirin");
        Name name2 = new Name("Panadol");
        int doses1 = 2;
        int doses2 = 1;

        TakePrescriptionCommand command1 = new TakePrescriptionCommand(name1, doses1);
        TakePrescriptionCommand command2 = new TakePrescriptionCommand(name1, doses1);
        TakePrescriptionCommand command3 = new TakePrescriptionCommand(name2, doses1);
        TakePrescriptionCommand command4 = new TakePrescriptionCommand(name1, doses2);

        // Same object
        assertTrue(command1.equals(command1));

        // Test for equality
        assertTrue(command1.equals(command2)); // Same name and doses
        assertTrue(command2.equals(command1)); // Test for symmetry
        assertFalse(command1.equals(command3)); // Different names
        assertFalse(command1.equals(command4)); // Different doses

        // Test for non-equality
        assertFalse(command1.equals(null)); // Null object
    }
}
