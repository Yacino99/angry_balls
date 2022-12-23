package modele.collisions;

import mesmaths.cinematique.Collisions;
import modele.Bille;
import modele.DecorateurBille;

public class PasseMuraille extends DecorateurBille {
    public PasseMuraille(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourPasseMuraille( this.billeDecoree.getPosition(), abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);
    }
}
