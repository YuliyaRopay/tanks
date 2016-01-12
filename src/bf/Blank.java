package bf;


import java.awt.*;

public class Blank extends SimpleBFObject {


    public Blank(int x, int y) {
        super(x, y);
        color = new Color(180, 180, 180);

        setImage(BFImages.IMG_BLANK);
    }


}
