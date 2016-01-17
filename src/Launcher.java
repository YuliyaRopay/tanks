import af.ActionField;

import java.awt.*;


public class Launcher {

    public static void main(String[] args) throws Exception {

        SplashScreen splash=SplashScreen.getSplashScreen();
        Thread.sleep(5000);
        splash.close();

        ActionField af = new ActionField();
        af.runTheGame();
    }


}
