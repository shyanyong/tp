package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.CompletedPrescriptions.getCompletedPrescriptionList;
import static seedu.address.testutil.TypicalPrescriptions.IBUPROFEN;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.prescription.Prescription;
import seedu.address.testutil.PrescriptionBuilder;


/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalPrescriptionList(), getCompletedPrescriptionList(), new UserPrefs());
    }

    @Test
    public void execute_newPrescription_success() {
        Prescription validPrescription = new PrescriptionBuilder().withName("Methadone").build();

        Model expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.addPrescription(validPrescription);

        assertCommandSuccess(new AddCommand(validPrescription), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPrescription)),
                expectedModel);
    }

    // Even if prescription to be added conflicts with an existing prescription, it should still be added.
    @Test
    public void execute_newPrescriptionConflictingDrug_success() {
        Prescription validPrescription = IBUPROFEN;

        Model expectedModel = new ModelManager(model.getPrescriptionList(),
                model.getCompletedPrescriptionList(), new UserPrefs());
        expectedModel.addPrescription(validPrescription);

        assertCommandSuccess(new AddCommand(validPrescription), model,
                String.format(AddCommand.MESSAGE_DRUG_CLASH
                        + AddCommand.MESSAGE_SUCCESS, Messages.format(validPrescription)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePrescription_throwsCommandException() {
        Prescription prescriptionInList = model.getPrescriptionList().getPrescriptionList().get(0);
        assertCommandFailure(new AddCommand(prescriptionInList), model,
                AddCommand.MESSAGE_DUPLICATE_PRESCRIPTION);
    }

}
