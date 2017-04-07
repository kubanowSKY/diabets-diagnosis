package diabetes.diagnosis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Sowul.
 */
public class PatientList {

    private ObservableList<Patient> patientList;

    public PatientList() {
        patientList = FXCollections.observableArrayList();
    }

    public ObservableList<Patient> getPatientsList() {
        return patientList;
    }
}
