package dad.puzzlepic.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import dad.puzzlepic.views.PuzzlePicApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
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
	private Button ceroceroButton, unoceroButton, dosceroceroButton;

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

	private ArrayList<File> piezas = new ArrayList<>();
	private ArrayList<File> troceadas = new ArrayList<>();
	private ArrayList<Integer> mezcla = new ArrayList<>();

	/**
	 * 
	 * 
	 * 
	 **/

	private MainController mainController;
	private Stage primaryStage;

	public SlidingPuzzleController(MainController mainController) throws IOException {
		this.mainController = mainController;
		this.primaryStage = PuzzlePicApp.getPrimaryStage();

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dad/puzzlepic/views/SlidingPuzzleView.fxml"));
		loader.setController(this);
		loader.load();

		initialize(null, null);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		primaryStage.setOnCloseRequest(e->mainController.onSalirAction(e));

		
	}

	public void rescatarTroceadas() {
		for (File file : mainController.getFotos()) {
			this.troceadas.add(file);
		}

	}

	/**
	 * @author Isaac Definicion: mezcla las fotos y actualiza los imgeViews, llamado "mezcla".
	 * 
	 * @throws IOException
	 */
	public void mezcla() throws IOException {
		if (troceadas != null) {
			mezcla.clear();
			while (mezcla.size() < 9) {
				int n = (int) (Math.random() * 9 + 0);
				if (!mezcla.contains(n))
					mezcla.add(n);
			}
		}
	}

	public void bindeaMezcla() {
		try {

			// System.out.println(troceadas.get(mezcla.get(0)).getName());
			pieza00.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(0)).getName()));
			pieza01.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(1)).getName()));
			pieza02.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(2)).getName()));
			pieza10.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(3)).getName()));
			pieza11.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(4)).getName()));
			pieza12.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(5)).getName()));
			pieza20.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(6)).getName()));
			pieza21.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(7)).getName()));
			pieza22.setImage(new Image("dad/puzzlepic/resources/troceadas/" + troceadas.get(mezcla.get(8)).getName()));
		} catch (Exception e) {
		}

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
