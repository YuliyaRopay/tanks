package bf.tanks;

import bf.Direction;

public class QuadrantStep {

    private int y;
    private int x;
    private Direction direction;
    private Action action;

    public QuadrantStep() {
    }

    public QuadrantStep(int y, int x, Direction direction,Action action) {
        this.y = y;
        this.x = x;
        this.direction = direction;
        this.action = action;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    @Override
    public String toString() {
        return "[ "+y+"_"+x+", "+direction+", "+action+" ]";
    }
}
