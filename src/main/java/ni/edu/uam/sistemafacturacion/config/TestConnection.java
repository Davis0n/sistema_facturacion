package ni.edu.uam.sistemafacturacion.config;

import java.sql.Connection;

public class TestConnection {

    public static void main(String[] args) throws Exception {

        try (Connection c = DatabaseConnection.getConnection()) {

            System.out.println("Conexión exitosa");

            System.out.println(c.getMetaData().getDatabaseProductName()
                    + " " + c.getMetaData().getDatabaseProductVersion());

            System.out.println("Base de datos: " + c.getCatalog());
        }
    }
}