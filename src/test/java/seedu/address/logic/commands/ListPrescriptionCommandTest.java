package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandPrescriptionTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandPrescriptionTestUtil.showPrescriptionAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PRESCRIPTION;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ModelManagerPrescription;
import seedu.address.model.ModelPrescription;
import seedu.address.model.UserPrefsPrescription;

/**
 * Contains integration tests (interaction with the seedu.address.model.ModelPrescription)
 * and unit tests for ListPrescriptionCommand.
 */
public class ListPrescriptionCommandTest {

    private ModelPrescription model;
    private ModelPrescription expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManagerPrescription(getTypicalPrescriptionList(), new UserPrefsPrescription());
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(), new UserPrefsPrescription());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPrescriptionCommand(), model,
            ListPrescriptionCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPrescriptionAtIndex(model, INDEX_FIRST_PRESCRIPTION);
        assertCommandSuccess(new ListPrescriptionCommand(), model,
            ListPrescriptionCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
