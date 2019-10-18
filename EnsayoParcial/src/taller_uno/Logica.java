package taller_uno;

import java.util.ArrayList;

import processing.core.PApplet;
import processing.core.PImage;

public class Logica implements Servidor.Mensaje {

	private PApplet p;
	Servidor server;

	private Personaje shooter1;
	private ArrayList<BalaPer> balasPer;
	private ArrayList<Ovni> ovnis;

	// Declaro im�genes
	private PImage p1, p2, p3, p4, juego, perder, ganar;

	// Declaro variables
	private int msj, pantalla, tiempo, restar, puntos, balas;
	private boolean enJuego;

	public Logica(PApplet p) {

		this.p = p;

		// Conection con Android
		server = new Servidor(this);
		server.setObserver(this);
		server.start();

		// Inicializo los objetos
		shooter1 = new Personaje(p);
		ovnis = new ArrayList<>();
		balasPer = new ArrayList<BalaPer>();

		// Inicializo variables
		msj = 0;
		pantalla = 0;
		tiempo = 0;
		restar = 0;
		puntos = 0;
		balas = 7;
		enJuego = false;

		// Cargo las im�genes
		p1 = p.loadImage("../data/pantalla_1.jpg");
		p2 = p.loadImage("../data/ins_1.jpg");
		p3 = p.loadImage("../data/ins_2.jpg");
		p4 = p.loadImage("../data/jugar.jpg");
		juego = p.loadImage("../data/pantalla_3.jpg");
		perder = p.loadImage("../data/perder.jpg");
		ganar = p.loadImage("../data/ganar.jpg");

	}

	public void pintar() {
		switch (pantalla) {

		case 0:
			p.image(p1, 0, 0);

			break;

		case 1:
			p.image(p2, 0, 0);
			break;

		case 2:
			p.image(p3, 0, 0);
			break;

		case 3:
			p.image(p4, 0, 0);
			break;

		case 4:
			tiempo = (p.millis() - restar) / 1000;
			p.image(juego, 0, 0);
			shooter1.pintarUno();

			for (int i = 0; i < ovnis.size(); i++) {
				ovnis.get(i).pintar();
				ovnis.get(i).mover();
			}

			// Metodos internos
			matarOvnis();
			perder();
			pintarBalas();
			// generarOvnis();
			ganar();

			// Pinto el tiempo y puntos
			p.fill(255);
			p.textSize(20);
			p.text(tiempo + "", 240, 55);
			p.text(puntos, 1025, 55);
			p.fill(0);
			p.text(balas, 650, 652);
			break;

		case 5:
			p.image(ganar, 0, 0);
			// Pinto el balas y puntos
			p.fill(255);
			p.textSize(50);
			p.text(puntos, 670, 370);
			p.text(balas, 670, 470);
			break;

		case 6:
			p.fill(255);
			p.textSize(50);
			p.image(perder, 0, 0);
			// Pinto el balas y puntos
			p.text(puntos, 670, 370);
			p.text(balas, 670, 470);
			break;
		}
	}

	// El juego comienza con ovnis
	public void iniciando() {
		enJuego = true;
		for (int i = 0; i < 18; i++) {
			Ovni ovniPequeno = new Ovni(p);
			ovniPequeno.setY(-200);
			if (i > 9)
				ovniPequeno.setY(-100);
			if (i > 15)
				ovniPequeno.setY(0);

			ovniPequeno.setX(i * 100 + 150);
			if (i > 9)
				ovniPequeno.setX((i - 9) * 100 + 270);
			if (i > 15)
				ovniPequeno.setX((i - 15) * 100 + 450);
			ovnis.add(ovniPequeno);
		}
	}

