package bf;


import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public abstract class SimpleBFObject implements BFObject {

    private int x;
    private int y;

    protected Color color;

    private boolean isDestroyed = false;

    protected Image image;

    public SimpleBFObject(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void destroy() {
        isDestroyed = true;
    }

    @Override
    public void draw(Graphics g) {
        if (!isDestroyed) {
            if(image != null){
                g.drawImage(image, x, y,new ImageObserver(){
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
                        return false;
                    }
                });
            }
            else{
                g.setColor(this.color);
                g.fillRect(this.getX(), this.getY(), 64, 64);
            }
        }
    }

    public boolean isDestroyed() {
        return isDestroyed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    protected void setImage(String nameImg){
        try{
            //File file=(new File(nameImg));
            //image= ImageIO.read(file);
            image= ImageIO.read(getClass().getResource(nameImg));

        }catch(IOException e){
            System.out.println("Error! The picture "+nameImg+" is not loaded"+" "+e.getMessage());

        }
    }
}
