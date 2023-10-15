package seedu.address.model;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class UserPrefsPrescriptionTest {

    @Test
    public void setGuiSettings_nullGuiSettings_throwsNullPointerException() {
        UserPrefsPrescription userPref = new UserPrefsPrescription();
        assertThrows(NullPointerException.class, () -> userPref.setGuiSettings(null));
    }

    @Test
    public void setPrescriptionListFilePath_nullPath_throwsNullPointerException() {
        UserPrefsPrescription userPrefs = new UserPrefsPrescription();
        assertThrows(NullPointerException.class, () -> userPrefs.setPrescriptionListFilePath(null));
    }

}
