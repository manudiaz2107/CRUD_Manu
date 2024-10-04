<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Productos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }

        h1 {
            color: #333;
            text-align: center;
        }

        .btn-crear {
            display: block;
            width: 200px;
            margin: 20px auto;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
        }

        .btn-crear:hover {
            background-color: #45a049;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        tr:hover {
            background-color: #f1f1f1;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
        }

        a:hover {
            text-decoration: underline;
        }

        .action {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Listar Productos</h1>
	<a href="productos?opcion=crear" class="btn-crear">Crear Nuevo Producto</a>
    <table>
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Fecha Creación</th>
            <th>Fecha Actualización</th>
            <th class="action">Acción</th>
        </tr>
        <c:forEach var="producto" items="${lista}">
            <tr>
                <td><a href="productos?opcion=meditar&id=${producto.id}"><c:out value="${producto.id}"></c:out></a></td>
                <td><c:out value="${producto.nombre}"></c:out></td>
                <td><c:out value="${producto.cantidad}"></c:out></td>
                <td><c:out value="${producto.precio}"></c:out></td>
                <td><c:out value="${producto.fechaCrear}"></c:out></td>
                <td><c:out value="${producto.fechaActualizar}"></c:out></td>
                <td class="action">
                    <a href="productos?opcion=eliminar&id=${producto.id}">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
