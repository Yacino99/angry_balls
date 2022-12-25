package modele.state;

import modele.Bille;
import vues.Billard;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;

public class ControleurGeneral implements MouseListener, MouseMotionListener {

    /*------------------ pour gérer le diagramme de transition d'état -------------------*/

    ControleurEtatBilleAttrapable controleurEtatBilleAttrapable ;       // associé à l'état "bille attrapable"
    ControleurEtatBilleAttrapee controleurEtatBilleAttrapee ;           // associé à l'état "bille attrapee"
    ControleurEtat controleurCourant;                                   // le contrôleur courant qui "voyage" dans le graphe orienté

    Vector<Bille> billes;
    Billard billard;

    public ControleurGeneral(Vector<Bille> billes, Billard billard) {
        this.billes = billes;
        this.billard = billard;

        this.billard.addMouseListener(this);
        this.billard.addMouseMotionListener(this);

        this.installeControleurs();     // à présent this écoute les évènements de la souris

    }

    private void installeControleurs() {
        //---------- instanciation des 2 objets contrôleurs ---------------
        this.controleurEtatBilleAttrapable = new ControleurEtatBilleAttrapable(this,null);
        this.controleurEtatBilleAttrapee = new ControleurEtatBilleAttrapee(this,this.controleurEtatBilleAttrapable);

        //--------------------- on établit les liens oubliés entre les contrôleurs ----------------
        this.controleurEtatBilleAttrapable.suivant = this.controleurEtatBilleAttrapee;

        //--------------------- on place le contrôleur courant sur l'état de départ dans le graphe
        this.controleurCourant = this.controleurEtatBilleAttrapable;
    }

    public void setControleurCourant(ControleurEtat controleurCourant) {
        this.controleurCourant = controleurCourant;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        this.controleurCourant.mousePressed(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        this.controleurCourant.mouseReleased(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        this.controleurCourant.mouseDragged(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }


    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
