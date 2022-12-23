package modele.collisions;

import mesmaths.cinematique.Collisions;
import modele.Bille;
import modele.DecorateurBille;

public class Bloque extends DecorateurBille {
    public Bloque(Bille billeDecoree) {
        super(billeDecoree);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
        Collisions.collisionBilleContourAvecArretHorizontal(this.getPosition(), this.getRayon(), this.getVitesse(),
                abscisseCoinHautGauche, largeur);
        Collisions.collisionBilleContourAvecArretVertical(this.getPosition(), this.getRayon(), this.getVitesse(),
                ordonnéeCoinHautGauche, hauteur);
    }
}
