package modele.accelerations;

import java.util.Vector;
import mesmaths.mecanique.MecaniquePoint;
import modele.Bille;
import modele.DecorateurBille;

public class Frottement extends DecorateurBille {
    public Frottement(Bille billeDecoree) {
        super(billeDecoree);
    }

    public void gestionAccélération(Vector<Bille> billes)
    {
        super.gestionAccélération(billes);          // remise é zéro du vecteur accélération
        this.billeDecoree.getAccélération().ajoute(MecaniquePoint.freinageFrottement(this.billeDecoree.masse(), this.billeDecoree.getVitesse())); // contribution de l'accélération due au frottement dans l'air
    }
}

// augmenter la masse pour up le frottement pour limiter l'acceleration