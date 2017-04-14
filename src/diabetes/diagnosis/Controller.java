package diabetes.diagnosis;

import javafx.scene.control.Alert;

/**
 * Created by Sowul.
 */
public class Controller {

    private ExaminationFormView examinationFormView;
    private PatientFormView patientFormView;
    private PatientTableView patientTableView;
    private PatientList patientList;

    public Controller(ExaminationFormView examinationFormView,
                      PatientFormView patientFormView,
                      PatientTableView patientTableView,
                      PatientList patientList) {
        this.examinationFormView = examinationFormView;
        this.patientFormView = patientFormView;
        this.patientTableView = patientTableView;
        this.patientList = patientList;

        patientTableView.getAddBtn().requestFocus();
        disableInputMode();

        //Buttons handlers initializations
        patientFormView.getSaveBtn().setOnAction((event) -> {
            reloadFields();
            if (validateData()) {
                Patient selected = patientTableView.getPatientTable().getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setFirstName(patientFormView.getFirstNameField().getText().toString());
                    selected.setLastName(patientFormView.getLastNameField().getText().toString());
                    selected.setGender(patientFormView.getGenderToggleGroup().getSelectedToggle().getUserData().toString());
                    selected.setPeselNumber(patientFormView.getPeselNumberField().getText().toString());
                    selected.setInsurance(patientFormView.getInsuranceComboBox().getSelectionModel().getSelectedItem().toString());

                    //clearPatientForm();
                    //clearExaminationForm();
                    patientTableView.getPatientTable().refresh();
                    //patientTableView.getPatientTable().getSelectionModel().clearSelection();
                    //disableInputMode();
                }
                else {
                    Patient patient = new Patient(
                            patientFormView.getFirstNameField().getText().toString(),
                            patientFormView.getLastNameField().getText().toString(),
                            patientFormView.getGenderToggleGroup().getSelectedToggle().getUserData().toString(),
                            patientFormView.getPeselNumberField().getText().toString(),
                            patientFormView.getInsuranceComboBox().getSelectionModel().getSelectedItem().toString()
                    );

                    patientList.getPatientsList().add(patient);
                    patientTableView.getPatientTable().setItems(patientList.getPatientsList());

                    patientTableView.getPatientTable().requestFocus();
                    patientTableView.getPatientTable().getSelectionModel().selectLast();
                    patientTableView.getPatientTable().getFocusModel().focus(-1);

                    //clear form after addition
                    //clearPatientForm();
                }
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Wprowadzono złe dane pacjenta.");
                alert.showAndWait();
            }
        //disableInputMode();
        });

        patientFormView.getCancelBtn().setOnAction((event) -> {
            System.out.println("patientFormView Cancel clicked");
            reloadFields();
            patientTableView.getPatientTable().getSelectionModel().clearSelection();
            clearPatientForm();
            clearExaminationForm();
            disableInputMode();
        });

