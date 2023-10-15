package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.UserPrefsPrescription;

public class JsonUserPrefsStoragePrescriptionTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonUserPrefsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readUserPrefs(null));
    }

    private Optional<UserPrefsPrescription> readUserPrefs(String userPrefsFileInTestDataFolder) throws DataLoadingException {
        Path prefsFilePath = addToTestDataPathIfNotNull(userPrefsFileInTestDataFolder);
        return new JsonUserPrefsStoragePrescription(prefsFilePath).readUserPrefs(prefsFilePath);
    }

    @Test
    public void readUserPrefs_missingFile_emptyResult() throws DataLoadingException {
        assertFalse(readUserPrefs("NonExistentFile.json").isPresent());
    }

    @Test
    public void readUserPrefs_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class, () -> readUserPrefs("NotJsonFormatUserPrefs.json"));
    }

    private Path addToTestDataPathIfNotNull(String userPrefsFileInTestDataFolder) {
        return userPrefsFileInTestDataFolder != null
            ? TEST_DATA_FOLDER.resolve(userPrefsFileInTestDataFolder)
            : null;
    }

    @Test
    public void readUserPrefs_fileInOrder_successfullyRead() throws DataLoadingException {
        UserPrefsPrescription expected = getTypicalUserPrefs();
        UserPrefsPrescription actual = readUserPrefs("TypicalUserPref.json").get();
        assertEquals(expected, actual);
    }

    @Test
    public void readUserPrefs_valuesMissingFromFile_defaultValuesUsed() throws DataLoadingException {
        UserPrefsPrescription actual = readUserPrefs("EmptyUserPrefs.json").get();
        assertEquals(new UserPrefsPrescription(), actual);
    }

    @Test
    public void readUserPrefs_extraValuesInFile_extraValuesIgnored() throws DataLoadingException {
        UserPrefsPrescription expected = getTypicalUserPrefs();
        UserPrefsPrescription actual = readUserPrefs("ExtraValuesUserPref.json").get();

        assertEquals(expected, actual);
    }

    private UserPrefsPrescription getTypicalUserPrefs() {
        UserPrefsPrescription userPrefs = new UserPrefsPrescription();
        userPrefs.setGuiSettings(new GuiSettings(1000, 500, 300, 100));
        userPrefs.setPrescriptionListFilePath(Paths.get("prescriptionList.json"));
        return userPrefs;
    }

    @Test
    public void savePrefs_nullPrefs_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(null, "SomeFile.json"));
    }

    @Test
    public void saveUserPrefs_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveUserPrefs(new UserPrefsPrescription(), null));
    }

    /**
     * Saves {@code userPrefs} at the specified {@code prefsFileInTestDataFolder} filepath.
     */
    private void saveUserPrefs(UserPrefsPrescription userPrefs, String prefsFileInTestDataFolder) {
        try {
            new JsonUserPrefsStoragePrescription(addToTestDataPathIfNotNull(prefsFileInTestDataFolder))
                .saveUserPrefs(userPrefs);
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file", ioe);
        }
    }

    @Test
    public void saveUserPrefs_allInOrder_success() throws DataLoadingException, IOException {

        UserPrefsPrescription original = new UserPrefsPrescription();
        original.setGuiSettings(new GuiSettings(1200, 200, 0, 2));

        Path pefsFilePath = testFolder.resolve("TempPrefs.json");
        JsonUserPrefsStoragePrescription jsonUserPrefsStorage = new JsonUserPrefsStoragePrescription(pefsFilePath);

        //Try writing when the file doesn't exist
        jsonUserPrefsStorage.saveUserPrefs(original);
        UserPrefsPrescription readBack = jsonUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);

        //Try saving when the file exists
        original.setGuiSettings(new GuiSettings(5, 5, 5, 5));
        jsonUserPrefsStorage.saveUserPrefs(original);
        readBack = jsonUserPrefsStorage.readUserPrefs().get();
        assertEquals(original, readBack);
    }

}
