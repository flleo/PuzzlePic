package dad.puzzlepic.models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Marcador {

	private StringProperty puesto,nombre,puntuacion,tiempo,foto;
	
	
	public Marcador() {
		puesto = new SimpleStringProperty(this, "puesto");
		nombre = new SimpleStringProperty(this, "nombre");
		puntuacion = new SimpleStringProperty(this, "puntuacion");
		tiempo = new SimpleStringProperty(this, "tiempo");
		foto = new SimpleStringProperty(this, "foto");
	}


	public final StringProperty puestoProperty() {
		return this.puesto;
	}
	


	public final String getPuesto() {
		return this.puestoProperty().get();
	}
	


	public final void setPuesto(final String puesto) {
		this.puestoProperty().set(puesto);
	}
	


	public final StringProperty nombreProperty() {
		return this.nombre;
	}
	


	public final String getNombre() {
		return this.nombreProperty().get();
	}
	


	public final void setNombre(final String nombre) {
		this.nombreProperty().set(nombre);
	}
	

	public final StringProperty tiempoProperty() {
		return this.tiempo;
	}
	


	public final String getTiempo() {
		return this.tiempoProperty().get();
	}
	


	public final void setTiempo(final String tiempo) {
		this.tiempoProperty().set(tiempo);
	}
	


	public final StringProperty fotoProperty() {
		return this.foto;
	}
	


	public final String getFoto() {
		return this.fotoProperty().get();
	}
	


	public final void setFoto(final String foto) {
		this.fotoProperty().set(foto);
	}


	public final StringProperty puntuacionProperty() {
		return this.puntuacion;
	}
	


	public final String getPuntuacion() {
		return this.puntuacionProperty().get();
	}
	


	public final void setPuntuacion(final String puntuacion) {
		this.puntuacionProperty().set(puntuacion);
	}
	
	




	
}
