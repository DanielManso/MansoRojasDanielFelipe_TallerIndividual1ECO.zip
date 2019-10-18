package taller_uno;

import processing.core.PApplet;
import processing.core.PImage;

public class BalaPer extends Thread{
	
	private PApplet p;
	private int x, y, daño;
	private PImage bala;
	private boolean eliminame;

	
	BalaPer(PApplet p, int x) {
		this.p = p;
		this.x = x;
		bala = p.loadImage("../data/bala_1.png");
		y = 509;
	}
	
	@Override
	public void run() {
		while (eliminame == false) {
			try {
				mover();
				seFue();
				sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void pintarNormal () {
		p.imageMode(p.CENTER);
		p.image(bala, x, y);
		p.imageMode(p.CORNER);
	}

	public void mover () {
		y -= 30;
	}
	
	public void seFue() {
		if(this.y<-20) {
			eliminame=true;
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

	public boolean isEliminame() {
		return eliminame;
	}

	public void setEliminame(boolean eliminame) {
		this.eliminame = eliminame;
	}

	public int getDaño() {
		return daño;
	}

	public void setDaño(int daño) {
		this.daño = daño;
	}
	
	
}
