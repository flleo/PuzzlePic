package dad.puzzlepic.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import dad.puzzlepic.models.Jugador;
import dad.puzzlepic.models.TroceadorImagenes;
import dad.puzzlepic.views.PuzzlePicApp;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.BorderPane;
import javafx.scene.transform.TransformChangedEvent;
import javafx.stage.Stage;

/**
 * 
 * @author fede
 *
 */
public class PuzzlePiecesController implements Initializable {

	
	

    @FXML
    private Button button_mezcla1;

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

    @FXML
    private Label label_player,label_ronda,label_tiempo;
	//


	private MainController mainController;
	private TroceadorImagenes troceadorImagenes = new TroceadorImagenes();
	private ArrayList<File> troceadas = new ArrayList<>();
    private ArrayList<Integer> mezcla = new ArrayList<>();
	private ImageView mifotoClicked=new ImageView(),mezclaClicked;
	private ImageView cambioImagen= new ImageView();
	private int aciertos;
	private Jugador jugador;
	private Stage primaryStage;
	private ArrayList<File> fotos = new ArrayList<>();
	private int ganadas;
	private File foto;
	private int size ;
	private File directorioTroceadas = new File("src/dad/puzzlepic/resources/troceadas/");
	/**
	 * @author fede
	 * @param mainController 
	 * @param mainController
	 * @throws IOException
	 */
	public PuzzlePiecesController(MainController mainController) throws IOException {
		this.mainController = mainController;	
		this.jugador  = mainController.getJugador();
		this.primaryStage = PuzzlePicApp.getPrimaryStage();
	
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
	
		foto = mainController.getOpcionesPartidasController().getFoto();
    	fotos = mainController.getOpcionesPartidasController().getFotos();
		
		//Bindeos etiquetas
		label_player.textProperty().bind(jugador.nombreProperty());
		label_tiempo.textProperty().bind(jugador.tiempoProperty().asString());
		label_ronda.textProperty().bind(jugador.rondasProperty().asString());
		
		primaryStage.setOnCloseRequest(e->mainController.onSalirAction(e));
	}
	
	
	
	private void receteaMiFoto() {
		mifoto0.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));				
		mifoto1.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		mifoto2.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		mifoto3.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		mifoto4.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		mifoto5.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		mifoto6.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		mifoto7.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		mifoto8.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));	
		
	}
	/**
	 * @author fede
	 * rescatamos la foto con uan nueva variacion de troceadas
	 */
	public void rescatarTroceadas() {
		aciertos=0;
		receteaMiFoto();
		troceadas.clear();
		for (File file : mainController.getFotosTroceadas()) 
			this.troceadas.add(file);
	}
	/**
	 * @author fede
	 * Definicion: mezcla las fotos y actualiza los imgeViews, llamado "mezcla".
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
		System.out.println(troceadas.get(mezcla.get(0)).getName());
		mezcla0.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(0)).getName()));				
		mezcla1.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(1)).getName()));				
		mezcla2.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(2)).getName()));				
		mezcla3.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(3)).getName()));				
		mezcla4.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(4)).getName()));				
		mezcla5.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(5)).getName()));				
		mezcla6.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(6)).getName()));				
		mezcla7.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(7)).getName()));				
		mezcla8.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ troceadas.get(mezcla.get(8)).getName()));				
		} catch (Exception e) {
			mainController.advertencia("PuzzlePic", "No se ha podido cargar la foto, " + foto.getName(), "Pasa a la siguiente.");
		}
	}
	

    @FXML
    void mezclaClick(MouseEvent event) {    	
   	    mezclaClicked = (ImageView) event.getSource();
    	cambioImagen.setImage(mezclaClicked.getImage());   
    }
    
    /**
     * @author fede
     * @param event
     */
    @FXML
    void mifotoClick(MouseEvent event) {
    	//Definimos la cuadricula de mifoto
   	    mifotoClicked = (ImageView) event.getSource();
   	    File cuadriculaId = new File(mifotoClicked.getId());
   	    //Le asignamos la nueva foto
    	mifotoClicked.setImage(cambioImagen.getImage());
    	//Asignamos la interrogacion por si quiere quitar la foto
    	cambioImagen.setImage(new Image("dad/puzzlepic/resources/interrogation.jpg"));       
        System.out.println(cuadriculaId.getName());  
        //Sacamos el indice de la foto mezclada
        File mezclaId = new File(mezclaClicked.getImage().impl_getUrl());
        String idMezcla = mezclaId.getName().substring(0, 1);
        //Sacamos el indice de la cuadricula de mi foto
        String idS = cuadriculaId.getName().substring(cuadriculaId.getName().length()-1);   
   	 	int idI = Integer.parseInt(idS);
        System.out.println(idMezcla+"="+idI);
        //Comparamos si corresponde indice de la mezcla con el de la original
        if(idMezcla.equals(troceadas.get(idI).getName().substring(0, 1))) {
    		System.out.println(idS + "=" + troceadas.get(idI).getName().substring(0, 1));
    		//Si coincide validamos la mezcla y desabilitamos la imagen correcta
    		mezclaClicked.setImage(new Image("dad/puzzlepic/resources/ok.png"));
    		mezclaClicked.setDisable(true);
    		mifotoClicked.setDisable(true);
    		aciertos++;
    		if(aciertos==9) ganadas++;
    		
    			
    	}
    	
    }
    
    private void seleccionaFoto() {
		fotos.remove(foto);
		size = fotos.size();
		if(size!=0) {
		int seleccionada = (int) (Math.random() * size + 0);
		foto = fotos.get(seleccionada);
		} else {
			mainController.advertencia("PuzzlePic", "Has completado todas las fotos", "Vuelve a seleccionar carpeta");
		}
    }
	
    @FXML
    void siguienteOnAction(ActionEvent event) {   	    
		
		try {
			mainController.vaciaDirectorioTroceadas();
			seleccionaFoto();
			mainController.setFotosTroceadas(troceadorImagenes.trocearFoto(foto, 3));
			rescatarTroceadas();
			mezcla();
			bindeaMezcla();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    
	@FXML
	void abandonarOnAction(ActionEvent event) {
		mainController.getVista().setCenter(mainController.getControladorMenu().getView());
		
	}

	public BorderPane getView() {
		return view;
	}

	

}
