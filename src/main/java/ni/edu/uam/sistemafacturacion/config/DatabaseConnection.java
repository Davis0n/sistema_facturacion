package ni.edu.uam.sistemafacturacion.config;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    private static final String URL =
            "jdbc:postgresql://localhost:5432/tienda_javafx";
    private static final String USER =
            "postgres";
    private static final String PASSWORD =
            System.getenv("DB_PASSWORD");
    public static Connection getConnection() throws SQLException {
        if (PASSWORD == null || PASSWORD.isBlank()) {
            throw new SQLException(
                    "No se encontro la variable DB_PASSWORD. Configurala con la contrasena de postgres."
            );
        }
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
