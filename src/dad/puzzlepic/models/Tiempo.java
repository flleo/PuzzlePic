package dad.puzzlepic.models;

public class Tiempo extends Thread {

	private Jugador jugador;
	private int tiempo;
	
	public Tiempo(Jugador jugador) {
		this.jugador = jugador;
		this.tiempo  = jugador.getTiempo();
		//jugador.tiempoProperty().bindBidirectional(this.jugador.tiempoProperty());
	}
	
	
	@Override
	public void run() {
		for (int i = 0; i < tiempo; i++) {
			try {
				Thread.sleep(1000);
				jugador.setTiempo(i);
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
	}

	
}
