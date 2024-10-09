package com.aprendec.model;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

/**
 * Clase Producto que representa un producto en el sistema.
 */
public class Producto {

    private int id;
    private String nombre;
    private double cantidad;
    private double precio;
    private Timestamp fechaCrear;
    private Timestamp fechaActualizar;

    /**
     * Constructor que inicializa un objeto Producto con todos sus atributos.
     * 
     * @param id El ID del producto.
     * @param nombre El nombre del producto.
     * @param cantidad La cantidad del producto.
     * @param precio El precio del producto.
     * @param fechaCrear La fecha de creación del producto.
     * @param fechaActualizar La fecha de actualización del producto.
     */
    public Producto(int id, String nombre, double cantidad, double precio, Timestamp fechaCrear, Timestamp fechaActualizar) {
        this.id = id;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.precio = precio;
        this.fechaCrear = fechaCrear;
        this.fechaActualizar = fechaActualizar;
    }

    /**
     * Constructor vacío que inicializa la fecha de creación al momento actual.
     */
    public Producto() {
        this.fechaCrear = new Timestamp(System.currentTimeMillis());
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

    /**
     * Obtiene la fecha de creación formateada como un String.
     * 
     * @return La fecha de creación en formato legible.
     */
    public String getFechaCrearFormateada() {
        if (fechaCrear != null) {
            SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy 'a las' HH:mm:ss");
            return sdf.format(fechaCrear);
        } else {
            return "Fecha no disponible";
        }
    }

    /**
     * Obtiene la fecha de actualización formateada como un String.
     * 
     * @return La fecha de actualización en formato legible.
     */
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
