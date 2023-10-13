package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.prescription.Prescription;

/**
 * Unmodifiable view of an address book
 */
public interface ReadOnlyPrescriptionList {

    /**
     * Returns an unmodifiable view of the prescriptions list.
     * This list will not contain any duplicate prescriptions.
     */
    ObservableList<Prescription> getPrescriptionList();

}
