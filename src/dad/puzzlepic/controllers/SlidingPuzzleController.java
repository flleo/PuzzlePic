package dad.puzzlepic.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.puzzlepic.models.Jugador;
import dad.puzzlepic.models.Marcador;
import dad.puzzlepic.models.Partida;
import dad.puzzlepic.models.TroceadorImagenes;
import dad.puzzlepic.views.PuzzlePicApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class SlidingPuzzleController implements Initializable {
	/**
	 * @author Isaac
	 *
	 */

	private PuzzlePicApp app;

	@FXML
	private BorderPane view;

	@FXML
	private Label label_player;

	@FXML
	private Label label_tiempo;

	@FXML
	private Label label_puntuacion;

	@FXML
	private ImageView pieza00;

	@FXML
	private ImageView pieza10;

	@FXML
	private ImageView pieza20;

	@FXML
	private ImageView pieza01;

	@FXML
	private ImageView pieza11;

	@FXML
	private ImageView pieza21;

	@FXML
	private ImageView pieza02;

	@FXML
	private ImageView pieza12;

	@FXML
	private ImageView pieza22;

	///

	private ArrayList<File> fotos = new ArrayList<>();
	private File foto;
	private ArrayList<Integer> aleatorio = new ArrayList<>();
	private ArrayList<File> piezasList = new ArrayList<>();
	private ArrayList<File> troceadasList = new ArrayList<>();
	private ArrayList<Integer> mezcladasList = new ArrayList<>();
	private Marcador marcador = new Marcador();
	private Partida partida = new Partida();
	private Jugador jugador;
	private MainController mainController;
	private Stage primaryStage;
	private TroceadorImagenes troceadorImagenes = new TroceadorImagenes();
	private ArrayList<File> fotosTroceadas = new ArrayList<>();

	/**
	 * @param mainController
	 * @param mainController
	 * @throws IOException
	 **/

	public SlidingPuzzleController(MainController mainController) throws IOException {
		this.mainController = mainController;
		this.jugador = mainController.getJugador();
		this.primaryStage = PuzzlePicApp.getPrimaryStage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dad/puzzlepic/views/SlidingPuzzleView.fxml"));
		loader.setController(this);
		loader.load();

		initialize(null, null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		primaryStage.setOnCloseRequest(e -> mainController.onSalirAction(e));

		primaryStage.setOnCloseRequest(e -> mainController.onSalirAction(e));

		// Bindeos etiquetas
		label_player.textProperty().bind(jugador.nombreProperty());
		label_tiempo.textProperty().bind(jugador.tiempoProperty().asString());
		label_puntuacion.textProperty().bind(partida.puntuacionProperty().asString());

		foto = mainController.getOpcionesPartidasController().getFoto();
		piezasList = mainController.getOpcionesPartidasController().getFotos();

	}

	public void rescatarTroceadas() {
		for (File file : mainController.getFotosTroceadas()) {
			this.troceadasList.add(file);
		}

	}

	/**
	 * @author fede
	 * @param event
	 */
	@FXML
	void siguienteOnAction(ActionEvent event) {

		try {
			mainController.vaciaDirectorioTroceadas();
			seleccionaFoto();
			mainController.setFotosTroceadas(troceadorImagenes.trocearFoto(foto, 3));

			aleatorio = mainController.getAleatorio();
			bindeaMezcla();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @author fede
	 */
	public void bindeaMezcla() {
		aleatorio = mainController.getAleatorio();
		fotosTroceadas = mainController.getFotosTroceadas();
		System.out.println(foto.getName());
		try {
			pieza00.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(0)).getName()));
			pieza01.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(1)).getName()));
			pieza02.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(2)).getName()));
			pieza10.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(3)).getName()));
			pieza11.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(4)).getName()));
			pieza12.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(5)).getName()));
			pieza20.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(6)).getName()));
			pieza21.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(7)).getName()));
			pieza22.setImage(
					new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(8)).getName()));
		} catch (Exception e) {
			mainController.advertencia("PuzzlePic", "No se ha podido cargar la foto, " + foto.getName(),
					"Pasa a la siguiente.");
		}
	}

	/**
	 * @author fede
	 */
	private void seleccionaFoto() {
		fotos.remove(foto);
		int size = fotos.size();
		if (size != 0) {
			int seleccionada = (int) (Math.random() * size + 0);
			foto = fotos.get(seleccionada);
		} else {
			mainController.advertencia("PuzzlePic", "Has completado todas las fotos", "Vuelve a seleccionar carpeta");
		}
	}

	/**
	 * @author fede
	 * @throws IOException
	 */
	public void mezcla() throws IOException {
		if (troceadasList != null) {
			mezcladasList.clear();
			while (mezcladasList.size() < 9) {
				int n = (int) (Math.random() * 9 + 0);
				if (!mezcladasList.contains(n))
					mezcladasList.add(n);
			}
		}
	}

	

	@FXML
	private void ceroCeroOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza01;
		ImageView movImagen2 = pieza11;
		ImageView movImagen3 = pieza00;
		ImageView movImagen4 = pieza10;

		
	}

	@FXML
	private void ceroDosOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza01;
		ImageView movImagen2 = pieza11;
		ImageView movImagen3 = pieza12;
		ImageView movImagen4 = pieza02;

	
	}

	@FXML
	private void dosCeroOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza20;
		ImageView movImagen2 = pieza21;
		ImageView movImagen3 = pieza11;
		ImageView movImagen4 = pieza10;

		
	}

	@FXML
	private void dosDosOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza22;
		ImageView movImagen2 = pieza21;
		ImageView movImagen3 = pieza11;
		ImageView movImagen4 = pieza12;

		
	}

	@FXML
	private void abandonarOnAction(ActionEvent event) {
		mainController.getVista().setCenter(mainController.getControladorMenu().getView());
	}

	public BorderPane getView() {
		return view;
	}

	public PuzzlePicApp getApp() {
		return app;
	}

	public void setApp(PuzzlePicApp app) {
		this.app = app;
	}

}
