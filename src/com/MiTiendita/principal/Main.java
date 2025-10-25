package com.MiTiendita.principal;
import com.MiTiendita.modelo.Pedido;
import com.MiTiendita.modelo.Producto;
import com.MiTiendita.servicio.GestionPedidos;
import com.MiTiendita.servicio.GestionarInventario;

import java.util.Scanner;

/**
 * Clase principal para ejecutar la aplicación.
 */
public class Main {

    //Hacemos a los gestores y scanner atributos de la clase para que todos los métodos puedan usarlos
    private GestionarInventario gestorInventario;
    private GestionPedidos gestorPedidos;
    private Scanner scanner;

    // Constructor
    public Main() {
        this.gestorInventario = new GestionarInventario();
        this.gestorPedidos = new GestionPedidos(this.gestorInventario);
        this.scanner = new Scanner(System.in);

        //Cargamos datos de ejemplo para que la app no inicie vacía
        cargarDatosIniciales();
    }

    public static void main(String[] args) {

        Main app = new Main();
        app.ejecutar();

    }

    /**
     * Método principal que ejecuta la app.
     */
    public void ejecutar() {
        boolean corriendo = true;

        while (corriendo) {
            mostrarMenu();
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    System.out.print("Ingrese el nombre del producto: ");
                    String nombre = scanner.nextLine();
                    System.out.print("Ingrese su precio: ");
                    double precio = Double.parseDouble(scanner.nextLine());
                    System.out.print("Ingrese el stock: ");
                    int stock = Integer.parseInt(scanner.nextLine());
                    gestorInventario.agregarProducto(nombre, precio, stock);
                    break;
                case "2":
                    gestorInventario.mostrarProductos();
                    break;
                case "3":
                    System.out.println("Ingrese el ID del producto a buscar: ");
                    gestorInventario.buscarProductoPorId(Integer.parseInt(scanner.nextLine()));
                    break;
                case "4":
                    System.out.println("Ingrese el nombre del producto a buscar: ");
                    gestorInventario.buscarProductoPorNombre(scanner.nextLine());
                    break;
                case "5":
                    System.out.println("Ingrese el ID del producto a eliminar: ");
                    gestorInventario.eliminarProducto(Integer.parseInt(scanner.nextLine()));
                    break;
                case "6":
                    Pedido pedido = gestorPedidos.iniciarPedido();
                    System.out.print("Ingrese el ID del producto a agregar al pedido: ");
                    int productoId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Ingrese la cantidad solicitada: ");
                    int cantidad = Integer.parseInt(scanner.nextLine());
                    gestorPedidos.agregarLineaAlPedido(pedido, productoId, cantidad);
                    gestorPedidos.confirmarPedido(pedido);
                    break;
                case "7":
                    gestorPedidos.mostrarPedidosRealizados();
                    break;
                case "8":
                    corriendo = false;
                    System.out.println("Gracias por usar el sistema. ¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intenta de nuevo.");
            }

            if (corriendo) {
                presioneEnterParaContinuar();
            }

        }
        scanner.close();

    }

    public void mostrarMenu() {
        System.out.println("\n========================================");
        System.out.println("     SISTEMA DE GESTIÓN - TECHLAB");
        System.out.println("==========================================");
        System.out.println("1) Agregar Producto");
        System.out.println("2) Mostrar Productos");
        System.out.println("3) Buscar Productos por ID");
        System.out.println("4) Buscar Productos por Nombre");
        System.out.println("5) Eliminar Producto");
        System.out.println("6) Crear un Pedido");
        System.out.println("7) Mostrar Pedidos Realizados");
        System.out.println("8) Salir del Sistema");
        System.out.print("\nElija una opción: ");
    }

    private void cargarDatosIniciales() {
        System.out.println("Cargando datos iniciales...");
        gestorInventario.agregarProducto("Café Premium", 7.50, 100);
        gestorInventario.agregarProducto("Té Verde Orgánico", 5.25, 80);
        gestorInventario.agregarProducto("Chocolate Amargo 70%", 4.99, 50);
    }

    private void presioneEnterParaContinuar() {
        System.out.println("\nPresione [Enter] para continuar...");
        scanner.nextLine();
    }


}






