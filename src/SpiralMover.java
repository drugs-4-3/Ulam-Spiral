/**
 * Created by Michał on 10.08.2016.
 *
 * This class is used to keep order of putting different elements of spiral in the right place.
 * We do this by returning Point2D object for every other call of move method.
 * Coordinates of this point tells us where to put next element of spiral as in XY axis in computer graphics.
 * For example: if returned point is (0,1) that means that it should be placed under the previous element.
 * Returned point always has one coordinate equal 0 and other equal +-value "stepLength" defined in the constructor.
 * Directions sequence (enum Direction) and starting directions (setDirections) are chosen subjectively.
 */

import java.awt.geom.Point2D;
import java.util.LinkedList;

public class SpiralMover {

    private enum Direction {RIGHT, UP, LEFT, DOWN}
    private SpiralList direction;
    private int movesToMake;
    private int vertical;
    private int horizontal;
    private double stepLength;


    public SpiralMover(double sl) {
        movesToMake = 0;
        direction = setDirections();
        stepLength = sl;
    }


    public Point2D move() {
        if (movesToMake == 0) {
            direction = direction.next;
            incrementLineLength(direction.current);
            movesToMake = lineLength(direction.current);
        }
        movesToMake--;
        return movePoint(direction.current);

    }


    private void incrementLineLength(Direction d) {
        if (d == Direction.DOWN || d == Direction.UP)
            vertical++;
        else
            horizontal++;
    }


    public int lineLength(Direction d) {
        if (d == Direction.DOWN || d == Direction.UP)
            return vertical;
        else
            return horizontal;
    }


    private Point2D movePoint(Direction d) {
        Point2D result = null;
        switch(d){
            case DOWN:
                result = new Point2D.Double(0, stepLength);
                break;
            case RIGHT:
                result = new Point2D.Double(stepLength, 0);
                break;
            case UP:
                result = new Point2D.Double(0, -stepLength);
                break;
            case LEFT:
                result = new Point2D.Double(-stepLength, 0);
                break;
            default:
                break;
        }
        return result;
    }


    private class SpiralList {
        public Direction current;
        public SpiralList next;

        public SpiralList(Direction d) {
            current = d;
        }
    }


    private SpiralList setDirections(){
        SpiralList result = new SpiralList(Direction.DOWN);
        result.next = new SpiralList(Direction.RIGHT);
        result.next.next = new SpiralList(Direction.UP);
        result.next.next.next = new SpiralList(Direction.LEFT);
        result.next.next.next.next = result;
        return result;

    } // I know that this implementation is terrible - will hopefully fix it later
    // TODO: how to write a loop that will go over all enums no matter how many of them
    

}