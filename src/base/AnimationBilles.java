package base;

import java.util.Vector;

import modele.Bille;
import vues.VueBillard;

/**
 * responsable de l'animation des billes, c-é-d responsable du mouvement de la
 * liste des billes. met perpétuellement é jour les billes. gére le délai entre
 * 2 mises é jour (deltaT) et prévient la vue responsable du dessin des billes
 * qu'il faut mettre é jour la scéne
 * 
 * ICI : IL N'Y A RIEN A CHANGER
 */
public class AnimationBilles implements Runnable {

	Vector<Bille> billes; // la liste de toutes les billes en mouvement
	VueBillard vueBillard; // la vue responsable du dessin des billes
	private Thread thread; // pour lancer et arréter les billes

	private static final double COEFF = 0.5;

	/**
	 * @param billes
	 * @param vueBillard
	 */
	public AnimationBilles(Vector<Bille> billes, VueBillard vueBillard) {
		this.billes = billes;
		this.vueBillard = vueBillard;
		this.thread = null; // est-ce utile ?
	}

	@Override
	public void run() {
		try {
			double deltaT; // délai entre 2 mises é jour de la liste des billes
			Bille billeCourante;

			double minRayons = AnimationBilles.minRayons(billes); // nécessaire au calcul de deltaT
			double minRayons2 = minRayons * minRayons; // nécessaire au calcul de deltaT

			while (!Thread.interrupted()) // gestion du mouvement
			{
				// deltaT = COEFF*minRayons2/(1+maxVitessesCarrées(billes)); // mise é jour
				// deltaT. L'addition + 1 est une astuce pour éviter les divisions par zéro

				// System.err.println("deltaT = " + deltaT);
				deltaT = 10;

				int i;
				for (i = 0; i < billes.size(); ++i) // mise é jour de la liste des billes
				{
					billeCourante = billes.get(i);
					billeCourante.déplacer(deltaT); // mise é jour position et vitesse de cette bille
					billeCourante.gestionAccélération(billes); // calcul de l'accélération subie par cette bille
					billeCourante.gestionCollisionBilleBille(billes);
					billeCourante.collisionContour(0, 0, vueBillard.largeurBillard(), vueBillard.hauteurBillard()); // System.err.println("billes
																													// =
																													// "
																													// +
																													// billes);
				}

				vueBillard.miseAJour(); // on prévient la vue qu'il faut redessiner les billes

				Thread.sleep((int) deltaT); // deltaT peut étre considéré comme le délai entre 2 flashes d'un
											// stroboscope qui éclairerait la scéne
			}
		}

		catch (InterruptedException e) {
			/* arrét normal, il n'y a rien é faire dans ce cas */
		}

	}

	/**
	 * calcule le maximum de de la norme carrée (pour faire moins de calcul) des
	 * vecteurs vitesse de la liste de billes
	 * 
	 */
	static double maxVitessesCarrées(Vector<Bille> billes) {
		double vitesse2Max = 0;

		int i;
		double vitesse2Courante;

		for (i = 0; i < billes.size(); ++i)
			if ((vitesse2Courante = billes.get(i).vitesse.normeCarrée()) > vitesse2Max)
				vitesse2Max = vitesse2Courante;

		return vitesse2Max;
	}

	/**
	 * calcule le minimum des rayons de a liste des billes
	 * 
	 * 
	 */
	static double minRayons(Vector<Bille> billes) {
		double rayonMin, rayonCourant;

		rayonMin = Double.MAX_VALUE;

		int i;
		for (i = 0; i < billes.size(); ++i)
			if ((rayonCourant = billes.get(i).rayon) < rayonMin)
				rayonMin = rayonCourant;

		return rayonMin;
	}

	public void lancerAnimation() {
		if (this.thread == null) {
			this.thread = new Thread(this);
			thread.start();
		}
	}

	public void arréterAnimation() {
		if (thread != null) {
			this.thread.interrupt();
			this.thread = null;
		}
	}
}
