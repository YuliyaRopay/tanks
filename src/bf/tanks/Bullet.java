package bf.tanks;

import bf.BFImages;
import bf.Direction;
import bf.Destroyable;
import bf.Drawable;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.File;
import java.io.IOException;

public class Bullet implements Drawable, Destroyable {

    private int speed;
    private int x;
    private int y;
    private Direction direction;
    private boolean destroyed;
    private Image image;

    public Bullet(int x, int y, Direction direction) {
        this.speed=10;
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.destroyed = false;
        image=setImage(BFImages.IMG_BULLET);
    }

    public int getSpeed() {
        return speed;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public void updateX(int x){
        this.x+=x;
    }

    public void updateY(int y){
        this.y+=y;
    }

    public void destroy(){
        destroyed = true;
    }


    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed) {
            if(image != null){
                g.drawImage(image, x, y,new ImageObserver(){
                    @Override
                    public boolean imageUpdate(Image img, int infoflags, int x, int y, int width, int height){
                        return false;
                    }
                });
            }
            else{
                g.setColor(new Color(255, 255, 0));
                g.fillRect(this.x, this.y, 14, 14);
            }

        }
    }

    private Image setImage(String nameImg){
        Image image=null;
        try{
            image= ImageIO.read(getClass().getResource(nameImg));

        }catch(IOException e){
            System.out.println("Error! The picture "+nameImg+" is not loaded"+" "+e.getMessage());

        }
        return image;
    }
}
