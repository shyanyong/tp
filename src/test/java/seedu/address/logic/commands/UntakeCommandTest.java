package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.UntakeCommand.MESSAGE_INSUFFICIENT_CONSUMPTION;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PRESCRIPTION;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.PrescriptionList;
import seedu.address.model.UserPrefs;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.SameNamePredicate;
import seedu.address.testutil.PrescriptionBuilder;

public class UntakeCommandTest {
    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
    }

    @Test
    public void execute_validDosesToUntakeCompleted_success() throws CommandException {
        PrescriptionList prescriptionList = new PrescriptionList();
        PrescriptionList completedPrescriptionList = new PrescriptionList();
        Prescription prescription = new PrescriptionBuilder()
                .withDosage("50")
                .withConsumptionCount("100")
                .withStock("100")
                .build();
        prescriptionList.addPrescription(prescription);
        Model model = new ModelManager(prescriptionList, completedPrescriptionList, new UserPrefs());
        Model expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());

        Prescription prescriptionToUntake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialStock = Integer.parseInt(prescriptionToUntake.getTotalStock().get().toString());
        Dosage dosesToUntake = new Dosage("1"); //Valid number of doses
        int dosesToUntakeInt = Integer.parseInt(dosesToUntake.toString());

        UntakeCommand untakePrescriptionCommand = new UntakeCommand(
                INDEX_FIRST_PRESCRIPTION, dosesToUntake);

        String expectedMessage = String.format(UntakeCommand.MESSAGE_SUCCESS,
                prescriptionToUntake.getName());
        Prescription expectedPrescription = expectedModel.getPrescriptionByName(prescriptionToUntake.getName());
        expectedPrescription.getTotalStock().get().incrementCount(dosesToUntakeInt);
        expectedPrescription.getConsumptionCount().decrementCount(dosesToUntakeInt);
        expectedModel.updateFilteredPrescriptionList(new SameNamePredicate(prescriptionToUntake.getName()));

        int newStock = Integer.parseInt(expectedModel.getPrescriptionByName(prescriptionToUntake.getName())
                .getTotalStock().get().toString());

        assertCommandSuccess(untakePrescriptionCommand, model, expectedMessage, expectedModel);
        assertTrue(prescriptionToUntake.getIsCompleted());
        assertEquals(initialStock + dosesToUntakeInt, newStock);
    }

    @Test
    public void execute_validDosesToUntakeIncomplete_success() throws CommandException {
        PrescriptionList prescriptionList = new PrescriptionList();
        PrescriptionList completedPrescriptionList = new PrescriptionList();
        Prescription prescription = new PrescriptionBuilder()
                .withDosage("200")
                .withConsumptionCount("100")
                .withStock("100")
                .build();
        prescriptionList.addPrescription(prescription);
        Model model = new ModelManager(prescriptionList, completedPrescriptionList, new UserPrefs());
        Model expectedModel = new ModelManager(model.getPrescriptionList(), model.getCompletedPrescriptionList(),
                new UserPrefs());

        Prescription prescriptionToUntake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialStock = Integer.parseInt(prescriptionToUntake.getTotalStock().get().toString());
        Dosage dosesToUntake = new Dosage("1"); //Valid number of doses
        int dosesToUntakeInt = Integer.parseInt(dosesToUntake.toString());

        UntakeCommand untakePrescriptionCommand = new UntakeCommand(
                INDEX_FIRST_PRESCRIPTION, dosesToUntake);

        String expectedMessage = String.format(UntakeCommand.MESSAGE_SUCCESS,
                prescriptionToUntake.getName());
        Prescription expectedPrescription = expectedModel.getPrescriptionByName(prescriptionToUntake.getName());
        expectedPrescription.getTotalStock().get().incrementCount(dosesToUntakeInt);
        expectedPrescription.getConsumptionCount().decrementCount(dosesToUntakeInt);
        expectedModel.updateFilteredPrescriptionList(new SameNamePredicate(prescriptionToUntake.getName()));

        int newStock = Integer.parseInt(expectedModel.getPrescriptionByName(prescriptionToUntake.getName())
                .getTotalStock().get().toString());

        assertCommandSuccess(untakePrescriptionCommand, model, expectedMessage, expectedModel);
        assertFalse(prescriptionToUntake.getIsCompleted());
        assertEquals(initialStock + dosesToUntakeInt, newStock);
    }

    @Test
    public void execute_insufficientConsumption_throwsCommandException() throws CommandException {
        Prescription prescriptionToUntake = model.getFilteredPrescriptionList()
                .get(INDEX_FIRST_PRESCRIPTION.getZeroBased());

        int initialConsumption = Integer.parseInt(prescriptionToUntake.getConsumptionCount().toString());
        Dosage dosesToUntake = new Dosage(String.valueOf(initialConsumption + 1)); // More than currently consumed

        UntakeCommand untakePrescriptionCommand = new UntakeCommand(
                INDEX_FIRST_PRESCRIPTION, dosesToUntake);

        assertCommandFailure(untakePrescriptionCommand, model, MESSAGE_INSUFFICIENT_CONSUMPTION);

        // Ensure that the consumption is not modified
        int newConsumption = Integer.parseInt(model.getPrescriptionByName(prescriptionToUntake.getName())
                .getConsumptionCount().toString());
        assertEquals(initialConsumption, newConsumption);
    }

    @Test
    public void equals() {
        Dosage doses1 = new Dosage("2");
        Dosage doses2 = new Dosage("1");

        UntakeCommand command1 = new UntakeCommand(INDEX_FIRST_PRESCRIPTION, doses1);
        UntakeCommand command2 = new UntakeCommand(INDEX_FIRST_PRESCRIPTION, doses1);
        UntakeCommand command3 = new UntakeCommand(INDEX_SECOND_PRESCRIPTION, doses1);
        UntakeCommand command4 = new UntakeCommand(INDEX_FIRST_PRESCRIPTION, doses2);

        // Same object
        assertTrue(command1.equals(command1));

        // Test for equality
        assertTrue(command1.equals(command2)); // Same name and doses
        assertTrue(command2.equals(command1)); // Test for symmetry
        assertFalse(command1.equals(command3)); // Different index
        assertFalse(command1.equals(command4)); // Different doses

        // Test for non-equality
        assertFalse(command1.equals(null)); // Null object
    }
}
