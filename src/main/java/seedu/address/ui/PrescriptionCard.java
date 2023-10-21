package seedu.address.ui;

// import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
// import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.prescription.Prescription;

/**
 * An UI component that displays information of a {@code Prescription}.
 */
public class PrescriptionCard extends UiPart<Region> {

    private static final String FXML = "PrescriptionListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Prescription prescription;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label dosage;
    @FXML
    private Label frequency;
    @FXML
    private Label startDate;
    @FXML
    private Label endDate;
    @FXML
    private Label expiryDate;
    @FXML
    private Label totalStock;
    @FXML
    private Label consumptionCount;
    @FXML
    private Label note;

    private String dosageHeader = "Dosage: ";

    private String frequencyHeader = "Frequency: ";

    private String startDateHeader = "Start date: ";

    private String endDateHeader = "End date: ";

    private String expiryDateHeader = "Expiry date: ";

    private String totalStockHeader = "Total stock: ";

    private String consumptionCountHeader = "Consumption count: ";

    private String noteHeader = "Note: ";

    /**
     * Creates a {@code PrescriptionCode} with the given {@code Prescription} and index to display.
     */
    public PrescriptionCard(Prescription prescription, int displayedIndex) {
        super(FXML);
        this.prescription = prescription;
        id.setText(displayedIndex + ". ");
        name.setText(prescription.getName().toString());

        dosage.setText(dosageHeader);
        consumptionCount.setText(consumptionCountHeader + prescription.getConsumptionCount().getConsumptionCount());
        if (prescription.getDosage().isPresent()) {
            dosage.setText(dosageHeader + prescription.getDosage().get().toString());
            consumptionCount.setText(consumptionCountHeader + prescription.getConsumptionCount().getConsumptionCount()
                + "/" + prescription.getDosage().get().toString());
        }

        frequency.setText(frequencyHeader);
        if (prescription.getFrequency().isPresent()) {
            frequency.setText(frequencyHeader + prescription.getFrequency().get().toString());
        }
        startDate.setText(startDateHeader + prescription.getStartDate().toString());

        endDate.setText(endDateHeader);
        if (prescription.getEndDate().isPresent()) {
            endDate.setText(endDateHeader + prescription.getEndDate().get().toString());
        }

        expiryDate.setText(expiryDateHeader);
        if (prescription.getExpiryDate().isPresent()) {
            expiryDate.setText(expiryDateHeader + prescription.getExpiryDate().get().toString());
        }

        totalStock.setText(totalStockHeader);
        if (prescription.getTotalStock().isPresent()) {
            totalStock.setText(totalStockHeader + prescription.getTotalStock().get().toString());
        }

        note.setText(noteHeader);
        if (prescription.getNote().isPresent()) {
            note.setText(noteHeader + prescription.getNote().get().toString());
        }
        // prescription.getTags().stream()
        //         .sorted(Comparator.comparing(tag -> tag.tagName))
        //         .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }
}
