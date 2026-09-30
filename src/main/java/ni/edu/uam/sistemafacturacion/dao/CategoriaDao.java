package ni.edu.uam.sistemafacturacion.dao;

import ni.edu.uam.sistemafacturacion.config.DatabaseConnection;
import ni.edu.uam.sistemafacturacion.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDao {

    public List<Categoria> listar() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT id, nombre, activa FROM categoria ORDER BY id";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Categoria categoria = new Categoria();
                categoria.setId(resultSet.getInt("id"));
                categoria.setNombre(resultSet.getString("nombre"));
                categoria.setActiva(resultSet.getBoolean("activa"));
                categorias.add(categoria);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las categorías", e);
        }
        return categorias;
    }

    public Categoria buscar(int id) {
        String sql = "SELECT id, nombre, activa FROM categoria WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setId(resultSet.getInt("id"));
                    categoria.setNombre(resultSet.getString("nombre"));
                    categoria.setActiva(resultSet.getBoolean("activa"));
                    return categoria;
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la categoría", e);
        }
        return null;
    }

    public void guardar(Categoria categoria) {
        String sql = "INSERT INTO categoria(nombre, activa) VALUES (?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoria.getNombre());
            statement.setBoolean(2, categoria.isActiva());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la categoría", e);
        }
    }

    public void actualizar(Categoria categoria) {
        String sql = "UPDATE categoria SET nombre = ?, activa = ? WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, categoria.getNombre());
            statement.setBoolean(2, categoria.isActiva());
            statement.setInt(3, categoria.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la categoría", e);
        }
    }

    /**
     * Desactiva la categoría en lugar de borrarla.
     * Si la borraras con DELETE, fallaría en cuanto tuviera productos
     * porque la llave foránea no tiene ON DELETE.
     */
    public void eliminar(int id) {
        String sql = "UPDATE categoria SET activa = false WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Error al desactivar la categoría", e);
        }
    }
}