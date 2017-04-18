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

import java.text.ParsePosition;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by Sowul.
 */
public class ExaminationFormView extends GridPane {

    private DatePicker datePicker;
    // Metoda zwracająca datę.
    public DatePicker getDatePicker() { return datePicker; }

    private CheckBox ghbCheckBox;
    // Metoda zwracająca CheckBox mówiący o obecności GHB.
    public CheckBox getGhbCheckBox() { return ghbCheckBox; }

    private TextField bloodGlucoseField;
    // Metoda zwracająca zawartość pola przeznaczonego na poziom glukozy we krwi.
    public TextField getBloodGlucoseField() { return bloodGlucoseField; }

    private TextField sugarLvlField;
    // Metoda zwracająca zawartość pola przeznaczonego na poziom cukru w moczu.
    public TextField getSugarLvlField() { return sugarLvlField; }

    private Label dateLabel;
    private Label ghbLabel;
    private Label bloodGlucoseLabel;
    private Label sugarLvlLabel;

    private Button saveBtn;
    // Metoda zwracająca przycisk służący do zapisywania wyników badania.
    public Button getSaveBtn() { return saveBtn; }

    private Button cancelBtn;
    // Metoda zwracająca przycisk służący do czyszczenia formularza badania.
    public Button getCancelBtn() { return cancelBtn; }


    private Patient patient;

    public ExaminationFormView() {

        createControls();
        setProperties();
        addControls();
        configureGrid();

    }

    private void createControls() {
        dateLabel = new Label("Data:");
        datePicker = new DatePicker();
        datePicker.setId(" - Data\n");

        ghbLabel = new Label("Obecność GHB:");
        ghbCheckBox = new CheckBox();

        bloodGlucoseLabel = new Label("Stężenie glukozy we krwi:");
        bloodGlucoseField = new TextField();
        bloodGlucoseField.setId(" - Stężenie glukozy we krwi\n");


        sugarLvlLabel = new Label("Poziom cukru w moczu:");
        sugarLvlField = new TextField();
        sugarLvlField.setId(" - Poziom cukru w moczu\n");


        saveBtn = new Button("Zapisz");
        cancelBtn = new Button("Anuluj");
    }

    private void addControls() {
        Text sceneTitle = new Text("Badanie");
        sceneTitle.setFont(Font.font(15));
        this.add(sceneTitle, 0, 0, 2, 1);

        this.add(dateLabel,0, 1);
        this.add(datePicker,1, 1);

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

    private void configureGrid() {
        ColumnConstraints leftCol = new ColumnConstraints();
        leftCol.setPercentWidth(45);
        ColumnConstraints rightCol = new ColumnConstraints();
        rightCol.setPercentWidth(55);
        this.getColumnConstraints().addAll(leftCol, rightCol);

        this.setHgap(50);
        this.setVgap(10);
        this.setPadding(new Insets(5, 5, 5, 5));
    }

    private void setProperties() {
        datePicker.setShowWeekNumbers(true);
        String pattern = "dd/MM/yyyy";
        // zmienia format daty na format używany w Polsce
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

        // TODO Patryk
        bloodGlucoseField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(bloodGlucoseField.getText().matches("[aA-zZ]")) {
                    bloodGlucoseField.setText(oldValue);
                }
            }
        });

        // TODO Patryk
        sugarLvlField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(sugarLvlField.getText().matches("[aA-zZ]")) {
                    sugarLvlField.setText(oldValue);
                }
            }
        });
    }
}
