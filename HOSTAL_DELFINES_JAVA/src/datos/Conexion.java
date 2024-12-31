package datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    private static Connection connect;
    public static Connection getInstance() {
        if (connect == null) {
            try {
                connect = DriverManager.getConnection("jdbc:mysql://localhost/dbhostaldelfines", "root", "dbasql");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
        return connect;
    }
}
