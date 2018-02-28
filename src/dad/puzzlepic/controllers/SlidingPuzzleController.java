package dad.puzzlepic.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.puzzlepic.models.Jugador;
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

public class SlidingPuzzleController implements Initializable {
	/**
	 * @author Isaac
	 *
	 */

	private PuzzlePicApp app;

	@FXML
	private BorderPane view;

	@FXML
	private Button abandonarButton;

	@FXML
	private GridPane puzzlePane;

	@FXML
	private Button ceroceroButton, unoceroButton, dosceroButton;

	@FXML
	private Button cerounoButton, unounoButton, dosunoButton;

	@FXML
	private Button cerodosButton, unodosButton, dosdosButton;

	@FXML
	private ImageView pieza00, pieza10, pieza20;

	@FXML
	private ImageView pieza01, pieza11, pieza21;

	@FXML
	private ImageView pieza02, pieza12, pieza22;

	@FXML
	private Label playerLabel, timeLabel, roundLabel;

	private File foto;

	private ArrayList<File> piezasList = new ArrayList<>();
	private ArrayList<File> troceadasList = new ArrayList<>();
	private ArrayList<Integer> mezcladasList = new ArrayList<>();

	private Jugador jugador;
	private MainController mainController;
	private Stage primaryStage;

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

		playerLabel.textProperty().bind(jugador.nombreProperty());
		timeLabel.textProperty().bind(jugador.tiempoProperty().asString());
		roundLabel.textProperty().bind(jugador.rondasProperty().asString());

		foto = mainController.getOpcionesPartidasController().getFoto();
		piezasList = mainController.getOpcionesPartidasController().getFotos();

	}

	public void rescatarTroceadas() {
		for (File file : mainController.getFotosTroceadas()) {
			this.troceadasList.add(file);
		}

	}

	/**
	 * @author Isaac
	 * 
	 * <tbody>Definicion: mezcla las fotos y actualiza los imgeViews
	 *</tbody>
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

	public void posicionarMezcladas() {
		try {
			// System.out.println(troceadas.get(mezcla.get(0)).getName());
			pieza00.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(0)).getName()));
			pieza01.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(1)).getName()));
			pieza02.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(2)).getName()));
			pieza10.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(3)).getName()));
			pieza11.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(4)).getName()));
			pieza12.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(5)).getName()));
			pieza20.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(6)).getName()));
			pieza21.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(7)).getName()));
			pieza22.setImage(new Image(
					"dad/puzzlepic/resources/troceadas/" + troceadasList.get(mezcladasList.get(8)).getName()));
		} catch (Exception e) {
			mainController.advertencia("PuzzlePic", "No se ha podido cargar la foto, " + foto.getName(),
					"Pasa a la siguiente.");
		}
	}

	@FXML
	private void ceroCeroOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza01;
		ImageView movImagen2 = pieza11;
		ImageView movImagen3 = pieza00;
		ImageView movImagen4 = pieza10;

		ceroceroButton.setGraphic(movImagen1);
		cerounoButton.setGraphic(movImagen2);
		unoceroButton.setGraphic(movImagen3);
		unounoButton.setGraphic(movImagen4);
	}

	@FXML
	private void ceroDosOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza01;
		ImageView movImagen2 = pieza11;
		ImageView movImagen3 = pieza12;
		ImageView movImagen4 = pieza02;

		cerodosButton.setGraphic(movImagen3);
		cerounoButton.setGraphic(movImagen4);
		unounoButton.setGraphic(movImagen1);
		unodosButton.setGraphic(movImagen2);
	}

	@FXML
	private void dosCeroOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza20;
		ImageView movImagen2 = pieza21;
		ImageView movImagen3 = pieza11;
		ImageView movImagen4 = pieza10;

		dosceroButton.setGraphic(movImagen4);
		dosunoButton.setGraphic(movImagen1);
		unoceroButton.setGraphic(movImagen3);
		unounoButton.setGraphic(movImagen2);
	}

	@FXML
	private void dosDosOnButtonAction(ActionEvent event) {
		ImageView movImagen1 = pieza22;
		ImageView movImagen2 = pieza21;
		ImageView movImagen3 = pieza11;
		ImageView movImagen4 = pieza12;

		dosdosButton.setGraphic(movImagen2);
		dosunoButton.setGraphic(movImagen3);
		unodosButton.setGraphic(movImagen1);
		unounoButton.setGraphic(movImagen4);
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
