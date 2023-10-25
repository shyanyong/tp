package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.drug.Drug;

/**
 * Jackson-friendly version of {@link Drug}.
 */
class JsonAdaptedDrug {

    private final String drugName;

    /**
     * Constructs a {@code JsonAdaptedDrug} with the given {@code drugName}.
     */
    @JsonCreator
    public JsonAdaptedDrug(String drugName) {
        this.drugName = drugName;
    }

    /**
     * Converts a given {@code Drug} into this class for Jackson use.
     */
    public JsonAdaptedDrug(Drug source) {
        drugName = source.drugName;
    }

    @JsonValue
    public String getDrugName() {
        return drugName;
    }

    /**
     * Converts this Jackson-friendly adapted drug object into the model's {@code Drug} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted drug.
     */
    public Drug toModelType() throws IllegalValueException {
        if (!Drug.isValidDrugName(drugName)) {
            throw new IllegalValueException(Drug.MESSAGE_CONSTRAINTS);
        }
        return new Drug(drugName);
    }

}
