package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

    public static Properties loadProperties(){
        Properties prop = null;
        try(FileInputStream fs = new FileInputStream("./src/main/java/resources/db.properties")){
            prop = new Properties();
            prop.load(fs);
        }catch(IOException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return prop;
    }
}
