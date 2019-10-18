package taller_uno;
import java.io.Console;
import java.util.logging.ConsoleHandler;

import processing.core.PApplet;

public class Main extends PApplet{
	
	Logica log;
	
	public static void main(String[] args) {
		PApplet.main("taller_uno.Main");
	}
	
	public void settings () {
		size (1200,700);
	}
	
	public void setup () {
		log = new Logica(this);
	}
	
	public void draw () {
		background(255);
		
		log.pintar();
	}
	
	public void mousePressed () {
		log.comenzar();
	}
	
	@Override
	public void keyPressed() {
		log.keyPressed();
	}
}
