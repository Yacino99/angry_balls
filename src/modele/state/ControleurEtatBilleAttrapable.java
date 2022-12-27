package modele.state;

import mesmaths.geometrie.base.Geop;
import mesmaths.geometrie.base.Vecteur;
import modele.Bille;
import modele.BillePilotee;

import java.awt.event.MouseEvent;

public class ControleurEtatBilleAttrapable extends ControleurEtat{
    public ControleurEtatBilleAttrapable(ControleurGeneral controleurGeneral, ControleurEtat suivant) {
        super(controleurGeneral, suivant);
    }

    public void mousePressed(MouseEvent e) {
        for (Bille bille : this.controleurGeneral.billes){
            if(Geop.appartientDisque(new Vecteur(e.getX(), e.getY()), bille.getPosition(), bille.getRayon())) {
                System.out.println("Touchéééééé  "+bille.getPosition());
                //On applique la decoration BillePilotee à la bille selectionnee
                this.billePilotee = new BillePilotee(bille);
                this.controleurGeneral.billes.remove(bille);
                this.controleurGeneral.billes.add(this.billePilotee);

                //On passe à l'etat suivant
                this.controleurGeneral.setControleurCourant(this.suivant);
                break;
            }
        }
    }
}
