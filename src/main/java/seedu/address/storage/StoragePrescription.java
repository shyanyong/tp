package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.ReadOnlyUserPrefsPrescription;
import seedu.address.model.UserPrefsPrescription;

/**
 * API of the StoragePrescription component
 */
public interface StoragePrescription extends PrescriptionListStorage, UserPrefsStoragePrescription {

    @Override
    Optional<UserPrefsPrescription> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefsPrescription userPrefs) throws IOException;

    @Override
    Path getPrescriptionListFilePath();

    @Override
    Optional<ReadOnlyPrescriptionList> readPrescriptionList() throws DataLoadingException;

    @Override
    void savePrescriptionList(ReadOnlyPrescriptionList prescriptionList) throws IOException;

}
