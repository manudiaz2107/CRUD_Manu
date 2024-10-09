package com.aprendec.model;

import java.sql.Timestamp; // Importar la clase Timestamp
import java.text.SimpleDateFormat;

public class Producto {

    private int id;
    private String nombre;
    private double cantidad;
    private double precio;
    private Timestamp fechaCrear; // Cambiado a Timestamp
    private Timestamp fechaActualizar; // Cambiado a Timestamp

    public Producto(int id, String nombre, double cantidad, double precio, Timestamp fechaCrear, Timestamp fechaActualizar) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaCrear = fechaCrear;
        this.fechaActualizar = fechaActualizar;
    }

    public Producto() {
        this.fechaCrear = new Timestamp(System.currentTimeMillis()); // Establecer la fecha actual
        this.fechaActualizar = null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getCantidad() {
        return cantidad;
    }

    public void setCantidad(double cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Timestamp getFechaCrear() {
        return fechaCrear;
    }

    public void setFechaCrear(Timestamp fechaCrear) {
        this.fechaCrear = fechaCrear;
    }

    public Timestamp getFechaActualizar() {
        return fechaActualizar;
    }

    public void setFechaActualizar(Timestamp fechaActualizar) {
        this.fechaActualizar = fechaActualizar;
    }

    public String getFechaCrearFormateada() {
        if (fechaCrear != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy 'a las' HH:mm:ss");
            return sdf.format(fechaCrear);
        } else {
            return "Fecha no disponible";
        }
    }

    public String getFechaActualizarFormateada() {
        if (fechaActualizar != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy 'a las' HH:mm:ss");
            return sdf.format(fechaActualizar);
        } else {
            return "Fecha no disponible";
        }
    }

    @Override
    public String toString() {
        return "Producto [id=" + id + ", nombre=" + nombre + ", cantidad=" + cantidad + ", precio=" + precio
                + ", fechaCrear=" + fechaCrear + ", fechaActualizar=" + fechaActualizar + "]";
    }
}
