package com.aprendec.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp; // Importar la clase Timestamp
import java.util.ArrayList;
import java.util.List;

import com.aprendec.conexion.Conexion;
import com.aprendec.model.Producto;

public class ProductoDAO {
    private Connection connection;
    private PreparedStatement statement;
    private boolean estadoOperacion;

    // guardar producto
    public boolean guardar(Producto producto) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "INSERT INTO productos (nombre, cantidad, precio, fecha_crear, fecha_actualizar) VALUES(?,?,?,?,?)";
            statement = connection.prepareStatement(sql);

            if (producto.getFechaActualizar() == null) {
                producto.setFechaActualizar(new Timestamp(System.currentTimeMillis())); // Usar Timestamp
            }

            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getCantidad());
            statement.setDouble(3, producto.getPrecio());
            statement.setTimestamp(4, producto.getFechaCrear()); // Cambiado a Timestamp
            statement.setTimestamp(5, producto.getFechaActualizar()); // Cambiado a Timestamp

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

    // editar producto
    public boolean editar(Producto producto) throws SQLException {
        String sql = null;
        estadoOperacion = false;
        connection = obtenerConexion();

        try {
            connection.setAutoCommit(false);
            sql = "UPDATE productos SET nombre=?, cantidad=?, precio=?, fecha_actualizar=? WHERE id=?";
            statement = connection.prepareStatement(sql);

            if (producto.getFechaActualizar() == null) {
                producto.setFechaActualizar(new Timestamp(System.currentTimeMillis())); // Usar Timestamp
            }

            statement.setString(1, producto.getNombre());
            statement.setDouble(2, producto.getCantidad());
            statement.setDouble(3, producto.getPrecio());
            statement.setTimestamp(4, producto.getFechaActualizar()); // Cambiado a Timestamp
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

    // eliminar producto
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

    // obtener lista de productos
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
                p.setFechaCrear(resultSet.getTimestamp("fecha_crear")); // Cambiado a Timestamp
                p.setFechaActualizar(resultSet.getTimestamp("fecha_actualizar")); // Cambiado a Timestamp
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

    // obtener producto
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

    // comprobar si existe producto
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

    // obtener conexion pool
    private Connection obtenerConexion() throws SQLException {
        return Conexion.getConnection();
    }
}
