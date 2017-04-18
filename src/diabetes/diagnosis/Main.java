package diabetes.diagnosis;
	
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.scene.Scene;

import java.util.Locale;


public class Main extends Application {
	public static void main(String[] args) {
		Locale.setDefault(new Locale("pl"));
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		primaryStage.setTitle("Rejestracja wyników badań");

		PatientFormView patientFormView = new PatientFormView();
		ExaminationFormView examinationFormView = new ExaminationFormView();
		PatientTableView patientTableView = new PatientTableView();

		PatientList patientsList = new PatientList();
		Controller controller = new Controller(examinationFormView,
				patientFormView, patientTableView, patientsList);

		BorderPane root = initializeGUI(patientFormView, examinationFormView, patientTableView);

		primaryStage.setScene(new Scene(root, 1000, 600));
		primaryStage.show();
	}

	private BorderPane initializeGUI(PatientFormView patientFormView,
									 ExaminationFormView examinationFormView,
									 PatientTableView patientTableView) {
		BorderPane root = new BorderPane();
		VBox topContainer = new VBox();
		MenuBar mainMenu = new MenuBar();

		Menu menu = new Menu("Aplikacja");
		MenuItem exit = new MenuItem("Zamknij");
		exit.setAccelerator(KeyCodeCombination.keyCombination("ALT+F4"));
		exit.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent t) {
				System.exit(0);
			}
		});
		menu.getItems().addAll(exit);
		mainMenu.getMenus().addAll(menu);

		topContainer.getChildren().add(mainMenu);
		root.setTop(topContainer);

		AnchorPane ap = new AnchorPane();
		root.setCenter(ap);

		SplitPane splitPane1 = new SplitPane();
		splitPane1.setOrientation(Orientation.HORIZONTAL);
		AnchorPane apl = new AnchorPane();
		VBox vb1 = new VBox();
		//PatientFormView patientFormView = new PatientFormView();
		//ExaminationFormView examinationFormView = new ExaminationFormView();
		vb1.getChildren().addAll(patientFormView, examinationFormView);
		vb1.setSpacing(100);
		vb1.setVgrow(patientFormView, Priority.ALWAYS);
		vb1.setVgrow(examinationFormView, Priority.ALWAYS);

		apl.getChildren().add(vb1);
		apl.setTopAnchor(vb1, 0.0);
		apl.setRightAnchor(vb1, 0.0);
		apl.setLeftAnchor(vb1, 0.0);
		apl.setBottomAnchor(vb1, 0.0);
		AnchorPane apr = new AnchorPane();
		//PatientTableView patientTableView = new PatientTableView();
		apr.getChildren().add(patientTableView);
		apr.setTopAnchor(patientTableView, 0.0);
		apr.setRightAnchor(patientTableView, 0.0);
		apr.setLeftAnchor(patientTableView, 0.0);
		apr.setBottomAnchor(patientTableView, 0.0);
		splitPane1.getItems().addAll(apl, apr);
		ap.getChildren().add(splitPane1);
		ap.setTopAnchor(splitPane1, 0.0);
		ap.setRightAnchor(splitPane1, 0.0);
		ap.setLeftAnchor(splitPane1, 0.0);
		ap.setBottomAnchor(splitPane1, 0.0);
		splitPane1.setDividerPosition(0, 0.45);

		return root;
	}
}
