package modele.accelerations;

import mesmaths.mecanique.MecaniquePoint;
import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import modele.DecorateurBille;
import modele.OutilsBille;

import java.util.Vector;

public class Pesanteur extends DecorateurBille {
    Vecteur pesanteur;

    public Pesanteur(Bille billeDecoree, Vecteur pesanteur) {
        super(billeDecoree);
        this.pesanteur = pesanteur;
    }

    public void gestionAccélération(Vector<Bille> billes)
    {
        super.gestionAccélération(billes);          // remise é zéro du vecteur accélération
        this.billeDecoree.getAccélération().ajoute(this.pesanteur);          // contribution du champ de pesanteur (par exemple)
    }
}
