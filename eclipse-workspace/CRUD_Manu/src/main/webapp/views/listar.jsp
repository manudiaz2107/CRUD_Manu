<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Lista de Productos</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f2f2f2;
        }

        .container {
            width: 80%;
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

        .message {
            padding: 15px;
            margin-bottom: 20px;
            border: 1px solid transparent;
            border-radius: 4px;
        }

        .message.success {
            color: #155724;
            background-color: #d4edda;
            border-color: #c3e6cb;
        }

        .message.error {
            color: #721c24;
            background-color: #f8d7da;
            border-color: #f5c6cb;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        table, th, td {
            border: 1px solid #ddd;
        }

        th, td {
            padding: 12px;
            text-align: left;
        }

        th {
            background-color: #4CAF50;
            color: white;
        }

        a {
            color: #4CAF50;
            text-decoration: none;
        }

        .action a {
            color: red;
            font-weight: bold;
        }

        .add-product-btn {
            margin-top: 20px;
            text-align: center;
            background-color: #4CAF50;
            color: white;
            padding: 10px;
            border-radius: 5px;
            cursor: pointer;
        }

        .add-product-btn:hover {
            background-color: #45a049;
        }

        .form-container {
            display: none;
            margin-top: 20px;
            padding: 20px;
            background-color: #f9f9f9;
            border: 1px solid #ccc;
            border-radius: 10px;
        }

        input[type="text"], input[type="number"] {
            width: 95%;
            padding: 8px;
            margin-top: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        input[type="submit"], .btn-volver {
            width: 100%;
            padding: 10px;
            margin-top: 20px;
            background-color: #4CAF50;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        input[type="submit"]:hover, .btn-volver:hover {
            background-color: #45a049;
        }

        .btn-volver {
            background-color: #007BFF;
        }

        .btn-volver:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1>Lista de Productos</h1>
        
        <!-- Mostrar mensaje de éxito o error -->
        <c:if test="${not empty mensaje}">
            <div class="message success">${mensaje}</div>
        </c:if>

        <c:if test="${not empty error}">
            <div class="message error">${error}</div>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Nombre</th>
                    <th>Cantidad</th>
                    <th>Precio</th>
                    <th>Fecha de Creación</th>
                    <th>Fecha de Actualización</th>
                    <th>Acción</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="producto" items="${lista}">
                    <tr>
                        <td>${producto.id}</td>
                        <td>${producto.nombre}</td>
                        <td>${producto.cantidad}</td>
                        <td>${producto.precio}</td>
                        <td>${producto.fechaCrearFormateada}</td>
                        <td>${producto.fechaActualizarFormateada}</td>
                        <td class="action">
                            <button><a href="productos?opcion=editar&id=${producto.id}">Editar</a></button>
                            <button><a href="productos?opcion=eliminar&id=${producto.id}" onclick="return confirm('¿Estás seguro de eliminar este producto?')">Eliminar</a></button>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>

        <div class="add-product-btn" onclick="mostrarFormulario()">Añadir nuevo producto</div>

        <div class="form-container" id="form-container">
            <h2>Crear Nuevo Producto</h2>
            <form action="productos" method="post" onsubmit="return validarFormulario()">
                <input type="hidden" name="opcion" value="guardar">
                <table>
                    <tr>
                        <td>Nombre:</td>
                        <td><input type="text" name="nombre" required></td>
                    </tr>
                    <tr>
                        <td>Cantidad:</td>
                        <td><input type="number" name="cantidad" required min="1"></td>
                    </tr>
                    <tr>
                        <td>Precio:</td>
                        <td><input type="number" step="0.01" name="precio" required min="0.01"></td>
                    </tr>
                </table>
                <input type="submit" value="Guardar">
            </form>
        </div>

        <button class="btn-volver" onclick="window.location.href='index.jsp'">Volver al Índice</button>
    </div>

    <script>
        function mostrarFormulario() {
            const formContainer = document.getElementById("form-container");
            if (formContainer.style.display === "none" || formContainer.style.display === "") {
                formContainer.style.display = "block";
            } else {
                formContainer.style.display = "none";
            }
        }

        function validarFormulario() {
            const cantidad = document.querySelector('input[name="cantidad"]').value;
            const precio = document.querySelector('input[name="precio"]').value;

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
