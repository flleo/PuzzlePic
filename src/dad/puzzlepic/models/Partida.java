package dad.puzzlepic.models;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
/**
 * 
 * @author isaac
 *
 */
public class Partida {

	private IntegerProperty puntuacion;
	private IntegerProperty tiempo;
	
	public Partida() {
		puntuacion = new SimpleIntegerProperty(this, "puntuacion", 0);
		tiempo = new SimpleIntegerProperty(this, "tiempoRestante");
	}

	public final IntegerProperty puntuacionProperty() {
		return this.puntuacion;
	}
	

	public final int getPuntuacion() {
		return this.puntuacionProperty().get();
	}
	

	public final void setPuntuacion(final int puntuacion) {
		this.puntuacionProperty().set(puntuacion);
	}
	

	public final IntegerProperty tiempoProperty() {
		return this.tiempo;
	}
	

	public final int getTiempo() {
		return this.tiempoProperty().get();
	}
	

	public final void setTiempo(final int tiempo) {
		this.tiempoProperty().set(tiempo);
	}
	

	


}
