package config;

import utils.ReadProperties;
import utils.SQLReader;

import exceptions.DBException;
import java.sql.*;
import java.util.List;
import java.util.Properties;

public class ConfigAmbienteJDBC {

    private static Connection con;

    //TODO: Adicionar sistema de log
    public static Connection getConnection(){
        try{
            if (con==null){
                if (ReadProperties.loadProperties()!=null){
                    Properties prop = ReadProperties.loadProperties();
                    String urlOracle = prop.getProperty("url");
                    con = DriverManager.getConnection(urlOracle, prop);
                }
            }
        }catch(SQLException ex){
            System.err.println(ex.getMessage());
            ex.printStackTrace();
        }
        return con;
    }

    private static void initContainer (){
        //TODO: Adicionar shell script para executar o container
    }

    private static void createInitTables (Connection con) {
       List<String> consults =  SQLReader.getSQLStatement("initDB.sql");
       Statement st = null;
       try {
            st = con.createStatement();
           Statement finalSt = st;
           for (String consult : consults) {
               try {
                   finalSt.executeUpdate(consult);
               } catch (SQLException e) {
                   e.printStackTrace();
               } finally {
                   closeStatement(finalSt);
               }
           }
       } catch (SQLException | DBException e) {
            e.printStackTrace();
        }finally {
           try {
               closeStatement(st);
           } catch (DBException e) {
               System.err.println(e.getMessage());
               e.printStackTrace();
           }
       }
    }

    public static void closeConnection() throws DBException{
        if(con!=null){
            try{
                con.close();
            }catch(SQLException e){
                throw new DBException("Erro ao carregar arquivo de config.");
            }
        }
    }

    public static void closeStatement(Statement st) throws DBException {
        if(st!=null){
            try{
                st.close();
            }catch(SQLException e){
                throw new DBException("Erro ao tentar encerrar o statement.");
            }
        }
    }

    public static void closeResultSet(ResultSet rs) throws DBException {
        if(rs!=null){
            try{
                rs.close();
            }catch(SQLException e){
                throw new DBException("Erro ao tentar encerrar o result set.");
            }
        }
    }
}
