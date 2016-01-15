package bf.tanks;

import bf.BFImages;
import bf.Direction;
import bf.BattleField;

import java.awt.*;

public class T34 extends AbstractTank {

    public T34(BattleField bf) {
        this(bf,128,512,Direction.TOP);
    }

    public T34(BattleField bf, int x, int y, Direction direction) {

        super(bf, x, y, direction);

        tankColor=new Color(0,255,0);
        towerColor=new Color(255,0,0);

        tankBottomImg=setImage(BFImages.IMG_T34_BOTTOM);
        tankTopImg=setImage(BFImages.IMG_T34_TOP);
        tankLeftImg=setImage(BFImages.IMG_T34_LEFT);
        tankRightImg=setImage(BFImages.IMG_T34_RIGHT);
    }

    private Object[] actoins = new Object[] {
            Direction.RIGHT,
            Action.FIRE,
            Action.FIRE,
            Direction.RIGHT,
            Action.FIRE,
            Action.MOVE,
            Direction.TOP,
            Action.FIRE,
            Action.MOVE,
            Direction.LEFT,
            Action.FIRE,
            Action.MOVE,
            Action.FIRE,
            Action.MOVE,
            Direction.TOP,
            Action.FIRE,
            Action.MOVE,
            Direction.LEFT,
            Action.FIRE,
            Action.MOVE,
            Direction.BOTTOM,
            Action.FIRE,
            Action.MOVE
    };

    private int step = 0;

    @Override
    public Action setUp() {
        /*
        if (step >= actoins.length) {
            step = 0;
        }
        if (!(actoins[step] instanceof Action)) {
            turn((Direction) actoins[step++]);
        }
        if (step >= actoins.length) {
            step = 0;
        }
        return (Action) actoins[step++];
        */

        return protectEagle();
    }


    //
    private Action protectEagle(){

        if(!bf.getTiger().isDestroyed()){
            return  getActionToTargetObject2("Tiger");
        }
        return getActionToTargetObject2("BT7");
    }

}
