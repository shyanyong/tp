package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.PrescriptionList;
import seedu.address.model.ReadOnlyPrescriptionList;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code PrescriptionList} with sample data.
 */
public class SampleDataUtilPrescription {
    public static Prescription[] getSamplePrescriptions() {
        return new Prescription[] {
            new Prescription(new Name("Aspirin"), new Dosage("1"), new Frequency("Daily"),
                            new Date("01/08/2023"), new Date("25/12/2023"), new Date("01/12/2024"),
                            new Stock("100"), new ConsumptionCount("0", false),
                            new Note("Test note")),
        };
    }

    public static ReadOnlyPrescriptionList getSamplePrescriptionList() {
        PrescriptionList samplePrescriptionList = new PrescriptionList();
        for (Prescription samplePrescription : getSamplePrescriptions()) {
            samplePrescriptionList.addPrescription(samplePrescription);
        }
        return samplePrescriptionList;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
