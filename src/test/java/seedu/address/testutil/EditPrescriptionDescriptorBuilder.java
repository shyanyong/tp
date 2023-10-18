package seedu.address.testutil;

import seedu.address.logic.commands.EditCommand.EditPrescriptionDescriptor;
import seedu.address.model.prescription.ConsumptionCount;
import seedu.address.model.prescription.Date;
import seedu.address.model.prescription.Dosage;
import seedu.address.model.prescription.Frequency;
import seedu.address.model.prescription.Name;
import seedu.address.model.prescription.Note;
import seedu.address.model.prescription.Prescription;
import seedu.address.model.prescription.Stock;

/**
 * A utility class to help with building EditPrescriptionDescriptor objects.
 */
public class EditPrescriptionDescriptorBuilder {

    private EditPrescriptionDescriptor descriptor;

    public EditPrescriptionDescriptorBuilder() {
        descriptor = new EditPrescriptionDescriptor();
    }

    public EditPrescriptionDescriptorBuilder(EditPrescriptionDescriptor descriptor) {
        this.descriptor = new EditPrescriptionDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditPrescriptionDescriptor} with fields containing {@code prescription}'s details
     */
    public EditPrescriptionDescriptorBuilder(Prescription prescription) {
        descriptor = new EditPrescriptionDescriptor();
        descriptor.setName(prescription.getName());
        descriptor.setDosage(prescription.getDosage());
        descriptor.setFrequency(prescription.getFrequency());
        descriptor.setStartDate(prescription.getStartDate());
        descriptor.setEndDate(prescription.getEndDate());
        descriptor.setExpiryDate(prescription.getExpiryDate());
        descriptor.setTotalStock(prescription.getTotalStock());
        descriptor.setConsumptionCount(prescription.getConsumptionCount());
        descriptor.setNote(prescription.getNote());
    }

    /**
     * Sets the {@code Name} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withName(String name) {
        descriptor.setName(new Name(name));
        return this;
    }

    /**
     * Sets the {@code Dosage} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withDosage(String dosage) {
        descriptor.setDosage(new Dosage(dosage));
        return this;
    }

    /**
     * Sets the {@code Frequency} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withFrequency(String frequency) {
        descriptor.setFrequency(new Frequency(frequency));
        return this;
    }

    /**
     * Sets the {@code StartDate} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withStartDate(String startDate) {
        descriptor.setStartDate(new Date(startDate));
        return this;
    }

    /**
     * Sets the {@code EndDate} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withEndDate(String endDate) {
        descriptor.setEndDate(new Date(endDate));
        return this;
    }

    /**
     * Sets the {@code ExpiryDate} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withExpiryDate(String expiryDate) {
        descriptor.setExpiryDate(new Date(expiryDate));
        return this;
    }

    /**
     * Sets the {@code TotalStock} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withTotalStock(String totalStock) {
        descriptor.setTotalStock(new Stock(totalStock));
        return this;
    }

    /**
     * Sets the {@code ConsumptionCount} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withConsumptionCount(String consumptionCount) {
        descriptor.setConsumptionCount(new ConsumptionCount(consumptionCount, false));
        return this;
    }

    /**
     * Sets the {@code Note} of the {@code EditPrescriptionDescriptor} that we are building.
     */
    public EditPrescriptionDescriptorBuilder withNote(String note) {
        descriptor.setNote(new Note(note));
        return this;
    }

    public EditPrescriptionDescriptor build() {
        return descriptor;
    }
}
