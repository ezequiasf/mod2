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

    public static Connection getConnection() {
        try
        {
            if (con==null)
            {
                if (ReadProperties.loadProperties()!=null)
                {
                    Properties prop = ReadProperties.loadProperties();
                    String urlOracle = prop.getProperty("dburl");
                    con = DriverManager.getConnection(urlOracle, prop);
                }
            }
        }catch(SQLException  ex )
        {
            System.out.println("Estrutura já criada, retornando a conexão...");
        }
        return con;
    }

    private static void createInitTables (Connection con) {

        List<String> consults =  SQLReader.getSQLStatement("initDB.sql");
        consults.forEach(consult -> {
           try {
               PreparedStatement psmt = con.prepareStatement(consult);
               psmt.execute();
           } catch (SQLException e) {
               e.printStackTrace();
           }
       });
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
