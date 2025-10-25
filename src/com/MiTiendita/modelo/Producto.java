package com.MiTiendita.modelo;

/**
 * Representa un producto en el inventario.
 * Utiliza encapsulamiento para proteger los datos.
 */
public class Producto {
    //Un contador para generar IDs únicos
    private static int contadorId = 0;

    //Atributos
    private int id;
    private String nombreProducto;
    private double precio;
    private int stock;

    //Constructor
    public Producto(String nombreProducto, double precio, int stock) {
        this.id = ++contadorId; //Genera un ID único
        this.nombreProducto = nombreProducto;
        this.precio = precio;
        this.stock = stock;
    }

    //Getters (para obtener datos)
    //Setters (para modificar datos)


    public int getId() {
        return id;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    @Override
    public String toString() {
        return "[ID=" + id +
                ", Nombre=" + nombreProducto +
                ", Precio=" + String.format("%.2f", precio) +       // Formatea el precio a 2 decimales
                ", Stock=" + stock + "]";
    }
}
