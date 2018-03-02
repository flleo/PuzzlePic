package dad.puzzlepic.controllers;

import java.io.File;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;

import dad.puzzlepic.views.PuzzlePicApp;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.BorderPane;
import javafx.scene.media.AudioClip;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.FileChooser.ExtensionFilter;
import dad.puzzlepic.controllers.MarcadorController;
import dad.puzzlepic.controllers.MenuController;
import dad.puzzlepic.controllers.OpcionesPartidasController;
import dad.puzzlepic.controllers.PuzzlePiecesController;
import dad.puzzlepic.models.Jugador;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * 
 * @author fede, issac
 *
 */

public class MainController implements Initializable {

	private BorderPane vista;
	private MenuController controladorMenu;
	private MarcadorController controladorMarcador;
	private OpcionesPartidasController opcionesPartidasController;
	//private PuzzlePiecesController puzzlePiecesController;
	private MatchPuzzleController matchPuzzleController;
	private SlidingPuzzleController slidingPuzzleController;
	private AudioClip audio;
	private Jugador jugador = new Jugador();
	private Stage primaryStage;
	private ArrayList<File> fotosTroceadas = new ArrayList<>();
	private File directorioTroceadas = new File("src/dad/puzzlepic/resources/troceadas/");
	private int aciertos;
	private ArrayList<Integer> aleatorio = new ArrayList<>();
	private ArrayList<Integer> puntuaciones = new ArrayList<>();

	/**
	 * 
	 * @throws IOException
	 */

	public MainController() throws IOException {

		primaryStage = PuzzlePicApp.getPrimaryStage();
		vista = new BorderPane();
		controladorMarcador = new MarcadorController(this);
		controladorMenu = new MenuController(this);
		opcionesPartidasController = new OpcionesPartidasController(this);
		matchPuzzleController = new MatchPuzzleController(this);
		slidingPuzzleController = new SlidingPuzzleController(this);
		primaryStage.setOnCloseRequest(e->onSalirAction(e));

		initialize(null, null);

	}

	public void onSalirAction(WindowEvent e) {
		Alert alertExit = new Alert(AlertType.CONFIRMATION);
		alertExit.setTitle("PuzzlePic");
		alertExit.setHeaderText("Saliendo de PuzzlePic");
		alertExit.initOwner(PuzzlePicApp.getPrimaryStage());
		alertExit.initModality(Modality.APPLICATION_MODAL);
		alertExit.setContentText("¿Seguro que quiere salir?");

		Optional<ButtonType> result = alertExit.showAndWait();
		if (result.get() == ButtonType.OK) {
			Platform.exit();
		} else { e.consume();}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {

		audio = new AudioClip(getClass().getResource("/dad/puzzlepic/resources/getlucky.mp3").toExternalForm());
		audio.play();

		vista.setCenter(controladorMenu.getView());

		controladorMarcador.getVolverButton().setOnAction(e -> onVolverMenuButtonAction(e));
		
	}

	
	/**
	 * 
	 * @param e
	 */
	private void onVolverMenuButtonAction(ActionEvent e) {
		vista.setCenter(controladorMenu.getView());
	}
	
	/**
	 * @author fede
	 * @param directorio
	 */
	public void vaciaDirectorioTroceadas() {
		for (File listFile : directorioTroceadas.listFiles())
			listFile.delete();
	}

	/**
	 * @author fede
	 * Definicion: mezcla las fotos y actualiza los imgeViews, llamado "mezcla".
	 * @throws IOException 
	 */
	public void aleatorio() throws IOException {		
		if (fotosTroceadas != null) {
			aleatorio.clear();
			while (aleatorio.size() < 9) {
				int n = (int) (Math.random() * 9 + 0);				
				if (!aleatorio.contains(n)) 					
					aleatorio.add(n);			
			}				
		}
	}
	/**
	 * 
	 * @param title
	 * @param initialDirectory
	 * @return
	 */
	public File directorioChooser(String title, String initialDirectory) {
		DirectoryChooser fch = new DirectoryChooser();
		fch.setTitle(title);
		fch.setInitialDirectory(new File(initialDirectory));
		return fch.showDialog(primaryStage);
	}

	/**
	 * 
	 * @param title
	 * @param initialDirectory
	 * @param tipoDeFichero
	 * @param extension
	 * @return
	 */
	public File fileChooser(String title, String initialDirectory, String tipoDeFichero, String extension) {
		FileChooser fch = new FileChooser();
		fch.setTitle(title);
		fch.setInitialDirectory(new File(initialDirectory));
		fch.getExtensionFilters().addAll(new ExtensionFilter(tipoDeFichero, extension),
				new ExtensionFilter("Todos los archivos (*.*)", "*.*"));
		return fch.showOpenDialog(primaryStage);
	}

	/**
	 * 
	 * @param string
	 * @param string2
	 * @param string3
	 * @return
	 */
	public Optional<ButtonType> confirmacion(String string, String string2, String string3) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(string);
		alert.setHeaderText(string2);
		alert.setContentText(string3);
		alert.initOwner(primaryStage);

		return alert.showAndWait();
	}

	/**
	 * @
	 * @param string
	 * @param string2
	 * @param string3
	 * @return
	 */
	public Optional<ButtonType> advertencia(String string, String string2, String string3) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle(string);
		alert.setHeaderText(string2);
		alert.setContentText(string3);
		alert.initOwner(primaryStage);

		return alert.showAndWait();
	}

	/**
	 * 
	 * @param string
	 */
	public void informacion(String string) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Agenda de contactos");
		alert.setHeaderText(string);
		alert.initOwner(primaryStage);
		alert.showAndWait();

	}

	/**
	 * 
	 * @param string
	 * @param string2
	 * @param e1
	 */
	public void error(String string, String string2, Exception e1) {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle(string);
		alert.setHeaderText(string2);
		// alert.setContentText(e1.getMessage());
		alert.initOwner(primaryStage);
		alert.showAndWait();

	}

	public MarcadorController getControladorMarcador() {
		return controladorMarcador;
	}

	public OpcionesPartidasController getControladorOpciones() {
		return opcionesPartidasController;
	}


	

	public MatchPuzzleController getMatchPuzzleController() {
		return matchPuzzleController;
	}

	public SlidingPuzzleController getSlidingPuzzleController() {
		return slidingPuzzleController;
	}

	public AudioClip getAudio() {
		return audio;
	}



	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public MenuController getControladorMenu() {
		return controladorMenu;
	}

	public BorderPane getVista() {
		return vista;
	}

	public void setVista(BorderPane vista) {
		this.vista = vista;
	}

	
	public OpcionesPartidasController getOpcionesPartidasController() {
		return opcionesPartidasController;
	}

	public ArrayList<File> getFotosTroceadas() {
		return fotosTroceadas;
	}

	public void setFotosTroceadas(ArrayList<File> fotosTroceadas) {
		this.fotosTroceadas = fotosTroceadas;
	}

	public ArrayList<Integer> getAleatorio() {
		return aleatorio;
	}

	public ArrayList<Integer> getPuntuaciones() {
		return puntuaciones;
	}

	public void setPuntuaciones(ArrayList<Integer> puntuaciones) {
		this.puntuaciones = puntuaciones;
	}

	

	

}
