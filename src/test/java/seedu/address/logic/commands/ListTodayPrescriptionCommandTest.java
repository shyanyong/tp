package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandPrescriptionTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.ModelManagerPrescription;
import seedu.address.model.ModelPrescription;
import seedu.address.model.PrescriptionList;
import seedu.address.model.UserPrefsPrescription;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.IsTodayPredicate;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

public class ListTodayPrescriptionCommandTest {
    private ModelPrescription model;
    private ModelPrescription expectedModel;
    @BeforeEach
    public void setUp() {
        model = new ModelManagerPrescription(getTypicalPrescriptionList(), new UserPrefsPrescription());
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());
    }

    @Test
    public void execute_validDateWithMedications_listTodaySuccess() {
        String expectedMessage = ListTodayPrescriptionCommand.MESSAGE_SUCCESS;
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayPrescriptionCommand(), model, expectedMessage, expectedModel);
    }

    @Test public void execute_noMedicationsForToday_listTodayEmpty() {
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        String expectedMessage = ListTodayPrescriptionCommand.MESSAGE_EMPTY_LIST;
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayPrescriptionCommand(), model, expectedMessage, expectedModel);
    }

    @Test public void execute_validWeeklyMedicationsForToday_listTodaySucess() {
        String expectedMessage = ListTodayPrescriptionCommand.MESSAGE_SUCCESS;
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(7);
        LocalDate endLocalDate = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Weekly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1", false), new Note("test")));
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayPrescriptionCommand(), model, expectedMessage, expectedModel);
    }

    @Test public void execute_invalidWeeklyMedicationsForToday_listTodayEmpty() {
        String expectedMessage = ListTodayPrescriptionCommand.MESSAGE_EMPTY_LIST;
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(6);
        LocalDate endLocalDate = LocalDate.now().plusDays(7);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Weekly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1", false), new Note("test")));
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayPrescriptionCommand(), model, expectedMessage, expectedModel);
    }

    @Test public void execute_validMonthlyMedicationsForToday_listTodaySucess() {
        String expectedMessage = ListTodayPrescriptionCommand.MESSAGE_SUCCESS;
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(30);
        LocalDate endLocalDate = LocalDate.now().plusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Monthly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1", false), new Note("test")));
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayPrescriptionCommand(), model, expectedMessage, expectedModel);
    }

    @Test public void execute_invalidMonthlyMedicationsForToday_listTodayEmpty() {
        String expectedMessage = ListTodayPrescriptionCommand.MESSAGE_EMPTY_LIST;
        model.setPrescriptionList(new PrescriptionList());
        assertTrue(model.getFilteredPrescriptionList().isEmpty());

        LocalDate startLocalDate = LocalDate.now().minusDays(29);
        LocalDate endLocalDate = LocalDate.now().plusDays(30);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String startDateString = startLocalDate.format(formatter);
        String endDateString = endLocalDate.format(formatter);

        Date startDate = new Date(startDateString);
        Date endDate = new Date(endDateString);
        model.addPrescription(new Prescription(new Name("Paracetamol"), new Dosage("1"), new Frequency("Monthly"),
                startDate, endDate, endDate, new Stock("100"),
                new ConsumptionCount("1", false), new Note("test")));
        expectedModel = new ModelManagerPrescription(model.getPrescriptionList(),
                new UserPrefsPrescription());
        expectedModel.updateFilteredPrescriptionList(new IsTodayPredicate());
        assertCommandSuccess(new ListTodayPrescriptionCommand(), model, expectedMessage, expectedModel);
    }

}
