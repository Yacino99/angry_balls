package modele;

import mesmaths.cinematique.Cinematique;
import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.awt.*;
import java.io.File;
import java.util.Vector;

public class BilleNormal extends Bille {


    public Vecteur position; // centre de la bille
    public double rayon; // rayon > 0
    public Vecteur vitesse;
    public Vecteur accélération;
    public int clef; // identifiant unique de cette bille

    public static double ro = 1; // masse volumique
//    protected Color couleur; // référence awt : mauvais
    protected String couleur;
    protected static int prochaineClef = 0;

    public BilleNormal(Vecteur position, double rayon, Vecteur vitesse, Vecteur accélération, String couleur) {
        this.position = position;
        this.rayon = rayon;
        this.vitesse = vitesse;
        this.accélération = accélération;
        this.couleur = couleur;
        this.clef = prochaineClef++;
    }

    public BilleNormal(Vecteur position, double rayon, Vecteur vitesse, String couleur) {
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

//    public Color getCouleur() {
//        return this.couleur;
//    }
    public String getCouleur() {
        return this.couleur;
    }

    public void setPosition(Vecteur position) {
        this.position = position;
    }
    public void déplacer(double deltaT) {
        Cinematique.mouvementUniformémentAccéléré(this.getPosition(), this.getVitesse(), this.getAccélération(), deltaT);
    }

    // ?? repetition dans la classe bille ???
    public void gestionAccélération(Vector<Bille> billes) {
        this.getAccélération().set(Vecteur.VECTEURNUL);
    }


    // cette classe la ne gere paas le son
    public boolean gestionCollisionBilleBille(Vector<Bille> billes) {
        //return OutilsBille.gestionCollisionBilleBille(this, billes);
        if (OutilsBille.gestionCollisionBilleBille(this, billes)){
            playSound("collision_bille_bille.wav");
        }

        return OutilsBille.gestionCollisionBilleBille(this, billes);
    }
    @Override
    public void collisionContour(double abscisseCoinHautGauche, double ordonnéeCoinHautGauche, double largeur, double hauteur) {
    }


}
