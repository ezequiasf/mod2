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
                InputStreamReader ireader = new InputStreamReader(initContainer()
                        .getInputStream());
                BufferedReader buffer = new BufferedReader(ireader);
                String line;
                while ((line = buffer.readLine()) != null){
                    System.out.println(line);
                }
                if (ReadProperties.loadProperties()!=null)
                {
                    Properties prop = ReadProperties.loadProperties();
                    String urlOracle = prop.getProperty("url");
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
            pb.command("/bin/bash");
            pb.command("-c");
            pb.command("docker pull epiclabs/docker-oracle-xe-11g");
            pb.command("docker run -d -p 1521:1521 -e ORACLE_ALLOW_REMOTE=true -e ORACLE_PASSWORD=oracle -e RELAX_SECURITY=1 epiclabs/docker-oracle-xe-11g");
            p = pb.start();
        }catch(IOException ex){
            System.err.println(ex.getMessage());
        }
        return p;
    }

    private static void createInitTables (Connection con) {
       List<String> consults =  SQLReader.getSQLStatement("initDB.sql");
       Statement st = null;
       try {
            st = con.createStatement();
           for (String consult : consults) {
               try {
                   st.executeUpdate(consult);
               } catch (SQLException e) {
                   e.printStackTrace();
               } finally {
                   closeStatement(st);
               }
           }
       } catch (SQLException e) {
            e.printStackTrace();
        }finally {
           closeStatement(st);
       }
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
