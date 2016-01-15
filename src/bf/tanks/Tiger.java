package bf.tanks;


import bf.BFImages;
import bf.Direction;
import bf.BattleField;

import java.awt.*;

public class Tiger extends AbstractTank {

    private int armor;

    public Tiger(BattleField bf) {
        this(bf,0,256,Direction.RIGHT);
    }

    public Tiger(BattleField bf, int x, int y, Direction direction) {
        super(bf, x, y, direction);
        tankColor = new Color(255, 0, 0);
        towerColor = new Color(0, 255, 0);
        armor = 1;

        tankBottomImg=setImage(BFImages.IMG_TIGER_BOTTOM);
        tankTopImg=setImage(BFImages.IMG_TIGER_TOP);
        tankLeftImg=setImage(BFImages.IMG_TIGER_LEFT);
        tankRightImg=setImage(BFImages.IMG_TIGER_RIGHT);
    }

    @Override
    public void destroy() {

        if (armor > 0) {
            armor--;
        } else {
            super.destroy();
        }

       // super.destroy();
    }

    private int step = 0;

    @Override
    public Action setUp() {
        if (step==0){
            step=1;
            return Action.FIRE;
        }
        else{
            step=0;
            return destroyDefender();
        }
    }


    //
    private Action destroyDefender(){
        return getActionToTargetObject("T34");
    }

}
