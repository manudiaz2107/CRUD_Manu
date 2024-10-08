package com.aprendec.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.aprendec.dao.ProductoDAO;
import com.aprendec.model.Producto;

@WebServlet(description = "administra peticiones para la tabla productos", urlPatterns = { "/productos" })
public class ProductoController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ProductoController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        ProductoDAO productoDAO = new ProductoDAO();
        RequestDispatcher requestDispatcher;

        try {
            if (opcion.equals("crear")) {
                requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
                requestDispatcher.forward(request, response);
            } else if (opcion.equals("listar")) {
                List<Producto> lista = productoDAO.obtenerProductos();
                request.setAttribute("lista", lista);
                requestDispatcher = request.getRequestDispatcher("/views/listar.jsp");
                requestDispatcher.forward(request, response);
            } else if (opcion.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Producto producto = productoDAO.obtenerProducto(id);
                if (producto != null) {
                    request.setAttribute("producto", producto);
                    requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    response.sendRedirect("productos?opcion=listar");
                }
            } else if (opcion.equals("eliminar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                productoDAO.eliminar(id);
                response.sendRedirect("productos?opcion=listar");
            }
        } catch (SQLException | NumberFormatException e) {
            e.printStackTrace();
            response.sendRedirect("productos?opcion=listar");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String opcion = request.getParameter("opcion");
        ProductoDAO productoDAO = new ProductoDAO();
        Producto producto = new Producto();
        Date fechaActual = new Date();

        try {
            if (opcion.equals("guardar")) {
                producto.setNombre(request.getParameter("nombre"));
                producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
                producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                producto.setFechaCrear(new java.sql.Date(fechaActual.getTime()));

                if (productoDAO.existeProducto(producto.getNombre())) {
                    request.setAttribute("error", "El producto ya existe.");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/crear.jsp");
                    requestDispatcher.forward(request, response);
                    return;
                }

                productoDAO.guardar(producto);
                response.sendRedirect("productos?opcion=listar");

            } else if (opcion.equals("editar")) {
                producto.setId(Integer.parseInt(request.getParameter("id")));
                producto.setNombre(request.getParameter("nombre"));
                producto.setCantidad(Double.parseDouble(request.getParameter("cantidad")));
                producto.setPrecio(Double.parseDouble(request.getParameter("precio")));
                producto.setFechaActualizar(new java.sql.Date(fechaActual.getTime()));

                Producto productoExistente = productoDAO.obtenerProducto(producto.getId());

                if (productoDAO.existeProducto(producto.getNombre()) && 
                    !productoExistente.getNombre().equals(producto.getNombre())) {
                    request.setAttribute("error", "El nombre del producto ya existe.");
                    request.setAttribute("producto", producto);
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/views/editar.jsp");
                    requestDispatcher.forward(request, response);
                    return;
                }

                productoDAO.editar(producto);
                response.sendRedirect("productos?opcion=listar");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Error en la base de datos.");
        }
    }
}
