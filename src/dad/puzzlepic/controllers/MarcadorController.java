package dad.puzzlepic.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import dad.puzzlepic.models.Dificultad;
import dad.puzzlepic.models.Modo;
import dad.puzzlepic.views.PuzzlePicApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


public class MarcadorController implements Initializable {
	@FXML
    private BorderPane view;

    @FXML
    private ComboBox<Dificultad> lvlCombo;

    @FXML
    private ComboBox<Modo> comboGame;

    @FXML
    private Button consultarButton, volverButton;

    @FXML
    private TableView<?> tableScores;

    @FXML
    private TableColumn<?, ?> puestoColumn;

    @FXML
    private TableColumn<?, ?> idColumn;

    @FXML
    private TableColumn<?, ?> nombreColumn;

    @FXML
    private TableColumn<?, ?> modoColumn;

    @FXML
    private TableColumn<?, ?> tiempoColumn;

    @FXML
    private TableColumn<?, ?> puntuacionColumn;

    //
    
    private MainController mainController;
    private Stage primaryStage;
    
	public MarcadorController(MainController mainController) throws IOException {
		this.mainController = mainController;
		this.primaryStage = PuzzlePicApp.getPrimaryStage();
		
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dad/puzzlepic/views/MarcadorView.fxml"));
		loader.setController(this);
		loader.load();
	
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		primaryStage.setOnCloseRequest(e->mainController.onSalirAction(e));
	}

	public BorderPane getView() {
		return view;
	}

	public void setView(BorderPane view) {
		this.view = view;
	}

	public Button getConsultarButton() {
		return consultarButton;
	}

	public Button getVolverButton() {
		return volverButton;
	}
	
	
	

	
}
