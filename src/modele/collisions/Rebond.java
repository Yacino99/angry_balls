package modele.collisions;

import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import modele.DecorateurBille;

import java.awt.*;
import java.util.Vector;

public class Rebond extends DecorateurBille {

    public Rebond(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourAvecRebond( this.billeDecoree.getPosition(), this.billeDecoree.getRayon(), this.billeDecoree.getVitesse(), abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);
    }

}
