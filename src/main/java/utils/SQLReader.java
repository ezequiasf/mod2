package utils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SQLReader {

    private static final String pathConsult = "./src/main/java/resources/statements/";

    public static List<String> getSQLStatement (String sqlArq){
        File arc = new File(pathConsult+sqlArq);
        StringBuilder str = new StringBuilder();
        List<String> consults = new ArrayList<>();

        try{
            FileInputStream input = new FileInputStream(arc);
            InputStreamReader inputReader = new InputStreamReader(input);
            BufferedReader reader = new BufferedReader(inputReader);
            reader.lines().forEach(str::append);
            consults.addAll(Arrays.asList(str.toString().split(";")));
        } catch (IOException e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
        return consults;
    }


}
