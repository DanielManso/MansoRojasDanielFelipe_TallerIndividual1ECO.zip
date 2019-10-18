package taller_uno;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Personaje {

	private PApplet p;
	private int y, x;
	private PImage per1;

	public Personaje(PApplet p) {
		this.p = p;
		y = 539;
		x = 575;
		per1 = p.loadImage("../data/killer.png");
	}

	public void pintarUno() {
		p.imageMode(p.CENTER);
		p.image(per1, x, y);
		p.imageMode(p.CORNER);
	}

	public void moverIzq() {
		if (x - 50 > 100) {
			x -= 50;
		}
	}

	public void moverDer() {
		if (x + 50 < 1125) {
			x += 50;
		}
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}
}
