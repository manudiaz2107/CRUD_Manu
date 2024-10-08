package com.aprendec.model;

import java.util.Date;
import java.text.SimpleDateFormat;

public class Producto {

    private int id;
    private String nombre;
    private double cantidad;
    private double precio;
    private Date fechaCrear;
    private Date fechaActualizar;

    public Producto(int id, String nombre, double cantidad, double precio, Date fechaCrear, Date fechaActualizar) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaCrear = fechaCrear;
        this.fechaActualizar = fechaActualizar;
    }

    public Producto() {
        this.fechaCrear = new Date(); // Fecha de creación establecida automáticamente
        this.fechaActualizar = null; // Fecha de actualización puede ser nula inicialmente
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

    public Date getFechaCrear() {
        return fechaCrear;
    }

    public void setFechaCrear(Date fechaCrear) {
        this.fechaCrear = fechaCrear;
    }

    public Date getFechaActualizar() {
        return fechaActualizar;
    }

    public void setFechaActualizar(Date fechaActualizar) {
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
