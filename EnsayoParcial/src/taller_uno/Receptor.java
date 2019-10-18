package taller_uno;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class Receptor extends Thread{
	
	Socket s;
	OnMessage observer;
	String yo;
	
	public Receptor ( Socket s, String yo ) {
		this.s = s;
		this.yo=yo;
	}
	
	@Override
	public void run() {
		
		try {
			
			InputStream is = s.getInputStream();
			BufferedReader lector = new BufferedReader ( new InputStreamReader (is) );
			
			while (true) {
				
				String mensaje = lector.readLine();
				observer.recibido(mensaje, yo);
			}
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public interface OnMessage {
		
		public void recibido ( String mensaje, String yo );
	}
	
	public void setObserver ( OnMessage mensajito ) {
		
		this.observer = mensajito;
	}
	
	
}
