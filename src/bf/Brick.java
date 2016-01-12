package bf;

import java.awt.*;

public class Brick extends SimpleBFObject{

    public Brick(int x, int y) {
        super(x, y);
        color = new Color(100, 0, 20);
        setImage(BFImages.IMG_BRICK);
    }

}
