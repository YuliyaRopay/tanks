package af;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

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


    public void write(String data){
        try(FileWriter fileWriter = new FileWriter(filePath, true)){
            fileWriter.write(data+"\n");
        }catch (IOException e){
            e.printStackTrace();
        }

    }

}
