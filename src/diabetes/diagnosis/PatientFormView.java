package diabetes.diagnosis;

import java.text.DecimalFormat;
import java.text.ParsePosition;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by Sowul.
 */
public class PatientFormView extends GridPane {

    final private DecimalFormat numberFormat;
    private TextField firstNameField;
    private TextField lastNameField;
    private TextField peselNumberField;

    private Label firstNameLabel;
    private Label lastNameLabel;
    private Label peselNumberLabel;
    private Label genderLabel;
    private Label insuranceLabel;

    public TextField getFirstNameField() { return firstNameField; }
    public TextField getLastNameField() { return lastNameField; }
    public TextField getPeselNumberField() { return peselNumberField; }
    public ToggleGroup getGenderToggleGroup() { return genderToggleGroup; }
    public ComboBox getInsuranceComboBox() { return insuranceComboBox; }

    public Label getGenderLabel() { return genderLabel; }

    private ToggleGroup genderToggleGroup;
    private RadioButton femaleBtn;
    private RadioButton maleBtn;

    public RadioButton getFemaleBtn() { return femaleBtn; }
    public RadioButton getMaleBtn() { return maleBtn; }

    private ComboBox<String> insuranceComboBox;

    private Button saveBtn;
    private Button cancelBtn;

    public Button getSaveBtn() { return saveBtn; }
    public Button getCancelBtn() { return cancelBtn; }

    private Patient patient;

    public PatientFormView() {

        numberFormat = new DecimalFormat("#.0");

        createAndConfigurePane();

        createAndLayoutControls();
    }

    private void createAndLayoutControls() {
        firstNameLabel = new Label("Imię:");
        firstNameField = new TextField();
        firstNameField.setId(" - Imię\n");
        firstNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(firstNameField.getText().matches(".*\\d+.*")) {
                      firstNameField.setText(oldValue);
                }
             }
        });

        lastNameLabel = new Label("Nazwisko:");
        lastNameField = new TextField();
        lastNameField.setId(" - Nazwisko\n");
        lastNameField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if(lastNameField.getText().matches(".*\\d+.*")) {
                      lastNameField.setText(oldValue);
                }
             }
        });

        peselNumberLabel = new Label("PESEL:");
        peselNumberField = new TextField();
        peselNumberField.setId(" - PESEL\n");

        //
        peselNumberField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> ov, final String oldValue, final String newValue) {
                if (peselNumberField.getText().length() > 11) {
                    String s = peselNumberField.getText().substring(0, 11);
                    peselNumberField.setText(s);
                }
            }
        });

        peselNumberField.setTextFormatter(new TextFormatter<>(c -> {
            if (c.getControlNewText().isEmpty()) { return c; }
            ParsePosition parsePosition = new ParsePosition( 0 );
            Object object = numberFormat.parse(c.getControlNewText(), parsePosition);

            if ( object == null || parsePosition.getIndex() < c.getControlNewText().length() )
            {
                return null;
            }
            else
            {
                return c;
            }
        }));

        genderLabel = new Label("Płeć");
        genderLabel.setId(" - Płeć\n");
        femaleBtn = new RadioButton("Kobieta");
        femaleBtn.setUserData("K");
        maleBtn = new RadioButton("Mężczyzna");
        maleBtn.setUserData("M");
        genderToggleGroup = new ToggleGroup();
        femaleBtn.setToggleGroup(genderToggleGroup);
        maleBtn.setToggleGroup(genderToggleGroup);

        insuranceLabel = new Label("Ubezpieczenie:");
        insuranceComboBox = new ComboBox<String>();
        insuranceComboBox.setId(" - Ubezpieczenie\n");
        insuranceComboBox.getItems().addAll(
                "NFZ",
                "Prywatne",
                "Brak");
        insuranceComboBox.setMinWidth(200);

        saveBtn = new Button("Zapisz");
        cancelBtn = new Button("Anuluj");

        Text scenetitle = new Text("Dane pacjenta");
        scenetitle.setFont(Font.font(15));
        this.add(scenetitle, 0, 0, 2, 1);

        this.add(firstNameLabel,0, 1);
        this.add(firstNameField,1, 1);
        this.add(lastNameLabel,0, 2);
        this.add(lastNameField,1, 2);
        this.add(peselNumberLabel,0, 3);
        this.add(peselNumberField,1, 3);

        this.add(genderLabel,0, 4);
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(femaleBtn, maleBtn);
        hb1.setSpacing(30);
        this.add(hb1,1, 4);
        this.add(insuranceLabel,0, 5);
        this.add(insuranceComboBox,1, 5);
        HBox hb2 = new HBox();
        hb2.setSpacing(10);
        hb2.getChildren().addAll(saveBtn, cancelBtn);
        this.add(hb2,0, 6);
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
