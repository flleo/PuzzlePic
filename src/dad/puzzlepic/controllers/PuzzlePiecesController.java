package dad.puzzlepic.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
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
import javafx.scene.layout.BorderPane;

/**
 * 
 * @author fede
 *
 */
public class PuzzlePiecesController implements Initializable {

	private PuzzlePicApp app;

	@FXML
	private BorderPane view;
	
	@FXML
    private ImageView mezcla1;

    @FXML
    private ImageView mezcla2;

    @FXML
    private ImageView mezcla7;

    @FXML
    private ImageView mezcla6;

    @FXML
    private ImageView mezcla5;

    @FXML
    private ImageView mezcla4;

    @FXML
    private ImageView mezcla3;

    @FXML
    private ImageView mezcla8;

    @FXML
    private ImageView mezcla0;

    @FXML
    private ImageView mifoto1;

    @FXML
    private ImageView mifoto2;

    @FXML
    private ImageView mifoto7;

    @FXML
    private ImageView mifoto6;

    @FXML
    private ImageView mifoto5;

    @FXML
    private ImageView mifoto4;

    @FXML
    private ImageView mifoto3;

    @FXML
    private ImageView mifoto0;

    @FXML
    private ImageView mifoto8;


	//

	private MainController mainController;
	private ArrayList<File> mifotos = new ArrayList<>();	
	private ArrayList<File> troceadas = new ArrayList<>();
    private ArrayList<Integer> mezcla = new ArrayList<>();
	/**
	 * @author fede
	 * @param mainController 
	 * @param mainController
	 * @throws IOException
	 */
	public PuzzlePiecesController(MainController mainController) throws IOException {
		this.mainController = mainController;	
		
		for (File file : mainController.getFotos()) {
			this.troceadas.add(file);
		}
		//System.out.println(troceadas.length+"cantidad ppcontroller");
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/dad/puzzlepic/views/PuzzlePiecesView.fxml"));
		loader.setController(this);
		loader.load();

		initialize(null, null);
	
	}
	/**
	 * @author fede
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
				mezcla();
				
				mezcla0.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(0)).getName()));				
				mezcla1.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(1)).getName()));				
				mezcla2.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(2)).getName()));				
				mezcla3.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(3)).getName()));				
				mezcla4.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(4)).getName()));				
				mezcla5.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(5)).getName()));				
				mezcla6.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(6)).getName()));				
				mezcla7.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(7)).getName()));				
				mezcla8.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(8)).getName()));				
				
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		
		
	}
	

	/**
	 * @author fede
	 * Definicion: mezcla las fotos y actualiza los imgeViews, llamado "mezcla".
	 * @throws IOException 
	 */
	private void mezcla() throws IOException {		
		if (troceadas != null) {
			while (mezcla.size() < 9) {
				int n = (int) (Math.random() * 9 + 0);				
				if (!mezcla.contains(n)) 					
					mezcla.add(n);			
			}				
		}
	}

	@FXML
	void abandonarOnAction(ActionEvent event) {
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
