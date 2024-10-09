package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.Producto;

/**
 * Clase ProductoDAO que proporciona métodos para realizar operaciones de acceso a datos
 * sobre productos en la base de datos.
 */
public class ProductoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    /**
     * Guarda un nuevo producto en la base de datos.
     * 
     * @param producto El objeto Producto a guardar.
     * @return true si la operación se realizó con éxito, false en caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean guardar(Producto producto) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "INSERT INTO productos (nombre, cantidad, precio, fecha_crear, fecha_actualizar) VALUES(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            if (producto.getFechaActualizar() == null) {
                producto.setFechaActualizar(new Timestamp(System.currentTimeMillis())); 
            }

            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getCantidad());
            statement.setDouble(3, producto.getPrecio());
            statement.setTimestamp(4, producto.getFechaCrear());
            statement.setTimestamp(5, producto.getFechaActualizar());

            estadoOperacion = statement.executeUpdate() > 0;

            connection.commit();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    /**
     * Edita un producto existente en la base de datos.
     * 
     * @param producto El objeto Producto con los nuevos datos.
     * @return true si la operación se realizó con éxito, false en caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean editar(Producto producto) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            if (producto.getFechaActualizar() == null) {
                producto.setFechaActualizar(new Timestamp(System.currentTimeMillis()));
            }

            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getCantidad());
            statement.setDouble(3, producto.getPrecio());
            statement.setTimestamp(4, producto.getFechaActualizar());
            statement.setInt(5, producto.getId());

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    /**
     * Elimina un producto de la base de datos.
     * 
     * @param idProducto El ID del producto a eliminar.
     * @return true si la operación se realizó con éxito, false en caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean eliminar(int idProducto) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "DELETE FROM productos WHERE id=?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProducto);

            estadoOperacion = statement.executeUpdate() > 0;
            connection.commit();
            statement.close();
            connection.close();

        } catch (SQLException e) {
            connection.rollback();
            e.printStackTrace();
        }

        return estadoOperacion;
    }

    /**
     * Obtiene una lista de todos los productos en la base de datos.
     * 
     * @return Una lista de objetos Producto.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public List<Producto> obtenerProductos() throws SQLException {
        ResultSet resultSet = null;
        List<Producto> listaProductos = new ArrayList<>();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM productos";
            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                Producto p = new Producto();
                p.setId(resultSet.getInt("id"));
                p.setNombre(resultSet.getString("nombre"));
                p.setCantidad(resultSet.getDouble("cantidad"));
                p.setPrecio(resultSet.getDouble("precio"));
                p.setFechaCrear(resultSet.getTimestamp("fecha_crear"));
                p.setFechaActualizar(resultSet.getTimestamp("fecha_actualizar"));
                listaProductos.add(p);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return listaProductos;
    }

    /**
     * Obtiene un producto específico de la base de datos.
     * 
     * @param idProducto El ID del producto a obtener.
     * @return El objeto Producto correspondiente, o un objeto vacío si no se encuentra.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public Producto obtenerProducto(int idProducto) throws SQLException {
        ResultSet resultSet = null;
        Producto p = new Producto();

        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            sql = "SELECT * FROM productos WHERE id =?";
            statement = connection.prepareStatement(sql);
            statement.setInt(1, idProducto);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                p.setId(resultSet.getInt(1));
                p.setNombre(resultSet.getString(2));
                p.setCantidad(resultSet.getDouble(3));
                p.setPrecio(resultSet.getDouble(4));
                p.setFechaCrear(resultSet.getTimestamp(5)); // Cambiado a Timestamp
                p.setFechaActualizar(resultSet.getTimestamp(6)); // Cambiado a Timestamp
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }

        return p;
    }

    /**
     * Comprueba si un producto con el nombre especificado existe en la base de datos.
     * 
     * @param nombre El nombre del producto a comprobar.
     * @return true si el producto existe, false en caso contrario.
     * @throws SQLException Si ocurre un error al acceder a la base de datos.
     */
    public boolean existeProducto(String nombre) throws SQLException {
        String sql = "SELECT COUNT(*) FROM productos WHERE nombre = ?";
        try (Connection connection = obtenerConexion();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1) > 0;
                }
            }
        }
        return false;
    }

    /**
     * Obtiene una conexión a la base de datos desde el pool de conexiones.
     * 
     * @return Un objeto Connection para interactuar con la base de datos.
     * @throws SQLException Si ocurre un error al establecer la conexión.
     */
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
