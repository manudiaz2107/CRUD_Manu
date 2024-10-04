<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar Producto</title>
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

        form {
            max-width: 600px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            border-radius: 5px;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        th, td {
            border: 1px solid #ccc;
            padding: 8px;
            text-align: left;
        }

        th {
            background-color: #f2f2f2;
        }

        input[type="text"],
        input[type="number"] {
            width: calc(100% - 20px);
            padding: 10px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
            transition: background-color 0.3s;
            width: 100%;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .btn-cancelar {
            display: block;
            margin: 20px auto;
            padding: 10px;
            background-color: #f44336;
            color: white;
            text-align: center;
            text-decoration: none;
            border-radius: 5px;
            transition: background-color 0.3s;
            width: 200px;
        }

        .btn-cancelar:hover {
            background-color: #e53935;
        }
    </style>
</head>
<body>
    <h1>Editar Producto</h1>
    <form action="productos" method="post">
        <c:set var="producto" value="${producto}"/>
        <input type="hidden" name="opcion" value="editar">
        <input type="hidden" name="id" value="${producto.id}">
        <table>
            <tr>
                <td>Nombre:</td>
                <td><input type="text" name="nombre" size="50" value="${producto.nombre}" required></td>
            </tr>
            <tr>
                <td>Cantidad:</td>
                <td><input type="number" name="cantidad" size="50" value="${producto.cantidad}" required></td>
            </tr>
            <tr>
                <td>Precio:</td>
                <td><input type="number" step="0.01" name="precio" size="50" value="${producto.precio}" required></td>
            </tr>
        </table>
        <input type="submit" value="Guardar">
    </form>
    <a href="productos?opcion=listar" class="btn-cancelar">Cancelar</a>
</body>
</html>
