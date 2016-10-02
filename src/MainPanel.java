/**
 * Created by Michał on 09.08.2016.
 *
 * This is the panel on which we draw our spiral.
 * By element we understand a rectangle with a number and color mark drawn on the panel.
 */



import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.font.*;
import java.awt.event.*;

public class MainPanel extends JPanel {

    // panel size
    private static final int DEFAULT_WIDTH = 800;
    private static final int DEFAULT_HEIGHT = 800;

    // size of one rectangle (square actually)
    private double width = 10;
    private double height = 10;

    // coordinates of central rectangle - points at top left corner of it
    private double leftX = (DEFAULT_WIDTH - width) / 2;
    private double topY = (DEFAULT_HEIGHT - height) / 2;

    // points at where the next rectangle should be placed - changes in the for loop
    private Point2D cursor;

    // point containng coordinates where to move with next element
    private Point2D move;

    // objects that help positioning and drawing elements
    private SpiralMover spiralMover = new SpiralMover(width);
    private SpiralDrawer spiralDrawer;

    private int numberOfElements = 2000;
    // how many numbers to draw

    private int fontSize = 10;


    public void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g;
        spiralDrawer = new SpiralDrawer(g2, width, fontSize);

        cursor = new Point2D.Double(leftX, topY);
        // points where the next rectangle should be placed

        for (int i = 1; i < numberOfElements; i++){ // for loop goes over all natural numbers up to numberOfElements
            spiralDrawer.drawElement(i, cursor);
            moveCursor();
        }
    }


    private void moveCursor() {
        move = spiralMover.move();
        cursor.setLocation(cursor.getX() + move.getX(), cursor.getY() + move.getY());
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


    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

}
