package exec;

import config.ConfigAmbienteJDBC;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        Connection con = ConfigAmbienteJDBC.getConnection();
    }
}
