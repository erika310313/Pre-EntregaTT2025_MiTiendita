package com.MiTiendita.servicio;

import com.MiTiendita.modelo.LineaPedido;
import com.MiTiendita.modelo.Pedido;
import com.MiTiendita.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la lógica de negocio de los Pedidos.
 * Colabora con GestionInventario para validar stock.
 */
public class GestionPedidos {

    private List<Pedido> pedidosRealizados;
    private GestionarInventario servicioDeInventario;

    public GestionPedidos(GestionarInventario servicioDeInventario) {
        this.servicioDeInventario = servicioDeInventario;
        this.pedidosRealizados = new ArrayList<>();
    }

    // ------ MÉtodos ------ //

    /**
     * Inicia la creación de un nuevo pedido vacío.
     */

    public Pedido iniciarPedido(){
        return new Pedido();
    }

    /**
     * Solicitar productos y validar stock. Intenta agregar un producto a un pedido.
     */

    public boolean agregarLineaAlPedido(Pedido pedido, int idProducto, int cantidadSolicitada){
        Producto producto = servicioDeInventario.buscarProductoPorId(idProducto);

        if(producto == null){
            System.out.println("❌ El producto con ID " + idProducto + " no existe.");
            return false;
        }

        if(producto.getStock() < cantidadSolicitada){
            System.out.println("❌ No hay suficiente stock para el producto '" + producto.getNombreProducto() + "'. Stock disponible: " + producto.getStock());
            return false;
        }

        // Si hay stock suficiente, se agrega la línea al pedido
        LineaPedido nuevaLinea = new LineaPedido(producto, cantidadSolicitada);
        pedido.agregarLinea(nuevaLinea);
        System.out.println("✅ Se agregó " + cantidadSolicitada + " unidad(es) del producto '" + producto.getNombreProducto() + "' al pedido.");

        // Y se reduce el stock en el inventario
        producto.setStock(producto.getStock() - cantidadSolicitada);

        return true;
    }

    /**
     * Disminuir el stock y guardar el pedido.
     */
    public void confirmarPedido(Pedido pedido){
        if(pedido.getLineasPedidos().isEmpty()){
            System.out.println("❌ No se puede confirmar un pedido vacío.");
        }

        System.out.println("Confirmando Pedido ID: " + pedido.getId() + ". Actualizando stock... ");
        for (LineaPedido linea : pedido.getLineasPedidos()){
            Producto producto = linea.getProducto();
            int cantidadPedido = linea.getCantidad();

            int nuevoStock = producto.getStock() - cantidadPedido;
            servicioDeInventario.actualizarStockProducto(producto.getId(), nuevoStock);

            System.out.println(" - Producto: " + producto.getNombreProducto() +
                    ", Cantidad Vendida: " + linea.getCantidad() +
                    ", Stock Restante: " + producto.getStock());
        }

        this.pedidosRealizados.add(pedido);
        System.out.println("✅ Pedido ID: " + pedido.getId() + " confirmado y guardado exitosamente.");
        System.out.println(pedido);
    }




}
