package com.MiTiendita.servicio;

import com.MiTiendita.modelo.Producto;

import java.util.ArrayList;
import java.util.List;

/**
 * Gestiona la lógica de negocio del inventario.
 * Contiene la lista de productos y los métodos para interactuar con ella.
 */
public class GestionarInventario {

    //Atributo
    private List<Producto> inventario;

    //Constructor
    public GestionarInventario(){
        this.inventario = new ArrayList<>();
    }

    //Métodos
    /**
     * Ingreso de productos: Crea un nuevo producto y lo añade a la lista "inventario".
     */
    public void agregarProducto(String nombre, double precio, int stock) {
        Producto nuevoProducto = new Producto(nombre, precio, stock);
        this.inventario.add(nuevoProducto);

        System.out.println("✅ Producto'" + nombre + "' agregado exitosamente al inventario.");
    }

    /**
     * Visualización de productos: Muestra todos los productos registrados
     */
    public void mostrarProductos(){
        if (inventario.isEmpty()){
            System.out.println("⚠️ El inventario está vacio. No hay productos que mostrar.");
        }

        System.out.println("\n ------ LISTA DE PRODUCTOS EN INVENTARIO ------");

        for (Producto producto : inventario) {
            System.out.println(producto);
        }
    }

    /**
     * Búsqueda por ID: Busca un producto por su ID.
     */
    public Producto buscarProductoPorId(int id) {
        for (Producto producto : inventario) {
            if (producto.getId() == id) {
                return producto;
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }

    /**
     * Búsqueda por Nombre: Busca un producto por su nombre (ignorando mayúsculas/minúsculas).
     */
    public Producto buscarProductoPorNombre(String nombre) {
        for (Producto producto : inventario) {
            if (producto.getNombreProducto().equalsIgnoreCase(nombre)) {
                return producto;
            }
        }
        return null; // Retorna null si no se encuentra el producto
    }

    /**
     * Actualización de Stock (con validación).
     */
    public void actualizarStockProducto(int id, int nuevoStock) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            if (nuevoStock >= 0) {
                producto.setStock(nuevoStock);
                System.out.println("✅ Stock del producto ID " + id + " actualizado a " + nuevoStock + ".");
            } else {
                System.out.println("❌ Error: El stock no puede ser negativo.");
            }
        } else {
            System.out.println("❌ Error: Producto con ID " + id + " no encontrado.");
        }
    }

    /**
     * Actualización de Precio (con validación).
     */
    public void actualizarPrecioProducto(int id, double nuevoPrecio){
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            if (nuevoPrecio >= 0) {
                producto.setPrecio(nuevoPrecio);
                System.out.println("✅ Precio del producto ID " + id + " actualizado a " + String.format("%.2f", nuevoPrecio) + ".");
            } else {
                System.out.println("❌ Error: El precio no puede ser negativo.");
            }
        } else {
            System.out.println("❌ Error: Producto con ID " + id + " no encontrado.");
        }
    }

    /**
     * Eliminación de productos.
     */

    public void eliminarProducto(int id) {
        Producto producto = buscarProductoPorId(id);
        if (producto != null) {
            System.out.println("ℹ️ Eliminando producto: " + producto.getNombreProducto() + "...");
            inventario.remove(producto);
            System.out.println("✅ Producto con ID " + id + " eliminado del inventario.");
        } else {
            System.out.println("❌ Error: Producto con ID " + id + " no encontrado.");
        }
    }




}
