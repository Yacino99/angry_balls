package modele;

import mesmaths.geometrie.base.Vecteur;
import mesmaths.mecanique.MecaniquePoint;

import java.util.Vector;

public class BillePilotee extends DecorateurBille{

    private Vecteur forceAppliquee;
    public BillePilotee(Bille billeDecoree) {
        super(billeDecoree);
        this.forceAppliquee = Vecteur.VECTEURNUL;
    }

    public void appliquerForce(Vecteur forceAppliquee) {
        this.forceAppliquee = forceAppliquee;
    }

    public void gestionAccélération(Vector<Bille> billes)
    {
        super.gestionAccélération(billes);          // remise é zéro du vecteur accélération
        this.billeDecoree.getAccélération().ajoute(this.forceAppliquee); // contribution de l'accélération due aux forces appliques
    }
}
