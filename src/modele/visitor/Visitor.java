package modele.visitor;

import modele.Bille;

import java.awt.*;

public interface Visitor {
    public void dessineAWT(Bille bille, Graphics g);
    //comment fait on pour faire un visitor android ?
}
