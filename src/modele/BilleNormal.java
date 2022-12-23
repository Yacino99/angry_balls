package modele;

import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Vecteur;
import vues.BoutonChoixHurlement;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Vector;

public class BilleNormal extends Bille {
    protected Color couleur; // référence awt : mauvais
    protected static int prochaineClef = 0;

    public BilleNormal(Vecteur position, double rayon, Vecteur vitesse, Vecteur accélération, Color couleur) {
        this.position = position;
        this.rayon = rayon;
        this.vitesse = vitesse;
        this.accélération = accélération;
        this.couleur = couleur;
        this.clef = prochaineClef++;
    }

    public BilleNormal(Vecteur position, double rayon, Vecteur vitesse, Color couleur) {
        this(position, rayon, vitesse, new Vecteur(), couleur);
    }

    @Override
    public Vecteur getPosition() {
        return this.position;
    }

    public double getRayon(){
        return this.rayon;
    }

    public Vecteur getVitesse() {
        return this.vitesse;
    }

    public Vecteur getAccélération() {
        return this.accélération;
    }

    public int getClef() {
        return this.clef;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public void déplacer(double deltaT) {
        Cinematique.mouvementUniformémentAccéléré(this.getPosition(), this.getVitesse(), this.getAccélération(),
                deltaT);
    }

    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
    }

    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        return OutilsBille.gestionCollisionBilleBille(this, billes);
    }

}
