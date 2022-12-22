package vues;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Vector;

import modele.Bille;

/**
 * responsable du dessin des billes
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 * 
 */
public class Billard extends Canvas {
	Vector<Bille> billes;
	//Graphics graphics;
	public Billard(Vector<Bille> billes) {
		this.billes = billes;

		/*this.setIgnoreRepaint(true);
		int nombreBuffers = 2;
		//this.setVisible(true);

		this.createBufferStrategy(nombreBuffers);


		//Thread.sleep(100);   // il faut attendre un minimum de 50 ms pour que le buffer soit operationnel
		this.graphics = this.getBufferStrategy().getDrawGraphics();*/
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics graphics) {
		int i;

		for (i = 0; i < this.billes.size(); ++i)
			this.billes.get(i).dessine(graphics);

		// System.out.println("billes dans le billard = " + billes);
	}

}
