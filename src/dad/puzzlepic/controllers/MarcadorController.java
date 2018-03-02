package dad.puzzlepic.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.puzzlepic.models.Dificultad;
import dad.puzzlepic.models.Jugador;
import dad.puzzlepic.models.Marcador;
import dad.puzzlepic.models.Modo;
import dad.puzzlepic.views.PuzzlePicApp;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

/**
 * 
 * @author fede
 *
 */
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
	private TableView<Marcador> tableScores;

	@FXML
	private TableColumn<Marcador, String> puestoColumn;

	@FXML
	private TableColumn<Marcador, String> nombreColumn;

	@FXML
	private TableColumn<Marcador, String> puntuacionColumn;

	@FXML
	private TableColumn<Marcador, String> tiempoColumn;

	@FXML
	private TableColumn<Marcador, String> fotoColumn;

	//

	private MainController mainController;
	private Stage primaryStage;
	// private ArrayList<Marcador> marcadores = new ArrayList<>();
	private ListProperty<Marcador> marcadores = new SimpleListProperty<>(this, "marcadores",
			FXCollections.observableArrayList());
	Marcador marcador = new Marcador();

	public MarcadorController(MainController mainController) throws IOException {
		this.mainController = mainController;
		this.primaryStage = PuzzlePicApp.getPrimaryStage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dad/puzzlepic/views/MarcadorView.fxml"));
		loader.setController(this);
		loader.load();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		comboGame.getItems().setAll(Modo.values());
		comboGame.setValue(Modo.PUZZLE_PIECES);
		lvlCombo.getItems().setAll(Dificultad.values());
		lvlCombo.setValue(Dificultad.FACIL);

		// tabla
		Bindings.bindBidirectional(tableScores.itemsProperty(), marcadores);
		puestoColumn.setCellValueFactory(v -> v.getValue().puestoProperty());
		nombreColumn.setCellValueFactory(v -> v.getValue().nombreProperty());
		puntuacionColumn.setCellValueFactory(v -> v.getValue().puntuacionProperty());
		tiempoColumn.setCellValueFactory(v -> v.getValue().tiempoProperty());
		fotoColumn.setCellValueFactory(v -> v.getValue().fotoProperty());

		primaryStage.setOnCloseRequest(e -> mainController.onSalirAction(e));
	}

	@FXML
	void reseteaOnAction(ActionEvent event) {

		tableScores.getItems().clear();
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

	public final ListProperty<Marcador> marcadoresProperty() {
		return this.marcadores;
	}

	public final ObservableList<Marcador> getMarcadores() {
		return this.marcadoresProperty().get();
	}

	public final void setMarcadores(final ObservableList<Marcador> marcadores) {
		this.marcadoresProperty().set(marcadores);
	}

}