	public void matarOvnis() {
		for (int i = 0; i < balasPer.size(); i++) {
			BalaPer b = balasPer.get(i);

			for (int j = 0; j < ovnis.size(); j++) {
				Ovni o = ovnis.get(j);

				if (p.dist(b.getX(), b.getY(), o.getX(), o.getY()) < 50) {
					o.morir(50);
					balasPer.remove(i);

					if (o.getVida() == 0) {
						ovnis.remove(j);
					}
					puntos++;
				}
			}
		}
	}

	public void perder() {
		for (int i = 0; i < ovnis.size(); i++) {

			if (ovnis.get(i).getY() >= 500) {

				ovnis.remove(i);
				enJuego = false;
				pantalla = 6;

				ovnis.removeAll(ovnis);
			}
		}
	}

	public void ganar() {
		if (puntos >= 7) {
			pantalla = 5;
		}
	}

	public void pintarBalas() {
		if (balasPer != null) {
			for (int i = 0; i < balasPer.size(); i++) {
				balasPer.get(i).pintarNormal();

				if (balasPer.get(i).isEliminame() == true) {
					balasPer.get(i).interrupted();
					balasPer.remove(i);
					return;

				}
			}
		}
	}
	/*
	 * public void generarOvnis() { if (enJuego = true) { if (p.frameCount % 180 ==
	 * 0) { ovnis.add(new Ovni(p)); } } }
	 */

	public void comenzar() {
		// System.out.println(p.mouseX + "," + p.mouseY);
		switch (pantalla) {
		case 0:
			if (p.mouseX > 572 && p.mouseX < 632 && p.mouseY > 588 && p.mouseY < 644) {
				pantalla = 1;
			}

			if (p.mouseX > 504 && p.mouseX < 691 && p.mouseY > 495 && p.mouseY < 571) {
				pantalla = 2;
			}

			break;

		case 1:

			if (p.mouseX > 33 && p.mouseX < 117 && p.mouseY > 21 && p.mouseY < 104) {
				pantalla = 0;
			}

			break;

		case 2:

			if (p.mouseX > 33 && p.mouseX < 117 && p.mouseY > 21 && p.mouseY < 104) {
				pantalla = 0;
			}

			if (p.mouseX > 503 && p.mouseX < 695 && p.mouseY > 580 && p.mouseY < 656) {
				pantalla = 3;
			}

			break;

		case 3:

			if (p.mouseX > 33 && p.mouseX < 117 && p.mouseY > 21 && p.mouseY < 104) {
				pantalla = 0;
			}

			if (p.mouseX > 503 && p.mouseX < 695 && p.mouseY > 580 && p.mouseY < 656) {
				restar = p.millis();
				enJuego = true;
				iniciando();
				pantalla = 4;
			}

			break;
		}
	}

	public void keyPressed() {
		if (pantalla == 4) {
			if (p.keyCode == p.LEFT) {
				shooter1.moverIzq();
			} else if (p.keyCode == p.RIGHT) {
				shooter1.moverDer();
			} /*
				 * else if (p.keyCode == p.UP) { if (balas > 0) { BalaPer b = new BalaPer(p,
				 * shooter1.getX()); b.start(); balasPer.add(b); balas--; } } else if (p.keyCode
				 * == p.DOWN) { balas = 7; }
				 */
		}
	}

	// En este metodo llega el mensaje de Android
	@Override
	public void enviarInfo(String mensaje, String yo) {
		accionJugador(mensaje, shooter1);
	}

	public void accionJugador(String mensaje, Personaje per) {

		msj = Integer.parseInt(mensaje);
		if (pantalla == 4) {
			// Disparar
			if (msj == 2 && balas > 0) {

				BalaPer b = new BalaPer(p, per.getX());
				b.start();
				balasPer.add(b);
				balas--;

			}

			// recargar
			else if (msj == 4) {
				balas = 7;
			}
		}

		// Siguiente
		else if (msj == 6) {
			pantalla = 2;
			restar = p.millis();
			enJuego = true;

		}

		// Jugar
		else if (msj == 7) {
			pantalla = 3;
			restar = p.millis();
			enJuego = true;

		}
	}
}
