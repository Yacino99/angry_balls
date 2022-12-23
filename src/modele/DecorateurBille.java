package modele;

import mesmaths.cinematique.Cinematique;
import mesmaths.cinematique.Collisions;
import mesmaths.geometrie.base.Vecteur;
import vues.BoutonChoixHurlement;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Vector;

public abstract class DecorateurBille extends Bille{

    protected Bille billeDecoree;

    public DecorateurBille(Bille billeDecoree){
        this.billeDecoree = billeDecoree;
    }

    @Override
    public Vecteur getPosition() {
        return this.billeDecoree.getPosition();
    }

    @Override
    public double getRayon() {
        return this.billeDecoree.getRayon();
    }

    public Vecteur getVitesse() {
        return this.billeDecoree.getVitesse();
    }

    public Vecteur getAccélération() {
        return this.billeDecoree.getAccélération();
    }

    public int getClef() {
        return this.billeDecoree.getClef();
    }

    public Color getCouleur() {
        return this.billeDecoree.getCouleur();
    }

    public void déplacer(double deltaT) {
        Cinematique.mouvementUniformémentAccéléré(this.billeDecoree.getPosition(), this.billeDecoree.getVitesse(), this.billeDecoree.getAccélération(),
                deltaT);
    }

    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        return OutilsBille.gestionCollisionBilleBille(this.billeDecoree, billes);
    }

    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur){
        Collisions.collisionBilleContourAvecRebond( this.billeDecoree.getPosition(), this.billeDecoree.getRayon(), this.billeDecoree.getVitesse(), abscisseCoinHautGauche, ordonnéeCoinHautGauche, largeur, hauteur);
    }
}
