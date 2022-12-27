package modele.visitor;

import modele.Bille;

import java.awt.*;

public class VisitorAWT implements Visitor{
    @Override
    public void dessineAWT(Bille bille, Graphics g,Color couleur) {
        int width, height;
        int xMin, yMin;

        xMin = (int) Math.round(bille.getPosition().x - bille.getRayon());
        yMin = (int) Math.round(bille.getPosition().y - bille.getRayon());

        width = height = 2 * (int) Math.round(bille.getRayon());

        g.setColor(bille.getCouleur());
        g.fillOval(xMin, yMin, width, height);
        g.setColor(bille.getCouleur());
        g.drawOval(xMin, yMin, width, height);
    }
}
