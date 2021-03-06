package dad.puzzlepic.controllers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;

import dad.puzzlepic.models.Jugador;
import dad.puzzlepic.models.Marcador;
import dad.puzzlepic.models.Partida;
import dad.puzzlepic.models.Tiempo;
import dad.puzzlepic.models.TroceadorImagenes;
import dad.puzzlepic.views.PuzzlePicApp;
import javafx.animation.Timeline;
import javafx.beans.property.ObjectProperty;
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
import javafx.util.Duration;

/**
 * 
 * @author fede
 *
 */
public class PuzzlePiecesController  implements Initializable {

	
	

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
    private Label label_player,label_puntuacion,label_tiempo;
	//


	private MainController mainController;
	private TroceadorImagenes troceadorImagenes = new TroceadorImagenes();
	private ArrayList<File> fotosTroceadas = new ArrayList<>();
    private ArrayList<Integer> aleatorio = new ArrayList<>();
	private ImageView mifotoClicked=new ImageView(),mezclaClicked;
	private ImageView cambioImagen= new ImageView();
	private Jugador jugador;
	private Stage primaryStage;
	private ArrayList<File> fotos = new ArrayList<>();
	private File foto = new File("");
	private int size ;
	private Marcador marcador = new Marcador();
	private Partida partida = new Partida();
	private MarcadorController marcadorController;
	private ArrayList<ImageView> mifotoClickes = new ArrayList<>();
	private ArrayList<ImageView> mezclaClickes = new ArrayList<>();
	private ArrayList<Marcador> marcadores = new ArrayList<>();
	private String puesto = "1";
//	private Tiempo tiempo;
	/**
	 * @author fede
	 * @param mainController 
	 * @param mainController
	 * @throws IOException
	 */
	public PuzzlePiecesController(MainController mainController)  throws IOException {
		this.mainController = mainController;	
		this.jugador  = mainController.getJugador();
	//	this.tiempo  = new Tiempo(jugador);
		this.primaryStage = PuzzlePicApp.getPrimaryStage();
		this.marcadorController = mainController.getControladorMarcador();
	
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
		receteaMiFoto();
		foto = mainController.getOpcionesPartidasController().getFoto();
    	fotos = mainController.getOpcionesPartidasController().getFotos();
		
		//Bindeos etiquetas
		label_player.textProperty().bind(jugador.nombreProperty());
		label_tiempo.textProperty().bind(jugador.tiempoProperty().asString());
		label_puntuacion.textProperty().bind(partida.puntuacionProperty().asString());
		
		
		
		primaryStage.setOnCloseRequest(e->mainController.onSalirAction(e));
	}
	
	
	
	/**
	 * @author fede
	 */
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
		
