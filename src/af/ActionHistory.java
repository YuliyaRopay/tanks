package af;


import bf.tanks.Action;
import bf.tanks.Tank;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ActionHistory {

    private final String fileHistoryName="tank_action.txt";
    private File filePath;


    public ActionHistory() {
        filePath=new File(fileHistoryName);
        if(!filePath.exists()){
            try {
                filePath.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void write(Tank tank, Action a){
        String tankName=tank.getClass().getSimpleName();
        Date data=new Date();
        SimpleDateFormat datFormat=new SimpleDateFormat("yy/MM/dd hh:mm:ss");

        this.write(datFormat.format(data)+"#"+tankName+"#"+tank.getX()+"#"+tank.getY()+"#"+tank.getDirection()
                +"#"+a.name()+"#");
    }

    public void write(String data){
        try(FileWriter fileWriter = new FileWriter(filePath, true)){
            fileWriter.write(data+"\n");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
