package vues;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.Vector;

import modele.Bille;
import modele.visitor.Visitor;
import modele.visitor.VisitorAWT;

/**
 * responsable du dessin des billes
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 * 
 * 
 */
public class Billard extends Canvas {
	Vector<Bille> billes;

	public Billard(Vector<Bille> billes) {
		this.billes = billes;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.Canvas#paint(java.awt.Graphics)
	 */
	@Override
	public void paint(Graphics graphics) {
		int i;
		Visitor visiteurAWT = new VisitorAWT(graphics);
		for (i = 0; i < this.billes.size(); ++i)
			//this.billes.get(i).dessine(graphics);
			this.billes.get(i).acceptAWT(visiteurAWT);

		// System.out.println("billes dans le billard = " + billes);
	}

}
