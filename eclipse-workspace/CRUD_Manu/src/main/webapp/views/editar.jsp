<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
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

.error {
	color: #d8000c; /* Color rojo para el texto */
	background-color: #ffbaba; /* Fondo suave */
	border: 1px solid #d8000c; /* Borde rojo */
	border-radius: 5px; /* Bordes redondeados */
	padding: 10px; /* Espaciado interno */
	margin: 20px 0; /* Margen arriba y abajo */
	text-align: center; /* Centrar texto */
	font-weight: bold; /* Texto en negrita */
}

table {
	width: 100%;
	margin-top: 20px;
}

td {
	padding: 10px;
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
	background-color: #007BFF; /* Color azul para el botón volver */
}

.btn-volver:hover {
	background-color: #0056b3; /* Color azul más oscuro al pasar el ratón */
}
</style>
</head>
<body>
	<div class="container">
		<h1>Editar Producto</h1>
		
		<% if (request.getAttribute("error") != null) { %>
			<div class="error"><%= request.getAttribute("error") %></div>
		<% } %>
		
		<form action="productos" method="post" onsubmit="return validarFormulario()">
			<c:set var="producto" value="${producto}" />
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
		<button class="btn-volver" onclick="window.location.href='index.jsp'">Volver a Listar</button>
	</div>

	<script>
        function validarFormulario() {
            const nombre = document.querySelector('input[name="nombre"]');
            const cantidad = document.querySelector('input[name="cantidad"]');
            const precio = document.querySelector('input[name="precio"]');
            let isValid = true;

            // Limpiar mensajes de error anteriores
            document.querySelectorAll('.error-message').forEach(msg => msg.remove());

            if (nombre.value.trim().length < 2) {
                mostrarMensajeError(nombre, "El nombre del producto debe tener al menos 2 caracteres.");
                isValid = false;
            }

            if (isNaN(cantidad.value) || cantidad.value <= 0) {
                mostrarMensajeError(cantidad, "La cantidad debe ser un número positivo.");
                isValid = false;
            }

            if (isNaN(precio.value) || precio.value <= 0) {
                mostrarMensajeError(precio, "El precio debe ser un número positivo.");
                isValid = false;
            }

            return isValid;
        }

        function mostrarMensajeError(input, mensaje) {
            const errorMsg = document.createElement('div');
            errorMsg.className = 'error-message';
            errorMsg.style.color = 'red';
            errorMsg.innerText = mensaje;
            input.parentNode.insertBefore(errorMsg, input.nextSibling);
        }
    </script>
</body>
</html>