        examinationFormView.getSaveBtn().setOnAction((event) -> {
            reloadFields();
            // TODO!!! nie działa
            if(validateExamination()) {
                Examination examination = new Examination(
                        examinationFormView.getDatePicker().getValue(),
                        examinationFormView.getGhbCheckBox().isSelected(),
                        Double.parseDouble(examinationFormView.getBloodGlucoseField().getText().toString()),
                        Double.parseDouble(examinationFormView.getSugarLvlField().getText().toString())
                );

                patientTableView.getPatientTable().getSelectionModel().getSelectedItem().setExamination(examination);
                clearExaminationForm();
                clearPatientForm();
                patientTableView.getPatientTable().refresh();
                patientTableView.getPatientTable().getSelectionModel().clearSelection();
                disableInputMode();
            }
            else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Błąd");
                alert.setHeaderText("Wprowadzono złe dane z wynikami badań.");
                alert.showAndWait();
            }
        });

        examinationFormView.getCancelBtn().setOnAction((event) -> {
            System.out.println("examinationFormView Cancel clicked");
            reloadFields();
            patientTableView.getPatientTable().getSelectionModel().clearSelection();
            clearExaminationForm();
            //clearPatientForm();
            //disableInputMode();
        });

        patientTableView.getAddBtn().setOnAction((event) -> {
            System.out.println("patientTableView Add clicked");
            patientTableView.getPatientTable().getSelectionModel().clearSelection();
            clearPatientForm();
            clearExaminationForm();
            enableInputMode();
        });

        patientTableView.getDeleteBtn().setOnAction((event) -> {
            System.out.println("patientTableView Delete clicked");
            disableInputMode();
            int selectedIndex = patientTableView.getPatientTable().getSelectionModel().getSelectedIndex();
            if (selectedIndex >= 0) {
                patientTableView.getPatientTable().getItems().remove(selectedIndex);
                patientTableView.getPatientTable().getSelectionModel().clearSelection();
                clearPatientForm();
                clearExaminationForm();
            } else {
                // popup?
            }
        });

        //observe table
        patientTableView.getPatientTable().getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> showPatientData(newValue));

    }

    private void showPatientData(Patient patient) {
        if (patient != null) {
            enableInputMode();
            // Fill the labels with info from the person object.
            patientFormView.getFirstNameField().setText(patient.getFirstName());
            patientFormView.getLastNameField().setText(patient.getLastName());
            patientFormView.getPeselNumberField().setText(patient.getPeselNumber());
            if(patient.getGender().toString() == "K"){
                patientFormView.getGenderToggleGroup().selectToggle(
                        patientFormView.getGenderToggleGroup().getToggles().get(0));
            }
            else {
                patientFormView.getGenderToggleGroup().selectToggle(
                        patientFormView.getGenderToggleGroup().getToggles().get(1));
            }
            patientFormView.getInsuranceComboBox().setValue(patient.getInsurance());

            if (patient.isExamined()){
                examinationFormView.getDatePicker().setValue(patient.getExamination().getExaminationDate());
                examinationFormView.getGhbCheckBox().setSelected(patient.isExamined());
                examinationFormView.getBloodGlucoseField().setText(patient.getExamination().getBloodGlucose().toString());
                examinationFormView.getSugarLvlField().setText(patient.getExamination().getSugarLvl().toString());
            }
            else {
                System.out.println("Niezbadany");
            }

        } else {
            //clearPatientForm();
            //clearExaminationForm();
        }
    }

    private boolean validateData() {
        boolean valid = true;
        if(patientFormView.getFirstNameField().getLength() == 0) {
            patientFormView.getFirstNameField().setStyle("-fx-border-color: red;");
            valid = false;
        }
        if(patientFormView.getLastNameField().getLength() == 0) {
            patientFormView.getLastNameField().setStyle("-fx-border-color: red;");
            valid = false;
        }
        if(patientFormView.getPeselNumberField().getLength() != 11) {
            patientFormView.getPeselNumberField().setStyle("-fx-border-color: red;");
            valid = false;
        }
        if(!(patientFormView.getMaleBtn().isSelected() || patientFormView.getFemaleBtn().isSelected())) {
            patientFormView.getGenderLabel().setStyle("-fx-border-color: red;");
            valid = false;
        }
        if(patientFormView.getInsuranceComboBox().getSelectionModel().isEmpty()) {
            patientFormView.getInsuranceComboBox().setStyle("-fx-border-color: red;");
            valid = false;
        }
        for(Patient patient: patientList.getPatientsList()) {
            if (patientFormView.getPeselNumberField().getText().equals(patient.getPeselNumber())) {
                Patient selected = patientTableView.getPatientTable().getSelectionModel().getSelectedItem();
                if(selected != null && patient.getPeselNumber().equals(selected.getPeselNumber()))
                    continue;
                patientFormView.getPeselNumberField().setStyle("-fx-border-color: red;");
                valid = false;
            }
        }

        return valid;
    }

    private boolean validateExamination() {
        boolean valid = true;
        String decimalPattern = "([0-9]*)\\.([0-9]*)";
        if(!examinationFormView.getBloodGlucoseField().getText().matches(decimalPattern)) {
            examinationFormView.getBloodGlucoseField().setStyle("-fx-border-color: red;");
            valid = false;
        }
        if(!examinationFormView.getSugarLvlField().getText().matches(decimalPattern)) {
            examinationFormView.getSugarLvlField().setStyle("-fx-border-color: red;");
            valid = false;
        }

        return valid;
    }

    void clearPatientForm() {
        patientFormView.getFirstNameField().setText("");
        patientFormView.getLastNameField().setText("");
        patientFormView.getPeselNumberField().setText("");
        patientFormView.getGenderToggleGroup().selectToggle(null);
        patientFormView.getInsuranceComboBox().getSelectionModel().clearSelection();
    }

    void clearExaminationForm() {
        examinationFormView.getDatePicker().setValue(null);
        examinationFormView.getGhbCheckBox().setSelected(false);
        examinationFormView.getBloodGlucoseField().setText("");
        examinationFormView.getSugarLvlField().setText("");
    }

    void enableInputMode() {
        patientFormView.getFirstNameField().setDisable(false);
        patientFormView.getLastNameField().setDisable(false);
        patientFormView.getFemaleBtn().setDisable(false);
        patientFormView.getMaleBtn().setDisable(false);
        patientFormView.getPeselNumberField().setDisable(false);
        patientFormView.getInsuranceComboBox().setDisable(false);
        patientFormView.getSaveBtn().setDisable(false);
        patientFormView.getCancelBtn().setDisable(false);

        Patient selected = patientTableView.getPatientTable().getSelectionModel().getSelectedItem();
        if (selected != null) {
            examinationFormView.getDatePicker().setDisable(false);
            examinationFormView.getGhbCheckBox().setDisable(false);
            examinationFormView.getBloodGlucoseField().setDisable(false);
            examinationFormView.getSugarLvlField().setDisable(false);
            examinationFormView.getSaveBtn().setDisable(false);
            examinationFormView.getCancelBtn().setDisable(false);
        }
    }

    void disableInputMode() {
        patientFormView.getFirstNameField().setDisable(true);
        patientFormView.getLastNameField().setDisable(true);
        patientFormView.getFemaleBtn().setDisable(true);
        patientFormView.getMaleBtn().setDisable(true);
        patientFormView.getPeselNumberField().setDisable(true);
        patientFormView.getInsuranceComboBox().setDisable(true);
        patientFormView.getSaveBtn().setDisable(true);
        patientFormView.getCancelBtn().setDisable(true);

        examinationFormView.getDatePicker().setDisable(true);
        examinationFormView.getGhbCheckBox().setDisable(true);
        examinationFormView.getBloodGlucoseField().setDisable(true);
        examinationFormView.getSugarLvlField().setDisable(true);
        examinationFormView.getSaveBtn().setDisable(true);
        examinationFormView.getCancelBtn().setDisable(true);
    }

    private void reloadFields() {
        patientFormView.getFirstNameField().setStyle("-fx-border-color: ;");
        patientFormView.getLastNameField().setStyle("-fx-border-color: ;");
        patientFormView.getPeselNumberField().setStyle("-fx-border-color: ;");
        patientFormView.getGenderLabel().setStyle("-fx-border-color: ;");
        patientFormView.getInsuranceComboBox().setStyle("-fx-border-color: ;");

        examinationFormView.getBloodGlucoseField().setStyle("-fx-border-color: ;");
        examinationFormView.getSugarLvlField().setStyle("-fx-border-color: ;");
    }
}
