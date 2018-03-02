package dad.puzzlepic.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Properties;
import java.util.ResourceBundle;

import dad.puzzlepic.models.Dificultad;
import dad.puzzlepic.models.Jugador;
import dad.puzzlepic.models.Marcador;
import dad.puzzlepic.models.Modo;
import dad.puzzlepic.models.TroceadorImagenes;
import dad.puzzlepic.views.PuzzlePicApp;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class OpcionesPartidasController implements Initializable {

	@FXML
	private BorderPane view;

	@FXML
	private Spinner<Integer> timeSpinner;

	@FXML
	private ComboBox<Dificultad> lvlCombo;

	@FXML
	private Button openButton;

	@FXML
	private Button backButton;

	@FXML
	private Button continueButton;

	@FXML
	private TextField playerField;

	@FXML
	private Label directorioLabel;

	@FXML
	private ComboBox<Modo> comboGame;

	//

	private PuzzlePiecesController puzzlePiecesController;
	private ObjectProperty <File> selectedDirectory = new SimpleObjectProperty<>();
	private MainController mainController;
	private TroceadorImagenes troceadorImagenes = new TroceadorImagenes();
	private Jugador jugador;
	private File foto = null;
	private Stage primaryStage;
	private ArrayList<File> fotos = new ArrayList<>();
	

	public OpcionesPartidasController(MainController mainController) throws IOException {
		this.mainController = mainController;
		this.primaryStage = PuzzlePicApp.getPrimaryStage();
		this.jugador = mainController.getJugador();
		

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dad/puzzlepic/views/OpcionesPartidasView.fxml"));
		loader.setController(this);
		loader.load();
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		lvlCombo.getItems().setAll(Dificultad.values());
		SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(30, 180, 30);
		timeSpinner.setValueFactory(valueFactory);
		lvlCombo.setValue(Dificultad.FACIL);
		comboGame.getItems().setAll(Modo.values());
		comboGame.setValue(Modo.PUZZLE_PIECES);

		// Bindeos
		jugador.nombreProperty().bind(playerField.textProperty());
		jugador.tiempoProperty().bind(timeSpinner.valueProperty());
		jugador.dificultadProperty().bind(lvlCombo.valueProperty());
		jugador.modoProperty().bind(comboGame.valueProperty());
		jugador.directorioProperty().bind(selectedDirectory);
		directorioLabel.textProperty().bind(selectedDirectoryProperty().asString());

		// Acciones
		backButton.setOnAction(e -> onVolverMenuButtonAction(e));
		continueButton.setOnAction(e -> {try {
			onIniciarPartidaButtonAction(e);
		} catch (IOException e1) {
			mainController.error("PuzzlePic", "La foto introducida no es válida", e1);			
		}});
		openButton.setOnAction(e -> onAbrirButtonAction(e));
		primaryStage.setOnCloseRequest(e->mainController.onSalirAction(e));

	}

	private void onVolverMenuButtonAction(ActionEvent e) {
		
		mainController.getVista().setCenter(mainController.getControladorMenu().getView());
	}

	private void onAbrirButtonAction(ActionEvent e) {
		try {
		    
			selectedDirectory.set(mainController.directorioChooser("Selecciona carpeta de imagenes", "."));
			if (!selectedDirectory.get().isDirectory()) {
				mainController.error("Error de seleeción", "Has de seleccionar una carpeta.", null);
			}
		} catch (NullPointerException e1) {
			mainController.error("Error de seleeción", "No se puedo cargar la carpeta, de imagenes", e1);
		}
	}
/*
	private void guardaFoto() {
		File fotosJugador = new File("jugadores/" + jugador.getNombre() + "fotos/" + selectedDirectory.get().getName());
		if(!fotosJugador.exists())
			fotosJugador.mkdir();
		selectedDirectory.get().renameTo(fotosJugador);
		
	}
*/
	@FXML
	private void onIniciarPartidaButtonAction(ActionEvent e) throws IOException   {
		if (jugador.getNombre().equals("") || selectedDirectory.get().getName().equals("")) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Error!!");
			alert.setHeaderText("Algo ha fallado...");
			alert.setContentText("Por favor, revise los campos.");
			alert.initModality(Modality.APPLICATION_MODAL);
			alert.initOwner(mainController.getPrimaryStage());
			alert.showAndWait();

		} else {
			seleccionarFoto();
			
				switch (lvlCombo.getValue()) {

				case FACIL:
					System.out.println("FACIL");
					mainController.vaciaDirectorioTroceadas();
					mainController.setFotosTroceadas(troceadorImagenes.trocearFoto(foto, 3));
					
					break;
					
				case MEDIA:
					mainController.vaciaDirectorioTroceadas();
					mainController.setFotosTroceadas(troceadorImagenes.trocearFoto(foto, 3));
				break;

				case DIFICIL:
					mainController.vaciaDirectorioTroceadas();
					mainController.setFotosTroceadas(troceadorImagenes.trocearFoto(foto, 3));
				break;

				default:
					break;
				}
		

			
			switch (mainController.getControladorOpciones().getComboGame().getValue()) {
			case PUZZLE_PIECES:
				System.out.println("PUZLE PIECES");
				mainController.aleatorio();
				puzzlePiecesController = new PuzzlePiecesController(mainController);
				puzzlePiecesController.bindeaMezcla();
				mainController.getVista().setCenter(puzzlePiecesController.getView());								
				
				
				break;
			case MATCH_PUZZLE:
				System.out.println("MATCH PUZZLE");
				mainController.getVista().setCenter(mainController.getMatchPuzzleController().getView());
				break;

			case SLIDING_PUZZLE:
				System.out.println("SLIDING PUZZLE");
				mainController.getVista().setCenter(mainController.getSlidingPuzzleController().getView());
				break;

			default:
				break;
			}
		}
	}

	
	private void seleccionarFoto()  {
		fotos.clear();
	    for (File file : selectedDirectory.get().listFiles()) {
			fotos.add(file);
		}
		int size = fotos.size();
		int seleccionada = (int) (Math.random() * size + 0);
		foto = fotos.get(seleccionada);
		System.out.println(foto.getName()+"seleccionarfoto opcione...Cantidad de fotos en carpeta:"+fotos.size());

	}

	public BorderPane getView() {
		return view;
	}

	public void setView(BorderPane view) {
		this.view = view;
	}

	public Spinner<Integer> getTimeSpinner() {
		return timeSpinner;
	}

	public ComboBox<Dificultad> getLvlCombo() {
		return lvlCombo;
	}

	public Button getOpenButton() {
		return openButton;
	}

	public Button getBackButton() {
		return backButton;
	}

	public Button getContinueButton() {
		return continueButton;
	}

	public TextField getPlayerField() {
		return playerField;
	}

	public ComboBox<Modo> getComboGame() {
		return comboGame;
	}

	public Label getDirectorioLabel() {
		return directorioLabel;
	}

	public final ObjectProperty<File> selectedDirectoryProperty() {
		return this.selectedDirectory;
	}

	public final File getSelectedDirectory() {
		return this.selectedDirectoryProperty().get();
	}

	public final void setSelectedDirectory(final File selectedDirectory) {
		this.selectedDirectoryProperty().set(selectedDirectory);
	}

	public ArrayList<File> getFotos() {
		return fotos;
	}

	public File getFoto() {
		return foto;
	}

	
	
}
