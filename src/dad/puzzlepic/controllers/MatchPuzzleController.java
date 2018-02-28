package dad.puzzlepic.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.puzzlepic.views.PuzzlePicApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MatchPuzzleController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private Button abandonarButton;

	//

	private MainController mainController;
	private Stage primaryStage;

	public MatchPuzzleController(MainController mainController) throws IOException {
		this.mainController = mainController;
		this.primaryStage = PuzzlePicApp.getPrimaryStage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dad/puzzlepic/views/MatchPuzzleView.fxml"));
		loader.setController(this);
		loader.load();

		initialize(null, null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		primaryStage.setOnCloseRequest(e->mainController.onSalirAction(e));
	}

	@FXML
	void abandonarOnAction(ActionEvent event) {
		mainController.getVista().setCenter(mainController.getControladorMenu().getView());
		System.out.println("Holaa");
	}

	public BorderPane getView() {
		return view;
	}

}
