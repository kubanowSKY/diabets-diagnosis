package diabetes.diagnosis;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

/**
 * Created by Sowul.
 */
public class PatientTableView extends VBox {

    private TableView<Patient> patientsTable;

    // Metoda zwracająca element przechowujący listę pacjentów.
    public TableView<Patient> getPatientTable() { return patientsTable; }

    private TableColumn<Patient, String> nameColumn;
    private TableColumn<Patient, String> genderColumn;
    private TableColumn<Patient, String> peselColumn;
    private TableColumn<Patient, String> insuranceColumn;
    private TableColumn<Patient, Boolean> examinedColumn;

    private ScrollPane scrollPane;

    private Button addBtn;
    private Button deleteBtn;

    // Metoda zwracająca przycisk do dodawania pacjentów.
    public Button getAddBtn() { return addBtn; }

    // Metoda zwracająca przycisk do usuwania pacjentów.
    public Button getDeleteBtn() { return deleteBtn; }

    public PatientTableView() {
        initializeTableView();
        initializePatientTableView();
    }

    // Metoda inicjalizująca element przechowujący listę pacjentów.
    private void initializeTableView() {
        nameColumn = new TableColumn<Patient, String>("Imię i nazwisko");
        genderColumn = new TableColumn<Patient, String>("Płeć");
        genderColumn.setMaxWidth(1500);
        peselColumn = new TableColumn<Patient, String>("PESEL");
        insuranceColumn = new TableColumn<Patient, String>("Ubezpieczenie");
        examinedColumn = new TableColumn<Patient, Boolean>("Badanie");
        examinedColumn.setMaxWidth(3000);

        nameColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName()));
        genderColumn.setCellValueFactory(cellData -> cellData.getValue().genderProperty());
        peselColumn.setCellValueFactory(cellData -> cellData.getValue().peselNumberProperty());
        insuranceColumn.setCellValueFactory(cellData -> cellData.getValue().insuranceProperty());
        examinedColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().examinedProperty().getValue()));
        examinedColumn.setCellFactory( tableCell -> new CheckBoxTableCell<>());

        patientsTable = new TableView<Patient>();
        patientsTable.getColumns().addAll(nameColumn, genderColumn, peselColumn, insuranceColumn, examinedColumn);
        patientsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    // Metoda łącząca listę pacjentów z resztą elementów prawego panelu aplikacji.
    private void initializePatientTableView() {
        addBtn = new Button("Dodaj");
        deleteBtn = new Button("Usuń");
        HBox hbox = new HBox();
        hbox.getChildren().addAll(addBtn, deleteBtn);
        hbox.setSpacing(10);
        hbox.setPadding(new Insets(5, 5, 5, 5));
        scrollPane = new ScrollPane();
        scrollPane.setContent(patientsTable);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
        this.setVgrow(scrollPane, Priority.ALWAYS);
        this.getChildren().addAll(scrollPane, hbox);
    }
}
