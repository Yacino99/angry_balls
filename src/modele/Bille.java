package modele;

import java.awt.*;
import java.util.Vector;

import mesmaths.cinematique.Cinematique;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

/**
 * Cas général d'une bille de billard
 * 
 * Aucune des classes du package maladroit.modele ne doit faire de référence é
 * des classes de java.awt ni é aucune autre librairie graphique JAVA car le
 * modéle NE DOIT PAS dépendre de la vue !!! Vous devez faire les modifications
 * en conséquence !! Exploitez les Design Patterns.
 * 
 * On rappelle que ces références a une librairie graphique sont néfastes car si
 * on change de librairie graphique, voire on fait migrer le projet sur android,
 * il faut modifier les classes du modéle. La maintenance devient catastrophique
 * 
 * A MODIFIER
 * 
 * 
 */
public abstract class Bille {
//----------------- classe Bille-------------------------------------

	public Vecteur position; // centre de la bille
	public double rayon; // rayon > 0
	public Vecteur vitesse;
	public Vecteur accélération;
	public int clef; // identifiant unique de cette bille

	private Color couleur; // référence awt : mauvais

	private static int prochaineClef = 0;

	public static double ro = 1; // masse volumique

	/**
	 * @param centre
	 * @param rayon
	 * @param vitesse
	 * @param accélération
	 * @param couleur
	 */
	protected Bille(Vecteur centre, double rayon, Vecteur vitesse, Vecteur accélération, Color couleur) {
		this.position = centre;
		this.rayon = rayon;
		this.vitesse = vitesse;
		this.accélération = accélération;
		this.couleur = couleur;
		this.clef = Bille.prochaineClef++;
	}

	/**
	 * @param position
	 * @param rayon
	 * @param vitesse
	 * @param couleur
	 */
	public Bille(Vecteur position, double rayon, Vecteur vitesse, Color couleur) {
		this(position, rayon, vitesse, new Vecteur(), couleur);
	}

	/**
	 * @return the position
	 */
	public Vecteur getPosition() {
		return this.position;
	}

	/**
	 * @return the rayon
	 */
	public double getRayon() {
		return this.rayon;
	}

	/**
	 * @return the vitesse
	 */
	public Vecteur getVitesse() {
		return this.vitesse;
	}

	/**
	 * @return the accélération
	 */
	public Vecteur getAccélération() {
		return this.accélération;
	}

	/**
	 * @return the clef
	 */
	public int getClef() {
		return this.clef;
	}

	public double masse() {
		return ro * Geop.volumeSphère(rayon);
	}

	/**
	 * mise a jour de position et vitesse é t+deltaT é partir de position et vitesse
	 * é l'instant t
	 * 
	 * modifie le vecteur position et le vecteur vitesse
	 * 
	 * laisse le vecteur accélération intact
	 *
	 * La bille subit par défaut un mouvement uniformément accéléré
	 */
	public void déplacer(double deltaT) {
		Cinematique.mouvementUniformémentAccéléré(this.getPosition(), this.getVitesse(), this.getAccélération(),
				deltaT);
	}

	/**
	 * calcul (c-é-d mise é jour) éventuel du vecteur accélération. billes est la
	 * liste de toutes les billes en mouvement Cette méthode peut avoir besoin de
	 * "billes" si this subit l'attraction gravitationnelle des autres billes La
	 * nature du calcul du vecteur accélération de la bille est définie dans les
	 * classes dérivées A ce niveau le vecteur accélération est mis é zéro (c'est é
	 * dire pas d'accélération)
	 */
	public void gestionAccélération(Vector<Bille> billes) {
		this.getAccélération().set(Vecteur.VECTEURNUL);
	}

	/**
	 * gestion de l'éventuelle collision de cette bille avec les autres billes
	 *
	 * billes est la liste de toutes les billes en mouvement
	 * 
	 * Le comportement par défaut est le choc parfaitement élastique (c-é-d rebond
	 * sans amortissement)
	 * 
	 * @return true si il y a collision et dans ce cas les positions et vecteurs
	 *         vitesses des 2 billes impliquées dans le choc sont modifiées si
	 *         renvoie false, il n'y a pas de collision et les billes sont laissées
	 *         intactes
	 */
	public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
		return OutilsBille.gestionCollisionBilleBille(this, billes);
	}

	/**
	 * gestion de l'éventuelle collision de la bille (this) avec le contour
	 * rectangulaire de l'écran défini par (abscisseCoinHautGauche,
	 * ordonnéeCoinHautGauche, largeur, hauteur)
	 * 
	 * détecte si il y a collision et le cas échéant met é jour position et vitesse
	 * 
	 * La nature du comportement de la bille en réponse é cette collision est
	 * définie dans les classes dérivées
	 */
	public abstract void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur,
			double hauteur);

	/*
	 * cette méthode engendre des clignotements : idée : utiliser l'active rendering
	 * et le double buffering pour éviter éa
	 */

	public void dessine(Graphics g) // référence awt : mauvais
	{


		int width, height;
		int xMin, yMin;

		xMin = (int) Math.round(position.x - rayon);
		yMin = (int) Math.round(position.y - rayon);

		width = height = 2 * (int) Math.round(rayon);

		g.setColor(couleur);
		g.fillOval(xMin, yMin, width, height);
		g.setColor(Color.CYAN);
		g.drawOval(xMin, yMin, width, height);
	}

	public String toString() {
		return "centre = " + position + " rayon = " + rayon + " vitesse = " + vitesse + " accélération = "
				+ accélération + " couleur = " + couleur + "clef = " + clef;
	}

//----------------- classe Bille -------------------------------------
}
