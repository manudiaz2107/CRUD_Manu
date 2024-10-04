<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Menú de Opciones</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
            text-align: center;
        }

        h1 {
            color: #333;
        }

        table {
            margin: 0 auto;
            border-collapse: collapse;
            margin-top: 20px;
        }

        td {
            border: 1px solid #ccc;
            padding: 10px;
            text-align: center;
        }

        a {
            text-decoration: none;
            color: #4CAF50;
            font-weight: bold;
            display: block;
            padding: 10px;
            border-radius: 5px;
            background-color: #fff;
            transition: background-color 0.3s, color 0.3s;
        }

        a:hover {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
    <h1>Menú de Opciones Productos</h1>
    <table>
        <tr>
            <td><a href="productos?opcion=crear">Crear un Producto</a></td>
        </tr>
        <tr>
            <td><a href="productos?opcion=listar">Listar Productos</a></td>
        </tr>
    </table>
</body>
</html>
