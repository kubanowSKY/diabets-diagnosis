package diabetes.diagnosis;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Created by Sowul.
 */
public class PatientList {

    // Lista przechowująca dane pacjentów.
    private ObservableList<Patient> patientList;

    // Konstruktor inicjalizujący listę pacjentów.
    public PatientList() {
        patientList = FXCollections.observableArrayList();
    }

    // Metoda zwracająca listę pacjentów.
    public ObservableList<Patient> getPatientsList() {
        return patientList;
    }
}
