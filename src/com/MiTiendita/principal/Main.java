package com.MiTiendita.principal;

import com.MiTiendita.modelo.Producto;
import com.MiTiendita.servicio.GestionarInventario;

/**
 * Clase principal para ejecutar la aplicación.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Bienvenido a Mi Tiendita!");
        System.out.println("\n ------ INICIANDO SISTEMA DE GESTIÓN DE PEDIDOS ------");

        GestionarInventario gestor = new GestionarInventario();

        gestor.mostrarProductos();

        gestor.agregarProducto("Café Premiun", 7.50, 100);
        gestor.agregarProducto("Té Verde Orgánico", 5.25, 80);
        gestor.agregarProducto("Chocolate Amargo 70%", 4.99, 50);

        gestor.mostrarProductos();


        System.out.println("\n ------ BÚSQUEDA DE PRODUCTO POR ID ------");
        Producto productoEncontrado = gestor.buscarProductoPorId(2);

        if(productoEncontrado != null) {
            System.out.println("✅ Producto encontrado: " + productoEncontrado);
        } else {
            System.out.println("❌ Producto con ID 2 no encontrado.");
        }


        System.out.println("\n ------ BÚSQUEDA DE PRODUCTO 'Café Premiun' ------ ");
        Producto productoEncontrado2 = gestor.buscarProductoPorNombre("Café Premiun");
        if(productoEncontrado2 != null) {
            System.out.println("✅ Producto encontrado: " + productoEncontrado2);
        } else {
            System.out.println("❌ Producto 'Café Premiun' no encontrado.");
        }


        System.out.println("\n ------ BÚSQUEDA DE PRODUCTO POR ID (CASO FALLIDO ID=99) ------ ");
        Producto productEncontrado3 = gestor.buscarProductoPorId(99);
        if(productEncontrado3 != null) {
            System.out.println("✅ Producto encontrado: " + productEncontrado3);
        } else {
            System.out.println("❌ Producto con ID 99 no encontrado.");
        }

        System.out.println("\n ------ ACTUALIZACIÓN DEL STOCK ------ ");
        gestor.actualizarStockProducto(1, 150);


        System.out.println("\n ------ ACTUALIZACIÓN DEL PRECIO ------ ");
        gestor.actualizarPrecioProducto(2, 6.50);


        System.out.println("\n ------ ELIMINACIÖN DE PRODUCTO ------ ");
        gestor.eliminarProducto(3);


        System.out.println("\n--- ESTADO FINAL DEL INVENTARIO ---");
        gestor.mostrarProductos();

    }

}
