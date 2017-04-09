package diabetes.diagnosis;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.util.StringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Sowul.
 */
public class ExaminationFormView extends GridPane {

    //private GridPane this;

    private DatePicker datePicker;
    private CheckBox ghbCheckBox;
    private TextField bloodGlucoseField;
    private TextField sugarLvlField;

    public DatePicker getDatePicker() { return datePicker; }
    public CheckBox getGhbCheckBox() { return ghbCheckBox; }
    public TextField getBloodGlucoseField() { return bloodGlucoseField; }
    public TextField getSugarLvlField() { return sugarLvlField; }

    private Label dateLabel;
    private Label ghbLabel;
    private Label bloodGlucoseLabel;
    private Label sugarLvlLabel;

    private Button saveBtn;
    private Button cancelBtn;

    public Button getSaveBtn() { return saveBtn; }
    public Button getCancelBtn() { return cancelBtn; }

    private Patient patient;

    public ExaminationFormView() {

        createAndConfigurePane();

        createAndLayoutControls();

    }

    private void createAndLayoutControls() {
        dateLabel = new Label("Data:");
        datePicker = new DatePicker();
        datePicker.setShowWeekNumbers(true);
        String pattern = "dd/MM/yyyy";
        StringConverter converter = new StringConverter<LocalDate>() {
            DateTimeFormatter dateFormatter =
                    DateTimeFormatter.ofPattern(pattern);
            @Override
            public String toString(LocalDate date) {
                if (date != null) {
                    return dateFormatter.format(date);
                } else {
                    return "";
                }
            }
            @Override
            public LocalDate fromString(String string) {
                if (string != null && !string.isEmpty()) {
                    return LocalDate.parse(string, dateFormatter);
                } else {
                    return null;
                }
            }
        };
        datePicker.setConverter(converter);

        ghbLabel = new Label("Obecność GHB:");
        ghbCheckBox = new CheckBox();

        bloodGlucoseLabel = new Label("Stężenie glukozy we krwi:");
        bloodGlucoseField = new TextField();
        bloodGlucoseField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(bloodGlucoseField.getText().matches("[aA-zZ]")) {
                      bloodGlucoseField.setText(oldValue);
                }
             }
        });

        sugarLvlLabel = new Label("Poziom cukru w moczu:");
        sugarLvlField = new TextField();
        sugarLvlField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(sugarLvlField.getText().matches("[aA-zZ]")) {
                    sugarLvlField.setText(oldValue);
                }
             }
        });

        saveBtn = new Button("Zapisz");
        cancelBtn = new Button("Anuluj");

        Text scenetitle = new Text("Badanie");
        scenetitle.setFont(Font.font(15));
        this.add(scenetitle, 0, 0, 2, 1);

        this.add(dateLabel,0, 1);
        this.add(datePicker,1, 1);
        //this.add(firstNameField,1, 1);

        this.add(ghbLabel,0, 2);
        this.add(ghbCheckBox,1, 2);
        this.add(bloodGlucoseLabel,0, 3);
        this.add(bloodGlucoseField,1, 3);
        this.add(sugarLvlLabel, 0, 4);
        this.add(sugarLvlField, 1, 4);

        HBox hb2 = new HBox();
        hb2.setSpacing(10);
        hb2.getChildren().addAll(saveBtn, cancelBtn);
        this.add(hb2,0, 5);
    }

    private void createAndConfigurePane() {

        ColumnConstraints leftCol = new ColumnConstraints();
        leftCol.setPercentWidth(45);
        ColumnConstraints rightCol = new ColumnConstraints();
        rightCol.setPercentWidth(55);
        this.getColumnConstraints().addAll(leftCol, rightCol);

        this.setHgap(50);
        this.setVgap(10);
        this.setPadding(new Insets(5, 5, 5, 5));
    }
}
