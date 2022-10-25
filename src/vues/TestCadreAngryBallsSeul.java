package vues;

import java.io.File;
import java.util.Vector;

import base.OutilsConfigurationBilleHurlante;
import modele.Bille;
import musique.SonLong;

public class TestCadreAngryBallsSeul {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
//---------------------- gestion des bruitages : paramétrage du chemin du dossier contenant les fichiers audio --------------------------

		File file = new File(""); // lé oé la JVM est lancée : racine du projet

		File répertoireSon = new File(file.getAbsoluteFile(),
				"exodecorateur_angryballs" + File.separatorChar + "maladroit" + File.separatorChar + "bruits");

//-------------------- chargement des sons pour les hurlements --------------------------------------

		Vector<SonLong> sonsLongs = OutilsConfigurationBilleHurlante.chargeSons(répertoireSon,
				"config_audio_bille_hurlante.txt");

		SonLong hurlements[] = SonLong.toTableau(sonsLongs); // on obtient un tableau de SonLong

		Vector<Bille> billes = new Vector<Bille>();
		int choixHurlementInitial = 0;
		CadreAngryBalls cadre = new CadreAngryBalls("angry balls", "animation de billes marrantes", billes, hurlements,
				choixHurlementInitial);
		cadre.montrer();
	}

}
