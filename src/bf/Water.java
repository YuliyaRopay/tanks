package bf;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Water extends SimpleBFObject {

    private boolean isDestroyed = false;

    public Water(int x, int y) {
        super(x, y);
        color = new Color(100,50, 255);
        setImage(BFImages.IMG_WATER);
    }

    @Override
    public void destroy() {
        isDestroyed = false;
    }


}
