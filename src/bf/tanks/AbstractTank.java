package bf.tanks;


import bf.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AbstractTank implements Tank {

    private int speed = 10;
    protected int movePath = 1;

    private Direction direction;

    private int x;
    private int y;

    private boolean destroyed;

    protected BattleField bf;

    protected Color tankColor;
    protected Color towerColor;

    protected Image tankTopImg;
    protected Image tankBottomImg;
    protected Image tankLeftImg;
    protected Image tankRightImg;

    protected Bullet bullet;

    public AbstractTank(BattleField bf) {
        this(bf, 0, 512, Direction.TOP);
    }

    public AbstractTank(BattleField bf, int x, int y, Direction direction) {
        this.bf = bf;
        this.x = x;
        this.y = y;
        this.direction = direction;
        bullet = new Bullet(-100, -100, Direction.NONE);
        this.destroyed = false;
    }

    @Override
    public void draw(Graphics g) {
        if (!destroyed && !this.drawImg(g)) {
                g.setColor(tankColor);
                g.fillRect(this.getX(), this.getY(), 64, 64);

                g.setColor(towerColor);
                if (this.getDirection() == Direction.TOP) {
                    g.fillRect(this.getX() + 20, this.getY(), 24, 34);
                } else if (this.getDirection() == Direction.BOTTOM) {
                    g.fillRect(this.getX() + 20, this.getY() + 30, 24, 34);
                } else if (this.getDirection() == Direction.LEFT) {
                    g.fillRect(this.getX(), this.getY() + 20, 34, 24);
                } else {
                    g.fillRect(this.getX() + 30, this.getY() + 20, 34, 24);
                }

        }
    }


    protected Image setImage(String nameImg){
        Image image=null;
        try{
            File file=(new File(nameImg));
            image= ImageIO.read(file);

        }catch(IOException e){
            System.out.println("Error! The picture "+nameImg+" is not loaded"+" "+e.getMessage());

        }
        return image;
    }

    private boolean drawImg(Graphics g){
        if(tankBottomImg==null || tankTopImg==null || tankLeftImg==null || tankRightImg==null){
            return false;
        }
        else{

            Graphics2D g2D=(Graphics2D) g;
            Composite origin=g2D.getComposite();
            if(this.bf.scanCoordinates(getY(), getX()) instanceof Water){
                Composite transparent=AlphaComposite.getInstance(AlphaComposite.SRC_OVER,0.5f);
                g2D.setComposite(transparent);
            }


            if (this.getDirection() == Direction.RIGHT) {
                g.drawImage(tankRightImg, getX(), getY(), null);

            } else if (this.getDirection() == Direction.TOP) {
                g.drawImage(tankTopImg, getX(), getY(), null);
            } else if (this.getDirection() == Direction.BOTTOM) {
                g.drawImage(tankBottomImg, getX(), getY(), null);
            } else {
                g.drawImage(tankLeftImg, getX(), getY(), null);
            }

            g2D.setComposite(origin);
        }


        return true;
    }


    public void turn(Direction direction) {
        this.direction = direction;
    }

    @Override
    public boolean isDestroyed() {
        return destroyed;
    }

    @Override
    public void destroy() {
        destroyed = true;
    }

    @Override
    public Bullet fire(){
        int bulletX = -100;
        int bulletY = -100;
        if (direction == Direction.TOP) {
            bulletX = x + 25;
            bulletY = y - 63;
        } else if (direction == Direction.BOTTOM) {
            bulletX = x + 25;
            bulletY = y + 63;
        } else if (direction == Direction.LEFT) {
            bulletX = x - 63;
            bulletY = y + 25;
        } else if (direction == Direction.RIGHT) {
            bulletX = x + 63;
            bulletY = y + 25;
        }
        return new Bullet(bulletX, bulletY, direction);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    @Override
    public void updateX(int x) {
        this.x += x;
    }

    @Override
    public void updateY(int y) {
        this.y += y;
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public int getMovePath() {
        return movePath;
    }


    @Override
    public Action setUp() {
        return null;
    }

    @Override
    public void move() {

    }

    @Override
    public Bullet getBullet(){
        return bullet;
    }

    //
    protected Action getActionToNextQuadrant(int v, int h){

        Tank scanTank=bf.scanQuadrantTank(v,h);

        if(scanTank!=null){
            System.out.println("tank fire  "+v+" "+h);
            return Action.FIRE;
        }

        BFObject bfObject= (BFObject) bf.scanQuadrant(v,h);

        if(bfObject instanceof Blank || bfObject instanceof Water || bfObject.isDestroyed()){
            //System.out.println("move "+v+" "+h);
            return Action.MOVE;
        }
        if(bfObject instanceof Brick || bfObject instanceof Eagle || bfObject instanceof Rock){
            //System.out.println("fire "+v+" "+h);
            return Action.FIRE;
        }

        //System.out.println("none "+v+" "+h);
        return Action.NONE;
    }


    //
    //bfObjectTemplate: B=BRICK E=EAGLE R=ROCK W=WATER;
    //tankName: BT7=aggressor, T34=defender, Tiger=tiger
    protected Action getActionToTargetObject(String bfTargetObject){

        int resultH=0;
        int resultV=0;

        if(bfTargetObject.equalsIgnoreCase("BT7") || bfTargetObject.equalsIgnoreCase("T34")
                || bfTargetObject.equalsIgnoreCase("Tiger")){
            resultH=bf.getTankLocation(bfTargetObject)[1];
            resultV=bf.getTankLocation(bfTargetObject)[0];
        }
        else {
            resultH=bf.getBFObjectLocation(bfTargetObject)[1];
            resultV=bf.getBFObjectLocation(bfTargetObject)[0];
        }


        int h=bf.getQuadrant(getX());
        int v=bf.getQuadrant(getY());

        // System.out.println("AT: Result target x="+resultH+" y="+resultV);
        //System.out.println("AT: current x="+h+" y="+v);



        if (resultH > h && getActionToNextQuadrant(v, h + 1)!= Action.NONE){
            turn(Direction.RIGHT);
            //System.out.println(bf.scanQuadrant(v,h+1)+" R "+v+" "+(h+1));
            return getActionToNextQuadrant(v, h + 1);
        }
        if (resultH < h && getActionToNextQuadrant(v, h - 1)!= Action.NONE){
            turn(Direction.LEFT);
            //System.out.println(bf.scanQuadrant(v,h-1)+" L");
            return getActionToNextQuadrant(v, h - 1);
        }
        if (resultV > v && getActionToNextQuadrant(v + 1, h)!= Action.NONE) {
            turn(Direction.BOTTOM);
            //System.out.println(bf.scanQuadrant(v+1,h)+" B");
            return getActionToNextQuadrant(v+1,h);
        }
        if (resultV < v && getActionToNextQuadrant(v - 1, h)!= Action.NONE) {
            turn(Direction.TOP);
            //System.out.println(bf.scanQuadrant(v-1,h)+" T");
            return getActionToNextQuadrant(v - 1, h);
        }


        return Action.NONE;
    }



}
