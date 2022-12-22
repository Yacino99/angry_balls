package base;

import java.awt.Color;
import java.io.File;
import java.util.Vector;

import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import modele.BilleHurlanteMvtNewtonArret;
import modele.BilleMvtNewtonFrottementRebond;
import modele.BilleMvtPesanteurFrottementRebond;
import modele.BilleMvtRUPasseMurailles;
import modele.BilleMvtRURebond;
import musique.SonLong;
import vues.CadreAngryBalls;

/**
 * Gestion d'une liste de billes en mouvement ayant toutes un comportement
 * différent
 * 
 * Idéal pour mettre en place le DP decorator
 */
public class TestAngryBalls {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//---------------------- gestion des bruitages : paramétrage du chemin du dossier contenant les fichiers audio --------------------------

		// petite modif
		File file = new File(""); // lé oé la JVM est lancée : racine du projet

		File répertoireSon = new File(file.getAbsoluteFile(),
				"src" + File.separatorChar + "bruits");
		System.out.println(répertoireSon);
//-------------------- chargement des sons pour les hurlements --------------------------------------

		Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(répertoireSon,
				"config_audio_bille_hurlante.txt");

		SonLong hurlements[] = SonLong.toTableau(sonsLongs); // on obtient un tableau de SonLong

//------------------- création de la liste (pour l'instant vide) des billes -----------------------

		Vector<Bille> billes = new Vector<Bille>();

//---------------- création de la vue responsable du dessin des billes -------------------------

		int choixHurlementInitial = 0;
		CadreAngryBalls cadre = new CadreAngryBalls("Angry balls",
				"Animation de billes ayant des comportements différents. Situation idéale pour mettre en place le DP Decorator",
				billes, hurlements, choixHurlementInitial);
		cadre.montrer(); // on rend visible la vue
		cadre.createBufferStrategy(2);

		//cadre.getBufferStrategy().show();


//------------- remplissage de la liste avec 5 billes -------------------------------

		double xMax, yMax;
		double vMax = 0.1;
		xMax = cadre.largeurBillard(); // abscisse maximal
		yMax = cadre.hauteurBillard(); // ordonnée maximale

		double rayon = 0.05 * Math.min(xMax, yMax); // rayon des billes : ici toutes les billes ont le méme rayon, mais
													// ce n'est pas obligatoire

		Vecteur p0, p1, p2, p3, p4, v0, v1, v2, v3, v4; // les positions des centres des billes et les vecteurs vitesse
														// au démarrage.
														// Elles vont étre choisies aléatoirement

//------------------- création des vecteurs position des billes ---------------------------------

		p0 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p1 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p2 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p3 = Vecteur.créationAléatoire(0, 0, xMax, yMax);
		p4 = Vecteur.créationAléatoire(0, 0, xMax, yMax);

//------------------- création des vecteurs vitesse des billes ---------------------------------

		v0 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v1 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, 0);
		v2 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v3 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);
		v4 = Vecteur.créationAléatoire(-vMax, -vMax, vMax, vMax);

//--------------- ici commence la partie é changer ---------------------------------

		billes.add(new BilleMvtRURebond(p0, rayon, v0, Color.red));
		billes.add(new BilleMvtPesanteurFrottementRebond(p1, rayon, v1, new Vecteur(0, 0.001), Color.yellow));
		billes.add(new BilleMvtNewtonFrottementRebond(p2, rayon, v2, Color.green));
		billes.add(new BilleMvtRUPasseMurailles(p3, rayon, v3, Color.cyan));

		BilleHurlanteMvtNewtonArret billeNoire; // cas particulier de la bille qui hurle

		billes.add(billeNoire = new BilleHurlanteMvtNewtonArret(p4, rayon, v4, Color.black,
				hurlements[choixHurlementInitial], cadre));

		cadre.addChoixHurlementListener(billeNoire); // é présent on peut changer le son de la bille qui hurle

//---------------------- ici finit la partie é changer -------------------------------------------------------------

		System.out.println("billes = " + billes);

//-------------------- création de l'objet responsable de l'animation (c'est un thread séparé) -----------------------

		AnimationBilles animationBilles = new AnimationBilles(billes, cadre);

//----------------------- mise en place des écouteurs de boutons qui permettent de contréler (un peu...) l'application -----------------

		EcouteurBoutonLancer écouteurBoutonLancer = new EcouteurBoutonLancer(animationBilles);
		EcouteurBoutonArreter écouteurBoutonArréter = new EcouteurBoutonArreter(animationBilles);

//------------------------- activation des écouteurs des boutons et éa tourne tout seul ------------------------------

		cadre.lancerBilles.addActionListener(écouteurBoutonLancer); // pourrait étre remplacé par Observable - Observer
		cadre.arréterBilles.addActionListener(écouteurBoutonArréter); // pourrait étre remplacé par Observable -
																		// Observer

	}

}
