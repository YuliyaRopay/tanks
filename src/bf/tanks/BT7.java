package bf.tanks;


import bf.*;

import java.awt.*;
import java.util.ArrayList;

public class BT7 extends AbstractTank {

    public BT7(BattleField bf) {
        this(bf,128,512,Direction.TOP);
    }

    public BT7(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
        movePath = 1;

        tankBottomImg=setImage(BFImages.IMG_BT7_BOTTOM);
        tankTopImg=setImage(BFImages.IMG_BT7_TOP);
        tankLeftImg=setImage(BFImages.IMG_BT7_LEFT);
        tankRightImg=setImage(BFImages.IMG_BT7_RIGHT);
    }

    @Override
    public Action setUp() {

        return destroyEagle();

     }


    //
    private Action destroyEagle(){
        return getActionToTargetObject("E");

     }



   //////////////////////////////////////////////////////////

/*
    private Action destroyEagle5(){

        ArrayList<QuadrantStep> resultPath=getPathToTarget3(this, "E");
        for(QuadrantStep step: resultPath){
            System.out.println("->> "+step.toString());
        }


       // QuadrantStep currentStep=resultPath.get(0);
        if(resultPath.size()>1){
            QuadrantStep nextStep=resultPath.get(1);
            System.out.println("step next : "+nextStep);

            //turn(Direction.RIGHT);
            //System.out.println(bf.scanQuadrant(v,h+1)+" R "+v+" "+(h+1));
            //return Action.NONE;


            turn(nextStep.getDirection());
            return getAction(nextStep.getY(),nextStep.getX());
        }
        return Action.NONE;
    }


    //
    private Integer[][] getPathToTarget(Tank tank,String objectTemplate){

        Integer [][] pathToTarget=new Integer[bf.QUADRANT_COUNT_X*bf.QUADRANT_COUNT_Y][];

        int resultH=bf.getBFObjectLocation(objectTemplate)[1];
        int resultV=bf.getBFObjectLocation(objectTemplate)[0];

        int h=bf.getQuadrant(tank.getX());
        int v=bf.getQuadrant(tank.getY());
        Direction newDirection= Direction.NONE;


        System.out.println("start->"+v+" "+h+" "+bf.scanQuadrant(v,h));

        pathToTarget[0]=new Integer[]{v,h};
        int i=1;

        while (resultH != h) {
            if (resultH > h) {
                newDirection= Direction.RIGHT;
                h=h+1;

                if(getAction(v,h)== Action.NONE){
                    h=h-1;
                    if (resultV > v) {
                        newDirection= Direction.BOTTOM;
                        v=v+1;

                    } else {
                        newDirection= Direction.TOP;
                        v=v-1;
                    }
                    //System.out.println("1.1-> "+v+" "+h);
                }


            } else {
                newDirection= Direction.LEFT;
                h=h-1;

                if(getAction(v,h)== Action.NONE){
                    h=h+1;
                    if (resultV > v) {
                        newDirection= Direction.BOTTOM;
                        v=v+1;

                    } else {
                        newDirection= Direction.TOP;
                        v=v-1;
                    }
                    //System.out.println("1.2-> "+v+" "+h);
                }

            }

            //System.out.println("1->"+v+" "+h+" "+newDirection+" "+bf.scanQuadrant(v,h)+" "+getAction(v,h));
           // System.out.println("1-> "+v+" "+h+" "+getAction(v,h));
            pathToTarget[i]=new Integer[]{v,h};
            i++;
        }

        while (resultV != v) {
            if (resultV > v) {
                newDirection= Direction.BOTTOM;
                v=v+1;

                ////////
                if(getAction(v,h)== Action.NONE){
                    v=v-1;
                    if (resultH > h) {
                        newDirection= Direction.RIGHT;
                        h=h+1;

                    } else {
                        newDirection= Direction.LEFT;
                        h=h-1;

                    }
                }


                ////////


            } else {
                newDirection= Direction.TOP;
                v=v-1;
                if(getAction(v,h)== Action.NONE){
                    v=v+1;
                    if (resultH > h) {
                        newDirection= Direction.RIGHT;
                        h=h+1;

                    } else {
                        newDirection= Direction.LEFT;
                        h=h-1;

                    }
                }
            }
           // System.out.println("2->"+v+" "+h+" "+newDirection+" "+bf.scanQuadrant(v,h)+" " + getAction(v,h));
           // System.out.println("2-> "+v+" "+h+" "+getAction(v,h));
            pathToTarget[i]=new Integer[]{v,h};
            i++;
        }

       for(int j=0;j<i;j++){
           System.out.println("f  "+pathToTarget[j][0]+" "+pathToTarget[j][1]);
       }

        return pathToTarget;
    }


    ///////////
    private ArrayList<QuadrantStep> getPathToTarget2(Tank tank,String objectTemplate){

        //Integer [][] pathToTarget=new Integer[bf.QUADRANT_COUNT_X*bf.QUADRANT_COUNT_Y][];
        ArrayList<QuadrantStep> pathToTarget=new ArrayList<QuadrantStep>();

        int resultH=bf.getBFObjectLocation(objectTemplate)[1];
        int resultV=bf.getBFObjectLocation(objectTemplate)[0];

        int h=bf.getQuadrant(tank.getX());
        int v=bf.getQuadrant(tank.getY());
        Direction newDirection= Direction.NONE;


        System.out.println("start->"+v+" "+h+" "+bf.scanQuadrant(v,h));

        pathToTarget.add(new QuadrantStep(v, h, newDirection, Action.NONE));


        int tmpH;
        int tmpV;
        while (resultH != h) {
            tmpH=h;
            tmpV=v;
            if (resultH > h) {
                newDirection= Direction.RIGHT;
                h=h+1;

                if(getAction(v,h)== Action.NONE){
                    h=h-1;
                    if (resultV > v) {
                        newDirection= Direction.BOTTOM;
                        v=v+1;

                    } else {
                        newDirection= Direction.TOP;
                        v=v-1;
                    }
                    //System.out.println("1.1-> "+v+" "+h);
                }


            } else {
                newDirection= Direction.LEFT;
                h=h-1;

                if(getAction(v,h)== Action.NONE){
                    h=h+1;
                    if (resultV > v) {
                        newDirection= Direction.BOTTOM;
                        v=v+1;

                    } else {
                        newDirection= Direction.TOP;
                        v=v-1;
                    }
                    //System.out.println("1.2-> "+v+" "+h);
                }

            }

            /////////

            if(getAction(v,h)== Action.NONE){
                System.out.println("new rez0="+v+" "+h+" "+newDirection+getAction(v,h));
                v=tmpV;
                h=tmpH;

                if(getAction(v,h+1)!= Action.NONE){
                    newDirection= Direction.RIGHT;
                    h=h+1;
                }
                else if(getAction(v-1,h)!= Action.NONE){
                    newDirection= Direction.TOP;
                    v=v-1;
                }
                else if(getAction(v,h-1)!= Action.NONE){
                    newDirection= Direction.LEFT;
                    h=h-1;
                }
                else{
                    newDirection= Direction.BOTTOM;
                    v=v+1;
                }
                System.out.println("new rez="+v+" "+h+" "+newDirection+getAction(v, h));
            }
            //////

            //System.out.println("1->"+v+" "+h+" "+newDirection+" "+bf.scanQuadrant(v,h)+" "+getAction(v,h));
            // System.out.println("1-> "+v+" "+h+" "+getAction(v,h));
            pathToTarget.add(new QuadrantStep(v, h, newDirection, getAction(v,h)));

          }

        while (resultV != v) {
            if (resultV > v) {
                newDirection= Direction.BOTTOM;
                v=v+1;

                ////////
                if(getAction(v,h)== Action.NONE){
                    v=v-1;
                    if (resultH > h) {
                        newDirection= Direction.RIGHT;
                        h=h+1;

                    } else {
                        newDirection= Direction.LEFT;
                        h=h-1;

                    }
                }


                ////////


            } else {
                newDirection= Direction.TOP;
                v=v-1;
                if(getAction(v,h)== Action.NONE){
                    v=v+1;
                    if (resultH > h) {
                        newDirection= Direction.RIGHT;
                        h=h+1;

                    } else {
                        newDirection= Direction.LEFT;
                        h=h-1;

                    }
                }
            }
            // System.out.println("2->"+v+" "+h+" "+newDirection+" "+bf.scanQuadrant(v,h)+" " + getAction(v,h));
            // System.out.println("2-> "+v+" "+h+" "+getAction(v,h));
            pathToTarget.add(new QuadrantStep(v, h, newDirection, getAction(v,h)));
        }

           for(QuadrantStep step :pathToTarget){
               System.out.println(step.getY()+" "+step.getX()+" "+step.getDirection());
           }


        //
        QuadrantStep stepTmp=new QuadrantStep(0,0,null,null);

        for (int i=0; i<pathToTarget.size();i++){
            QuadrantStep step=pathToTarget.get(i);
            for(int j=i+1; j<pathToTarget.size();j++){
                QuadrantStep nextStep=pathToTarget.get(j);
                if(step.getY()==nextStep.getY() && step.getX()==nextStep.getX()){

                    for(int k=i+1;k<=j;k++){
                        System.out.println("remove="+"k="+k+"  "+pathToTarget.get(k));
                        pathToTarget.set(k, stepTmp);
                    }
                    i=j;
                }

            }
        }

        for(int i=0; i<pathToTarget.size();i++){
            if(pathToTarget.get(i).equals(stepTmp)){
                pathToTarget.remove(i);
                i--;
            }
        }


        return pathToTarget;
    }


    //////////////////////////////////////////////////////////////////
    //888888888888888888888888888888888888888888888888888888888888888888888

    private ArrayList<QuadrantStep> getPathToTarget3(Tank tank,String objectTemplate){


        ArrayList<QuadrantStep> pathToTarget=new ArrayList<QuadrantStep>();

        int resultH=bf.getBFObjectLocation(objectTemplate)[1];
        int resultV=bf.getBFObjectLocation(objectTemplate)[0];

        int h=bf.getQuadrant(tank.getX());
        int v=bf.getQuadrant(tank.getY());
        Direction newDirection= Direction.NONE;


        System.out.println("start->"+v+" "+h+" "+bf.scanQuadrant(v,h));

        pathToTarget.add(new QuadrantStep(v, h, newDirection, Action.NONE));

        int tmpH=h;
        int tmpV=v;

        int i=1;
        while(i<10){
            if (h<bf.QUADRANT_COUNT_X-1 && (resultH > h || resultV == v)) {
                newDirection= Direction.RIGHT;
                h=h+1;
            }
            else if(h<bf.QUADRANT_COUNT_X-1 && (resultH < h || resultV == v)){
                newDirection= Direction.LEFT;
                h=h-1;
            }
            else if(v<bf.QUADRANT_COUNT_Y-1 && (resultV > v || resultH == h)) {
                newDirection= Direction.BOTTOM;
                v=v+1;
            }
            else if(v<bf.QUADRANT_COUNT_Y-1 && (resultV < v || resultH == h)){
                newDirection= Direction.TOP;
                v=v-1;
            }


            if(getAction(v,h)== Action.NONE) {
                System.out.println("new rez0=" + v + " " + h + " " + newDirection + getAction(v, h));
                v = tmpV;
                h = tmpH;


                QuadrantStep step=null;

                if (h<bf.QUADRANT_COUNT_X-1 && getAction(v, h + 1) != Action.NONE) {
                    newDirection = Direction.RIGHT;
                    h = tmpH + 1;
                    step=new QuadrantStep(v, h, newDirection, getAction(v,h));

                }
                if (v<bf.QUADRANT_COUNT_Y && (getAction(v - 1, h) != Action.NONE  && pathToTarget.get(i-1).equals(step))) {
                    newDirection = Direction.TOP;
                    h=tmpH;
                    v = tmpV - 1;
                    step=new QuadrantStep(v, h, newDirection, getAction(v,h));
                }
                if (h<bf.QUADRANT_COUNT_X && (getAction(v, h - 1) != Action.NONE && pathToTarget.get(i-1).equals(step))) {
                    newDirection = Direction.LEFT;
                    v=tmpV;
                    h = tmpH - 1;
                }
                else if(v<bf.QUADRANT_COUNT_Y-1){
                    newDirection = Direction.BOTTOM;
                    v = tmpV + 1;
                    h=tmpH;
                }


            }

            System.out.println("new rez1="+v+" "+h+" "+newDirection+getAction(v, h));
            pathToTarget.add(new QuadrantStep(v, h, newDirection, getAction(v,h)));

            i++;
        }

        return pathToTarget;
    }
    */
}
