package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalPrescriptionList;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.UserPrefsPrescription;

public class StorageManagerPrescriptionTest {

    @TempDir
    public Path testFolder;

    private StorageManagerPrescription storageManager;

    @BeforeEach
    public void setUp() {
        JsonPrescriptionListStorage prescriptionListStorage = new JsonPrescriptionListStorage(
            getTempFilePath("pl"));
        JsonUserPrefsStoragePrescription userPrefsStorage = new JsonUserPrefsStoragePrescription(
            getTempFilePath("prefs"));
        storageManager = new StorageManagerPrescription(prescriptionListStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonUserPrefsStoragePrescription} class.
         * More extensive testing of UserPref saving/reading is done in
         * {@link JsonUserPrefsStoragePrescriptionTest} class.
         */
        UserPrefsPrescription original = new UserPrefsPrescription();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefsPrescription retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    @Test
    public void addressBookReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is properly wired to the
         * {@link JsonAddressBookStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link JsonAddressBookStorageTest} class.
         */
        PrescriptionList original = getTypicalPrescriptionList();
        storageManager.savePrescriptionList(original);
        ReadOnlyPrescriptionList retrieved = storageManager.readPrescriptionList().get();
        assertEquals(original, new PrescriptionList(retrieved));
    }

    @Test
    public void getAddressBookFilePath() {
        assertNotNull(storageManager.getPrescriptionListFilePath());
    }

}
