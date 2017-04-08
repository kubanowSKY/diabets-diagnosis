package diabetes.diagnosis;

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
        if (validateData()) {
                Patient selected = patientTableView.getPatientTable().getSelectionModel().getSelectedItem();
                if (selected != null) {
                    selected.setFirstName(patientFormView.getFirstNameField().getText().toString());
                    selected.setLastName(patientFormView.getLastNameField().getText().toString());
                    selected.setGender(patientFormView.getGenderToggleGroup().getSelectedToggle().getUserData().toString());
                    selected.setPeselNumber(patientFormView.getPeselNumberField().getText().toString());
                    selected.setInsurance(patientFormView.getInsuranceComboBox().getSelectionModel().getSelectedItem().toString());

                    clearPatientForm();
                    patientTableView.getPatientTable().getSelectionModel().clearSelection();
                    disableInputMode();
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

                    // clear form after addition1
                    clearPatientForm();
                }
                //disableInputMode();
            }
        });

        patientFormView.getCancelBtn().setOnAction((event) -> {
            System.out.println("patientFormView Cancel clicked");
            clearPatientForm();
            clearExaminationForm();
            patientTableView.getPatientTable().getSelectionModel().clearSelection();
            disableInputMode();
        });

        examinationFormView.getSaveBtn().setOnAction((event) -> {
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
        });

        examinationFormView.getCancelBtn().setOnAction((event) -> {
            clearExaminationForm();
            clearPatientForm();
            disableInputMode();
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

    // TODO!!! NIE DZIAŁA
    private boolean validateData() {
    if(patientFormView.getPeselNumberField().getLength() != 11) { return false; }
        for(Patient patient: patientList.getPatientsList()) {
            if (patientFormView.getPeselNumberField().getText() == patient.getPeselNumber()) {
                return false;
            }
        }

    return true;
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

        examinationFormView.getDatePicker().setDisable(false);
        examinationFormView.getGhbCheckBox().setDisable(false);
        examinationFormView.getBloodGlucoseField().setDisable(false);
        examinationFormView.getSugarLvlField().setDisable(false);
        examinationFormView.getSaveBtn().setDisable(false);
        examinationFormView.getCancelBtn().setDisable(false);
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
}