		for (int i = 0; i < mezclaClickes.size(); i++) {
			mifotoClickes.get(i).setDisable(false);
			mezclaClickes.get(i).setDisable(false);
		}	
		
	
		
	}
	
	/**
	 * @author fede
	 */
	private void nuevoMarcador() {
		
		marcador = new Marcador();
		marcador.setFoto(foto.getName());
		marcador.setPuntuacion(String.valueOf(partida.getPuntuacion()));
		marcador.setNombre(jugador.getNombre());			
		marcador.setTiempo(String.valueOf(partida.getTiempo()));
		marcador.setPuesto("1");
		marcadorController.marcadoresProperty().add(marcador);
		calculaPuestoYActualizaValores(marcador);
	}
	/**
	 * @author fede
	 * @return
	 */
	private void calculaPuestoYActualizaValores(Marcador marcador) {
		ArrayList<Marcador> marcadores1  =new ArrayList<>();
		ArrayList<Marcador> marcadores0 = new ArrayList<>();
		for (Marcador marca : marcadorController.marcadoresProperty()) 
			marcadores0.add(marca);
		marcadores0.add(marcador);
		int max=1000;
		for (int i = 0; i < marcadores0.size(); i++) {
			if(Integer.parseInt(marcadores0.get(i).getPuntuacion()) < max) {
				max = Integer.parseInt(marcadores0.get(i).getPuntuacion());
				marcadores1.add(marcadores0.get(i));
				marcadores1.get(i).setPuesto(String.valueOf(i+1));
			}
		}
		marcadorController.marcadoresProperty().clear();
		for (int i = 0; i < marcadores1.size(); i++) {
			marcadorController.marcadoresProperty().add(marcadores1.get(i));	
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
		mezcla0.setImage(new Image("dad/puzzlepic/resources/troceadas/" + fotosTroceadas.get(aleatorio.get(0)).getName()));		
		mezcla1.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(1)).getName()));					
		mezcla2.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(2)).getName()));				
		mezcla3.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(3)).getName()));				
		mezcla4.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(4)).getName()));			
		mezcla5.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(5)).getName()));				
		mezcla6.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(6)).getName()));				
		mezcla7.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(7)).getName()));				
		mezcla8.setImage(new Image("dad/puzzlepic/resources/troceadas/"+ fotosTroceadas.get(aleatorio.get(8)).getName()));	
		} catch (Exception e) {
			mainController.advertencia("PuzzlePic", "No se ha podido cargar la foto, " + foto.getName(), "Pasa a la siguiente.");
		}
	}
	
	/**
	 * @author fede
	 * @param event
	 */
    @FXML
    void mezclaClick(MouseEvent event) { 
   	    mezclaClicked = (ImageView) event.getSource();
    	cambioImagen.setImage(mezclaClicked.getImage());   
    }
    
    /**
     * @author fede
     * @param event
     * @throws InterruptedException 
     */
    @FXML
    void mifotoClick(MouseEvent event)  throws InterruptedException {
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
        if(idMezcla.equals(fotosTroceadas.get(idI).getName().substring(0, 1))) {
    		System.out.println(idS + "=" + fotosTroceadas.get(idI).getName().substring(0, 1));
    		//Si coincide validamos la mezcla y desabilitamos la imagen correcta
    		mezclaClicked.setImage(new Image("dad/puzzlepic/resources/ok.png"));
    		mezclaClicked.setDisable(true);
    		mezclaClickes.add(mezclaClicked);
    		mifotoClicked.setDisable(true);
    		mifotoClickes.add(mifotoClicked);
    		partida.setPuntuacion(partida.getPuntuacion()+10);
    		
    	
    			
    	} else {
    		mifotoClicked.setImage(new Image("dad/puzzlepic/resources/bad.png"));
    		if(partida.getPuntuacion()>2)
    			partida.setPuntuacion(partida.getPuntuacion()-3);	
    	}
    	
    }
    /**
     * @author fede
     */
    private void seleccionaFoto() {
		fotos.remove(foto);
		size = fotos.size();
		if(size!=0) {
		int seleccionada = (int) (Math.random() * size + 0);
		foto = fotos.get(seleccionada);
		} else {
			mainController.advertencia("PuzzlePic", "Has completado todas las fotos", "Vuelve a seleccionar carpeta");
			receteaMiFoto();
		}
    }
	
    /**
     * @author fede
     * @param event
     */
    @FXML
    void siguienteOnAction(ActionEvent event) {   	    
    	receteaMiFoto();
    	nuevoMarcador();
		try {
			mainController.vaciaDirectorioTroceadas();
			seleccionaFoto();
			mainController.setFotosTroceadas(troceadorImagenes.trocearFoto(foto, 3));
			//partida=new Partida();
			aleatorio = mainController.getAleatorio();
			bindeaMezcla();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
    }
    /**
     * @author fede
     * @param event
     */
	@FXML
	void abandonarOnAction(ActionEvent event) {
		nuevoMarcador();
		receteaMiFoto();
		//partida = new Partida();
		mainController.getVista().setCenter(mainController.getControladorMenu().getView());
		
	}

	public BorderPane getView() {
		return view;
	}

	

}
