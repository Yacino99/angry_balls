package modele.visitor;

import modele.Bille;

import java.awt.*;
import java.lang.reflect.Field;

public class VisitorAWT implements Visitor{
    @Override
    public void dessineAWT(Bille bille, Graphics g) {
        int width, height;
        int xMin, yMin;

        xMin = (int) Math.round(bille.getPosition().x - bille.getRayon());
        yMin = (int) Math.round(bille.getPosition().y - bille.getRayon());

        width = height = 2 * (int) Math.round(bille.getRayon());

        Color color;
        try {
            Field field = Class.forName("java.awt.Color").getField(bille.getCouleur());
            color = (Color)field.get(null);
        } catch (Exception e) {
            color = null; // Not defined
        }

        g.setColor(/*bille.getCouleur()*/color);
        g.fillOval(xMin, yMin, width, height);
        g.setColor(/*bille.getCouleur()*/color);
        g.drawOval(xMin, yMin, width, height);
    }
}
