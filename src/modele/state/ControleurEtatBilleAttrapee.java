package modele.state;

import mesmaths.geometrie.base.Vecteur;
import modele.Bille;

import java.awt.event.MouseEvent;

public class ControleurEtatBilleAttrapee extends ControleurEtat{

    public ControleurEtatBilleAttrapee(ControleurGeneral controleurGeneral, ControleurEtat suivant) {
        super(controleurGeneral, suivant);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //a verifié
        Bille bille = this.suivant.billePilotee.getBilleDecoree();
        this.controleurGeneral.billes.remove(this.billePilotee);
        this.controleurGeneral.billes.add(bille);

        //On passe à l'etat suivant
        this.controleurGeneral.setControleurCourant(this.suivant);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        double impacteDeLaMasse = 1/this.suivant.billePilotee.masse();

        Vecteur forceAppliquee;
        forceAppliquee = new Vecteur(e.getX(), e.getY());
        forceAppliquee.retire(this.suivant.billePilotee.getPosition());
        // prendre en consideration la masse de la bille
        forceAppliquee.multiplie(impacteDeLaMasse);
        //forceAppliquee.multiplie(0.00003);
        // application de la force
        this.suivant.billePilotee.appliquerForce(forceAppliquee);
    }
}
