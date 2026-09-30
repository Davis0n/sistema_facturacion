package ni.edu.uam.sistemafacturacion.dao;

import ni.edu.uam.sistemafacturacion.config.DatabaseConnection;
import ni.edu.uam.sistemafacturacion.model.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDao {
    private static final String SELECT_BASE =
            "SELECT p.id, p.codigo, p.nombre, p.precio_venta, p.existencia, "
            +" p.ruta_imagen, p.activo, p.categoria_id, "
            +" c.nombre AS categoria_nombre, c.activa AS categoria_activa "
            +"FROM producto p "
            +"INNER JOIN categoria c ON p.categoria_id = c.id";
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        String sql = SELECT_BASE + "ORDER BY p.id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet rs =statement.executeQuery()) {}
        )
    }
}
