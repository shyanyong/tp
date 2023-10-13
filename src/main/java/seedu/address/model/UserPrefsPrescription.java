package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import seedu.address.commons.core.GuiSettings;

/**
 * Represents User's preferences.
 */
public class UserPrefsPrescription implements ReadOnlyUserPrefsPrescription {

    private GuiSettings guiSettings = new GuiSettings();
    private Path prescriptionListFilePath = Paths.get("data" , "prescriptionList.json");

    /**
     * Creates a {@code UserPrefsPrescriptionPrescription} with default values.
     */
    public UserPrefsPrescription() {}

    /**
     * Creates a {@code UserPrefsPrescription} with the prefs in {@code userPrefs}.
     */
    public UserPrefsPrescription(ReadOnlyUserPrefsPrescription userPrefs) {
        this();
        resetData(userPrefs);
    }

    /**
     * Resets the existing data of this {@code UserPrefsPrescription} with {@code newUserPrefsPrescription}.
     */
    public void resetData(ReadOnlyUserPrefsPrescription newUserPrefsPrescription) {
        requireNonNull(newUserPrefsPrescription);
        setGuiSettings(newUserPrefsPrescription.getGuiSettings());
        setPrescriptionListFilePath(newUserPrefsPrescription.getPrescriptionListFilePath());
    }

    public GuiSettings getGuiSettings() {
        return guiSettings;
    }

    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        this.guiSettings = guiSettings;
    }

    public Path getPrescriptionListFilePath() {
        return prescriptionListFilePath;
    }

    public void setPrescriptionListFilePath(Path prescriptionListFilePath) {
        requireNonNull(prescriptionListFilePath);
        this.prescriptionListFilePath = prescriptionListFilePath;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof UserPrefsPrescription)) {
            return false;
        }

        UserPrefsPrescription otherUserPrefsPrescription = (UserPrefsPrescription) other;
        return guiSettings.equals(otherUserPrefsPrescription.guiSettings)
                && prescriptionListFilePath.equals(otherUserPrefsPrescription.prescriptionListFilePath);
    }

    @Override
    public int hashCode() {
        return Objects.hash(guiSettings, prescriptionListFilePath);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Gui Settings : " + guiSettings);
        sb.append("\nLocal data file location : " + prescriptionListFilePath);
        return sb.toString();
    }

}
