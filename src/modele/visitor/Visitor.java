package modele.visitor;

import modele.Bille;

import java.awt.*;

public interface Visitor {
    public void dessineAWT(Bille bille, Graphics g,Color couleur);

}
