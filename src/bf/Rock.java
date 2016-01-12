package bf;


import java.awt.*;

public class Rock extends SimpleBFObject{

    public Rock(int x, int y) {
        super(x, y);
        color = new Color(10, 50, 100);
        setImage(BFImages.IMG_ROCK);
    }
}
