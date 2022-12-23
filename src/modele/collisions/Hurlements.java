/*
package modele.collisions;

import mesmaths.geometrie.base.Vecteur;
import musique.SonLong;
import vues.BoutonChoixHurlement;
import vues.VueBillard;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class Hurlements extends DecorateurBille{

    private static final int DELAI_MIN = 10; */
/* délai minimum de rafraichissement du son. en millisecondes *//*

    public static final int DELAI_MAX = 150; */
/* délai maximum de rafraichissement du son. en millisecondes *//*

    public SonLong sonLong; */
/* bande son é diffuser *//*

    int i; */
/*
     * né de l'élément de sonLong é jouer. on doit avoir i >= 0. sonLong se charge
     * de faire le modulo pour obtenir un indice correct et pour boucler ainsi sur
     * le tableau inscrit dans sonLong
     *//*

    long dernierInstant; */
/* dernier instant oé le son a été diffusé *//*

    VueBillard vueBillard;

    private static final double COEFF_VOLUME = 6; // plus la valeur est grande, plus le son augmente rapidement en fct
    // de la vitesse de la boule

    public Hurlements(Bille billeDecoree, SonLong sonLong, VueBillard vueBillard) {
        super(billeDecoree);
        this.sonLong = sonLong;
        i = 0;
        dernierInstant = System.currentTimeMillis();
        this.vueBillard = vueBillard;
    }

    public void déplacer(double deltaT) {
        super.déplacer(deltaT);
        Vecteur p = this.billeDecoree.getPosition();
        Vecteur v = this.billeDecoree.getVitesse();
        double xMax;

        xMax = vueBillard.largeurBillard();

        double n = v.norme();
//double n2 = v.normeCarrée();
        double y = Math.exp(-COEFF_VOLUME * n); // y = e^(-COEFF*n). on obtient donc 0 < y <= 1
        double volume = 1 - y; // on obtient 0 <= volume < 1 avec volume == 0 si n == 0 et volume proche de 1
        // si n est grand
        double x1 = p.x / xMax; */
/* on obtient 0 <= x1 <= 1 *//*
 //// System.err.println("dans BilleHurlante.déplacer() :
        //// x1 = "+ x1);
        double balance = 2 * x1 - 1; // on obtient -1 <= balance <= 1
        //// System.err.println("volume = " + volume);
//double v2 = volume*volume;
        int délai = (int) (DELAI_MIN * volume
                + DELAI_MAX * y); */
/* le délai entre 2 diffusions diminue lorsque la vitesse augmente *//*

        long instant = System.currentTimeMillis();
        if (instant
                - this.dernierInstant >= délai) */
/*
         * la fréquence de diffusion augmente donc avec la vitesse de la bille
         *//*

        {
            double coeffPitch = 1;
            this.sonLong.joue(i++, volume, balance, coeffPitch); */
/* le son est diffusé dans un thread séparé *//*

            this.dernierInstant = instant;
        }
    }

    public void itemStateChanged(ItemEvent e) { // System.err.println("dans BilleHurlanteMvtNewtonArret.itemStateChanged
        // au début");
        if (e.getSource() instanceof BoutonChoixHurlement) {
            BoutonChoixHurlement boutonChoixHurlement = (BoutonChoixHurlement) (e.getSource());
            this.sonLong = boutonChoixHurlement.sonLong; // System.err.println("dans
            // BilleHurlanteMvtNewtonArret.itemStateChanged");
        }
    }
}
*/
