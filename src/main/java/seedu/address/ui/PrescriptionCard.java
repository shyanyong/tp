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

    /**
     * Creates a {@code PrescriptionCode} with the given {@code Prescription} and index to display.
     */
    public PrescriptionCard(Prescription prescription, int displayedIndex) {
        super(FXML);
        this.prescription = prescription;
        id.setText(displayedIndex + ". ");
        name.setText(prescription.getName().toString());
        dosage.setText(prescription.getDosage().toString());
        frequency.setText(prescription.getFrequency().toString());
        startDate.setText(prescription.getStartDate().toString());
        endDate.setText(prescription.getEndDate().toString());
        expiryDate.setText(prescription.getExpiryDate().toString());
        totalStock.setText(prescription.getTotalStock().toString());
        note.setText(prescription.getNote().toString());

        setCompletionStatus(prescription);
        // prescription.getTags().stream()
        //         .sorted(Comparator.comparing(tag -> tag.tagName))
        //         .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    private void setCompletionStatus(Prescription prescription) {
        consumptionCount.getStyleClass().clear();

        if (prescription.getIsCompleted()) {
            consumptionCount.setText("Completed");
            consumptionCount.getStyleClass().add("consumption-status-green");
        } else {
            consumptionCount.setText(String.format("Uncompleted %s/%s",
                prescription.getConsumptionCount().getConsumptionCount(),
                prescription.getDosage()));
            consumptionCount.getStyleClass().add("consumption-status-red");
        }
    }
}
