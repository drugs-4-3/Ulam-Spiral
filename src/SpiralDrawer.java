/**
 * Created by Michał on 14.08.2016.
 */

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class SpiralDrawer {

    private Graphics2D graphics2d;
    private Font font;
    private double squareSize;
    private int fontSize;


    public SpiralDrawer(Graphics2D graphics2d, double squareSize, int fontSize) {
        this.graphics2d = graphics2d;
        this.squareSize = squareSize;
        this.fontSize = fontSize;
        font = new Font("Serif", Font.BOLD, fontSize);
        graphics2d.setFont(font);

    }

    public void drawElement(int number, Point2D point) {
        Rectangle2D rect = new Rectangle2D.Double(point.getX(), point.getY(), squareSize, squareSize);
        drawRectangle(number, point, rect);
        drawNumber(number, point, rect);
    }


    private void drawRectangle(int number, Point2D point, Rectangle2D rect) {
        setMarkingColor(number);
        graphics2d.fill(rect);
        graphics2d.draw(rect);
    }


    private void drawNumber(int number, Point2D point, Rectangle2D rect) {
        String text = Integer.toString(number);

        // checking for text size
        FontRenderContext context = graphics2d.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(text, context);

        // set (x, y) = upper left corner of the text (related to given rectangle)
        double x = rect.getX() + (rect.getWidth() - bounds.getWidth()) / 2;
        double y = rect.getY() + (rect.getHeight() - bounds.getHeight()) / 2;
        double ascent = - bounds.getY(); // check: Java Podstawy Wydanie IX, p. 346

        graphics2d.setPaint(Color.BLACK); // remember to change color before painting number
        graphics2d.drawString(text, (int) x, (int) (y + ascent));
    }

    private void setMarkingColor(int i) {
        if (isPrime(i))
            graphics2d.setPaint(Color.RED);
        else
            graphics2d.setPaint(Color.WHITE);
    }


    private boolean isPrime(int n) {
        if (n < 2) return false;
        boolean result = true;
        for (int i = 2; i*i <= n; i++){
            if (n % i == 0)
                result = false;
        }
        return result;
    }


}
