package bf;


import af.ActionField;
import bf.tanks.BT7;
import bf.tanks.T34;
import bf.tanks.Tank;
import bf.tanks.Tiger;

import java.awt.*;
import java.util.Random;

public class BattleField implements Drawable {

    public static final String BRICK = "B";
    public static final String EAGLE = "E";
    public static final String ROCK = "R";
    public static final String WATER = "W";

    public final int QUADRANT_SIZE=64;
    public final int QUADRANT_COUNT_X=9;
    public final int QUADRANT_COUNT_Y=9;

    private int bfWidth = QUADRANT_SIZE*QUADRANT_COUNT_X;
    private int bfHeight = QUADRANT_SIZE*QUADRANT_COUNT_Y;

    private Tank defender;
    private Tank aggressor;
    private Tank tiger;


    private String[][] battleFieldTemplate = {
            {" ", "W", "W", " ", "B", "B", "B", "B", "B"},
            {" ", " ", " ", "W", " ", " ", " ", " ", "B"},
            {" ", "B", "B", " ", "B", " ", "B", "W", "R"},
            {"R", " ", " ", "W", "W", " ", "B", " ", " "},
            {" ", "B", "B", " ", "B", " ", "W", "B", " "},
            {" ", "B", " ", "W", "B", "B", " ", " ", "B"},
            {"W", "B", " ", " ", " ", " ", "B", "B", " "},
            {" ", "B", " ", "B", "B", "B", " ", " ", "B"},
            {" ", " ", " ", "B", "E", "B", " ", " ", " "}
    };

    private BFObject[][] battleField = new BFObject[QUADRANT_COUNT_X][QUADRANT_COUNT_Y];

    public BattleField() throws Exception {

        String location = getAggressorLocation();
        defender = new T34(this,getStartDefenderLocation()[1], getStartDefenderLocation()[0],Direction.LEFT);
        aggressor = new BT7(this,Integer.parseInt(location.split("_")[1]), Integer.parseInt(location.split("_")[0]), Direction.RIGHT);
        tiger=new Tiger(this);
        drawBattleField();
    }

    public BattleField(String[][] battleField) throws Exception {

        this();
        battleFieldTemplate = battleField;
        drawBattleField();

    }

    private void drawBattleField() {
        for (int i = 0; i < QUADRANT_COUNT_X; i++) {
            for (int j = 0; j < QUADRANT_COUNT_Y; j++) {
                String coordinates = getCoordinates(i + 1, j + 1);
                int separator = coordinates.indexOf("_");
                int y = Integer.parseInt(coordinates
                        .substring(0, separator));
                int x = Integer.parseInt(coordinates
                        .substring(separator + 1));

                String obj = battleFieldTemplate[i][j];
                BFObject bfObject;
                if (obj.equals(BRICK)) {
                    bfObject = new Brick(x, y);
                } else if (obj.equals(ROCK)) {
                    bfObject = new Rock(x, y);
                } else if (obj.equals(EAGLE)) {
                    bfObject = new Eagle(x, y);
                } else if (obj.equals(WATER)) {
                    bfObject = new Water(x, y);
                } else {
                    bfObject = new Blank(x, y);
                }
                battleField[i][j] = bfObject;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        for (int j = 0; j < battleField.length; j++) {
            for (int k = 0; k < battleField.length; k++) {
                battleField[j][k].draw(g);
            }
        }


        defender.draw(g);
        aggressor.draw(g);
        tiger.draw(g);

    }

    public int getBfWidth() {
        return bfWidth;
    }

    public int getBfHeight() {
        return bfHeight;
    }

    public void destroyObject(int v, int h) {
        battleField[v][h].destroy();
    }

    public Tank getAggressor(){
        return aggressor;
    }

    public Tank getDefender(){
        return defender;
    }

    public Tank getTiger(){
        return tiger;
    }

    //private String getQuadrantXY(int v, int h) {
    //    return (v - 1) * QUADRANT_SIZE + "_" + (h - 1) * QUADRANT_SIZE;
    //}

    //get coordinates by quadrant v-vertical, h-horizontal
    //result=y_x
    //public String getQuadrantXY(int v, int h) {
    public String getCoordinates(int v, int h) {
        return (v - 1) * QUADRANT_SIZE + "_" + (h - 1) * QUADRANT_SIZE;
    }

    public int getCoordinate(int quadrant) {
        return (quadrant - 1) * QUADRANT_SIZE;
    }


    //get quadrant by coordinates x,y
    //result=v_h
    public String getQuadrant(int y, int x){
        return y/QUADRANT_SIZE+"_"+x/QUADRANT_SIZE;
    }

    public int getQuadrant(int coordinate){
        return coordinate/QUADRANT_SIZE;
    }

    //
	public BFObject scanQuadrant(int v, int h) {
        //System.out.println("scanQ "+battleField[v][h].isDestroyed());
        if(v<0 || v>=QUADRANT_COUNT_Y || h<0 || h>QUADRANT_COUNT_X){
            return null;
        }

        return battleField[v][h];
	}

    //
    public BFObject scanCoordinates(int y, int x) {
        String quadrant = getQuadrant(y, x);
        int v = Integer.parseInt(quadrant.split("_")[0]);
        int h = Integer.parseInt(quadrant.split("_")[1]);

        BFObject bfobject = scanQuadrant(v, h);
        return battleField[v][h];
    }


    //
    //bfObjectTemplate: B=BRICK E=EAGLE R=ROCK W=WATER;
    //return {y,x} quadrant
    public Integer[] getBFObjectLocation(String bfObjectTemplate){
        for (int i = 0; i < QUADRANT_COUNT_X; i++) {
            for (int j = 0; j < QUADRANT_COUNT_Y; j++) {
                if(bfObjectTemplate.equalsIgnoreCase(battleFieldTemplate[i][j])){
                    return new Integer[]{i,j};
                }
            }
        }
        return new Integer[]{-1,-1};
    }

    //tankName: BT7=aggressor, T34=defender, Tiger=tiger
    //return {y,x} quadrant
    public Integer[] getTankLocation(String tankName){

        if(tankName.equalsIgnoreCase("BT7")){
            return new Integer[]{getQuadrant(aggressor.getY()), getQuadrant(aggressor.getX())};
        }
        else if(tankName.equalsIgnoreCase("T34")){
            return new Integer[]{getQuadrant(defender.getY()), getQuadrant(defender.getX())};
        }
        else if(tankName.equalsIgnoreCase("Tiger")){
            return new Integer[]{getQuadrant(tiger.getY()), getQuadrant(tiger.getX())};
        }

        return new Integer[]{-1,-1};
    }


    //
    public String getAggressorLocation() {
        String[] aggressorPosition=new String[]{"64_64",64*3+"_"+64*5,0+"_"+0};
        Random r=new Random();
        int i=r.nextInt(3);

        return aggressorPosition[i];
        //return 4*64+"_"+3*64;
    }

    //
    public Integer[] getStartDefenderLocation(){

        int eagleH=getBFObjectLocation("E")[1];
        int eagleV=getBFObjectLocation("E")[0];

        int resultH=0;
        int resultV=0;

        if(eagleV==0 ){
            resultH=eagleH;
            resultV=eagleV+2;
        }
        else {
            resultH=eagleH;
            resultV=eagleV-2;
        }
        return new Integer[]{resultV*QUADRANT_SIZE,resultH*QUADRANT_SIZE};
    }


}
