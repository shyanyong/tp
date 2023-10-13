package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.ReadOnlyUserPrefsPrescription;
import seedu.address.model.UserPrefsPrescription;

/**
 * Manages storage of PrescriptionList data in local storage.
 */
public class StorageManagerPrescription implements StoragePrescription {

    private static final Logger logger = LogsCenter.getLogger(StorageManagerPrescription.class);
    private PrescriptionListStorage prescriptionListStorage;
    private UserPrefsStoragePrescription userPrefsStorage;

    /**
     * Creates a {@code StorageManagerPrescription} with the given {@code PrescriptionListStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManagerPrescription(PrescriptionListStorage prescriptionListStorage,
        UserPrefsStoragePrescription userPrefsStorage) {
        this.prescriptionListStorage = prescriptionListStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefsPrescription> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefsPrescription userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }


    // ================ PrescriptionList methods ==============================

    @Override
    public Path getPrescriptionListFilePath() {
        return prescriptionListStorage.getPrescriptionListFilePath();
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readPrescriptionList() throws DataLoadingException {
        return readPrescriptionList(prescriptionListStorage.getPrescriptionListFilePath());
    }

    @Override
    public Optional<ReadOnlyPrescriptionList> readPrescriptionList(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return prescriptionListStorage.readPrescriptionList(filePath);
    }

    @Override
    public void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList) throws IOException {
        savePrescriptionList(prescriptionList, prescriptionListStorage.getPrescriptionListFilePath());
    }

    @Override
    public void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        prescriptionListStorage.savePrescriptionList(prescriptionList, filePath);
    }

}
