package modele.accelerations;

import mesmaths.mecanique.MecaniquePoint;
import modele.Bille;
import modele.DecorateurBille;
import modele.OutilsBille;

import java.util.Vector;

public class Newton extends DecorateurBille {
    public Newton(Bille billeDecoree) {
        super(billeDecoree);
    }

    public void gestionAccélération(Vector<Bille> billes)
    {
        super.gestionAccélération(billes);                              // remise é zéro du vecteur accélération
        this.billeDecoree.getAccélération().ajoute(OutilsBille.gestionAccélérationNewton(this.billeDecoree, billes));     // contribution de l'accélération due é l'attraction des autres billes
        this.billeDecoree.getAccélération().ajoute(MecaniquePoint.freinageFrottement(this.billeDecoree.masse(), this.billeDecoree.getVitesse()));      // contribution de l'accélération due au frottement dans l'air
    }
}
