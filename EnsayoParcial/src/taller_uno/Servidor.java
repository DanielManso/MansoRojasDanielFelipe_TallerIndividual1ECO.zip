package taller_uno;

import java.net.Socket;
import java.util.ArrayList;

import taller_uno.Receptor.OnMessage;

public class Servidor extends Thread implements Conexion.Connect, Receptor.OnMessage {

	Logica log;
	private Receptor jugador;
	private Conexion conexion;
	private int mensajito;
	private Mensaje observer;

	public Servidor(Logica log) {
		this.log = log;
		mensajito = 0;

		conexion = new Conexion();
		conexion.setObserver(this);
		conexion.start();
	}

	// Usuario que llegan de Conection
	@Override
	public void conectados(Socket socket) {

		System.out.println("Se conection");
		
		Receptor rec;
		rec = new Receptor(socket, "uno");
		rec.setObserver(this);
		rec.start();

		// A�ado al jugador "que todavia no es visible"
		jugador = rec;
		
	}

	// En este m�todo llega el mensaje de Android
	@Override
	public void recibido(String mensaje, String yo) {
		observer.enviarInfo(mensaje, yo);
	}

	public interface Mensaje {

		public void enviarInfo(String mensaje, String yo);
	}

	public void setObserver(Mensaje mensajito) {

		this.observer = mensajito;
	}

}
