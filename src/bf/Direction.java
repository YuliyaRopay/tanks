package bf;

public enum Direction {

    NONE(0), RIGHT(1), LEFT(2), BOTTOM(3), TOP(4);

    private int id;
    private Direction(int id){
        this.id=id;
    }

}
