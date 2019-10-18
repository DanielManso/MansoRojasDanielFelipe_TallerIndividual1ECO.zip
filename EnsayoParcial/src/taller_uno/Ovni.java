package taller_uno;

import processing.core.PApplet;
import processing.core.PImage;

public class Ovni {

	protected PApplet p;
	protected int x, y, vida;
	protected PImage ovni;

	public Ovni(PApplet p) {
		this.p = p;

		y = -50;
		x = (int) p.random(100, 1100);
		ovni = p.loadImage("../data/ovni.png");
		vida = 500;
	}

	public void pintar() {
		
		p.imageMode(p.CENTER);
		p.image(ovni, x, y);
		p.imageMode(p.CORNER);
	}

	public void mover() {

		y++;
	}

	public void morir(int golpe) {

		vida -= golpe;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getVida() {
		return vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

}
