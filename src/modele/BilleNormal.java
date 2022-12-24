package modele;

import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;
import vues.BoutonChoixHurlement;

import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.Vector;

public class BilleNormal extends Bille {


    public Vecteur position; // centre de la bille
    public double rayon; // rayon > 0
    public Vecteur vitesse;
    public Vecteur accélération;
    public int clef; // identifiant unique de cette bille

    public static double ro = 1; // masse volumique
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

    public double masse() {
        return ro * Geop.volumeSphère(this.getRayon());
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public void déplacer(double deltaT) {
        Cinematique.mouvementUniformémentAccéléré(this.getPosition(), this.getVitesse(), this.getAccélération(), deltaT);
    }

    public void gestionAccélération(Vector<Bille> billes) {
        this.getAccélération().set(Vecteur.VECTEURNUL);
    }

    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        return OutilsBille.gestionCollisionBilleBille(this, billes);
    }
    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
    }


}
