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


/*
        //Image image=null;
        try{
            //File file=(new File("/img/water.gif"));
            image= ImageIO.read(getClass().getResource(BFImages.IMG_WATER));

        }catch(IOException e){
            System.out.println("Error! The picture "+" is not loaded"+" "+e.getMessage());

        }*/
    }

    @Override
    public void destroy() {
        isDestroyed = false;
    }

}
