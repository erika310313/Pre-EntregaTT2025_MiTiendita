package com.MiTiendita.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa un Pedido, que contiene una lista de productos (LineaPedido) y calcula el costo total.
 */
public class Pedido {
    private static int contadorId = 0;

    private int id;
    private List<LineaPedido> LineasPedidos;

    public Pedido(){
        this.id = ++contadorId;
        this.LineasPedidos = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public List<LineaPedido> getLineasPedidos() {
        return LineasPedidos;
    }

    /**
     * Agrega una nueva línea de producto al pedido.
     */
    public double getTotal(){
        double total = 0.0;
        for (LineaPedido linea : LineasPedidos){
            total += linea.getSubtotal();
        }
        return total;
    }

    public void agregarLinea(LineaPedido nuevaLinea) {
        this.LineasPedidos.add(nuevaLinea);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Pedido ID: ").append(id).append("\n");

        if(getLineasPedidos().isEmpty()){
            sb.append("⚠️ No hay productos en este pedido.\n");
        }else{
            for(LineaPedido linea : getLineasPedidos()) {
                sb.append(linea).append("\n");
            }
        }
        sb.append("--------------------------------\n");
        sb.append("Total del Pedido: $").append(String.format("%.2f", getTotal())).append("\n");
        return sb.toString();
    }

}
