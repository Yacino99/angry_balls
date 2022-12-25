package modele.state;

import modele.BillePilotee;

import java.awt.event.MouseEvent;

public abstract class ControleurEtat {

    protected ControleurGeneral controleurGeneral;

    // pas besoin de l'etat retour
    protected ControleurEtat suivant;

    protected BillePilotee billePilotee;

    public ControleurEtat(ControleurGeneral controleurGeneral, ControleurEtat suivant) {
        this.controleurGeneral = controleurGeneral;
        this.suivant = suivant;
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseDragged(MouseEvent e) {
    }
}
