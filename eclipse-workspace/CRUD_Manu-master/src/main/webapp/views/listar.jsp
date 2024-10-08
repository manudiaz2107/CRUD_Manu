<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Listar Productos</title>
</head>
<body>
    <h1>Listar Productos</h1>
    <table border="1">
        <tr>
            <th>Id</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Precio</th>
            <th>Fecha Creacion</th>
            <th>Fecha Actualizacion</th>
            <th>Accion</th>
        </tr>
        <c:forEach var="producto" items="${lista}">
            <tr>
                <td>
                    <a href="productos?opcion=meditar&id=${producto.id}">
                        <c:out value="${producto.id}"></c:out>
                    </a>
                </td>
                <td><c:out value="${producto.nombre}"></c:out></td>
                <td><c:out value="${producto.cantidad}"></c:out></td>
                <td><c:out value="${producto.precio}"></c:out></td>
                <td><c:out value="${producto.fechaCrear}"></c:out></td>
                <td><c:out value="${producto.fechaActualizar}"></c:out></td>
                <td>
                    <a href="productos?opcion=eliminar&id=${producto.id}">
                        Eliminar 
                    </a>
                </td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
