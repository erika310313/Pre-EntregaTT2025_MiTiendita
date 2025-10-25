package com.MiTiendita.modelo;

/**
 * Representa una línea de ítem dentro de un Pedido. Almacena el producto y la cantidad deseada.
 */
public class LineaPedido {

    private Producto producto;
    private int cantidad;

    public LineaPedido(Producto producto, int cantidad){
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /**
     * Calcula el subtotal para la línea de pedido.
     */
    public double getSubtotal() {
        return producto.getPrecio() * cantidad;
    }

    @Override
    public String toString() {
        return " > Producto: " + producto.getNombreProducto() +
                " (Cant.: " + cantidad +
                ", Precio Unit.: $" + String.format("%.2f", producto.getPrecio()) +
                ", Subtotal: $" + String.format("%.2f", getSubtotal()) +
                ")";
    }
}
