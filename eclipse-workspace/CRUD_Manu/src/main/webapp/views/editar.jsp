<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Editar Producto</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            width: 50%;
            margin: 50px auto;
            background-color: #fff;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.1);
        }

        h1 {
            text-align: center;
            color: #333;
        }

        table {
            width: 100%;
            margin-top: 20px;
        }

        td {
            padding: 10px;
        }

        input[type="text"],
        input[type="number"] {
            width: 95%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"] {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
        }

        .error {
            color: red;
            text-align: center;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Editar Producto</h1>
        <form action="productos" method="post" onsubmit="return validarFormulario()">
            <c:set var="producto" value="${producto}"/>
            <input type="hidden" name="opcion" value="editar">
            <input type="hidden" name="id" value="${producto.id}">
            <table>
                <tr>
                    <td>Nombre:</td>
                    <td><input type="text" name="nombre" value="${producto.nombre}" required></td>
                </tr>
                <tr>
                    <td>Cantidad:</td>
                    <td><input type="number" name="cantidad" value="${producto.cantidad}" required min="1"></td>
                </tr>
                <tr>
                    <td>Precio:</td>
                    <td><input type="number" step="0.01" name="precio" value="${producto.precio}" required min="0.01"></td>
                </tr>
            </table>
            <input type="submit" value="Guardar">
        </form>
    </div>

    <script>
        function validarFormulario() {
            const nombre = document.querySelector('input[name="nombre"]').value.trim();
            const cantidad = document.querySelector('input[name="cantidad"]').value;
            const precio = document.querySelector('input[name="precio"]').value;

            if (nombre.length < 2) {
                alert("El nombre del producto debe tener al menos 2 caracteres.");
                return false;
            }

            if (isNaN(cantidad) || cantidad <= 0) {
                alert("La cantidad debe ser un número positivo.");
                return false;
            }

            if (isNaN(precio) || precio <= 0) {
                alert("El precio debe ser un número positivo.");
                return false;
            }

            return true;
        }
    </script>
</body>
</html>
