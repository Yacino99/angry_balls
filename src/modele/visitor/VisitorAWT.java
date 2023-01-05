package modele.visitor;

import modele.Bille;

import java.awt.*;
import java.lang.reflect.Field;

public class VisitorAWT implements Visitor{

    Graphics graphics;

    public VisitorAWT(Graphics graphics)
    {
        this.graphics = graphics;
    }

    @Override
    public void dessineAWT(Bille bille) {
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

        graphics.setColor(/*bille.getCouleur()*/color);
        graphics.fillOval(xMin, yMin, width, height);
        graphics.setColor(/*bille.getCouleur()*/color);
        graphics.drawOval(xMin, yMin, width, height);
    }
}
