package config;

import utils.ReadProperties;
import utils.SQLReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class ConfigAmbienteJDBC {

    private static Connection con;
    //TODO: Adicionar sistema de log
    public static Connection getConnection()
    {
        try
        {
            if (con==null)
            {
                String resultProcess = readConsole(initContainer());
                System.out.println(resultProcess);
                if (ReadProperties.loadProperties()!=null)
                {
                    Properties prop = ReadProperties.loadProperties();
                    String urlOracle = prop.getProperty("dburl");
                    con = DriverManager.getConnection(urlOracle, prop);
                    createInitTables(con);
                }
            }
        }catch(SQLException | IOException ex)
        {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return con;
    }

    private static Process initContainer (){
        Process p = null;
        try{
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("/bin/bash", "-c", "docker pull epiclabs/docker-oracle-xe-11g &&" +
                    "docker run -d -p 1521:1521 -e ORACLE_ALLOW_REMOTE=true -e ORACLE_PASSWORD=oracle -e " +
                    "RELAX_SECURITY=1 --name container-oracle epiclabs/docker-oracle-xe-11g");
            p = pb.start();
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        return p;
    }

    public static Process finishConnection (Connection con){
        closeConnection(con);
        Process p = null;
        try{
            ProcessBuilder pb = new ProcessBuilder();
            pb.command("/bin/bash", "-c", "docker stop oracle-container && docker rm oracle-container");
            p = pb.start();
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        return p;
    }

    private static void createInitTables (Connection con) {
       List<String> consults =  SQLReader.getSQLStatement("initDB.sql");
       consults.forEach(System.out::println);
       //PreparedStatement st = null;
       consults.forEach(consult -> {
           try {
               PreparedStatement psmt = con.prepareStatement(consult);
               psmt.execute();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       });
    }

    private static String readConsole (Process p) throws IOException {
        InputStreamReader ireader = new InputStreamReader(p.getInputStream());
        BufferedReader buffer = new BufferedReader(ireader);
        String line;
        StringBuilder sb = new StringBuilder();
        while ((line = buffer.readLine()) != null){
            sb.append(line);
            sb.append(System.getProperty("line.separator"));
        }
        return sb.toString();
    }

    public static void closeConnection(Connection con){
        if(con!=null){
            try{
                con.close();
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public static void closeStatement(Statement st) {
        if(st!=null){
            try{
                st.close();
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }

    public static void closeResultSet(ResultSet rs){
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                System.err.println(e.getMessage());
            }
        }
    }
}
