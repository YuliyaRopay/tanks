package bf;

import java.awt.*;

public class Eagle extends SimpleBFObject {

    public Eagle(int x, int y) {
        super(x, y);
        color = new Color(255, 255, 0);
        setImage(BFImages.IMG_EAGLE);

    }
}
