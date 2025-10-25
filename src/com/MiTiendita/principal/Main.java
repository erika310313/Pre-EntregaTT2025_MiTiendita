package com.MiTiendita.principal;

import com.MiTiendita.excepciones.StockInsuficienteException;
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
                    gestionarAgregarProducto();
                    break;
                case "2":
                    gestorInventario.mostrarProductos();
                    break;
                case "3":
                    gestionarBuscarActualizar();
                    break;
                case "4":
                    gestionarEliminarProducto();
                    break;
                case "5":
                    gestionarCrearPedido();
                    break;
                case "6":
                    gestorPedidos.mostrarPedidosRealizados();
                    break;
                case "7":
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
        System.out.println("3) Buscar o Actualizar Producto");
        System.out.println("4) Eliminar Producto");
        System.out.println("5) Crear un Pedido");
        System.out.println("6) Mostrar Pedidos Realizados");
        System.out.println("7) Salir del Sistema");
        System.out.print("\nElija una opción: ");
    }

    //Métodos "gestores" para cada opción del menú

    private void gestionarAgregarProducto() {
        try {
            System.out.println("n ------ 1. AGREGAR PRODUCTO ------");
            System.out.print("Ingrese el nombre del producto: ");
            String nombre = scanner.nextLine();
            System.out.print("Ingrese el precio del producto (ej. 7.50): ");
            double precio = Double.parseDouble(scanner.nextLine());
            System.out.print("Ingrese la cantidad en stock: ");
            int stock = Integer.parseInt(scanner.nextLine());

            gestorInventario.agregarProducto(nombre, precio, stock);
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Asegúrese de ingresar números válidos para precio y stock. Operación Cancelada");
        }
    }

    private void gestionarBuscarActualizar() {
        System.out.println("\n ------ 3. BUSCAR O ACTUALIZAR PRODUCTO ------");
        System.out.println("\n    ¿Cómo desea buscar el producto?");
        System.out.println("      1) Buscar por ID");
        System.out.println("      2) Buscar por Nombre");
        System.out.println("      3) Volver al menú");
        System.out.print("Opción: ");
        String metodoBusqueda = scanner.nextLine();

        if (metodoBusqueda.equals("1")) {
            System.out.print("Ingrese el ID del producto a buscar: ");
            try {
                int idBuscar = Integer.parseInt(scanner.nextLine());
                Producto productoEncontrado = gestorInventario.buscarProductoPorId(idBuscar);

                if (productoEncontrado != null) {
                    System.out.println("Producto encontrado: " + productoEncontrado);
                    System.out.print("¿Desea actualizar este producto? (s/n): ");
                    String respuesta = scanner.nextLine();

                    if (respuesta.equalsIgnoreCase("s")) {
                        procesarActualizacionProducto(idBuscar);
                    }
                } else {
                    System.out.println("❌ No se encontró ningún producto con ID " + idBuscar);
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Asegúrese de ingresar un número válido para el ID. Operación cancelada.");
            }
        } else if (metodoBusqueda.equals("2")) {
            System.out.print("Ingrese el NOMBRE del producto a buscar: ");
            String nombreBuscar = scanner.nextLine();
            Producto productoEncontrado = gestorInventario.buscarProductoPorNombre(nombreBuscar);

            if (productoEncontrado != null) {
                System.out.println("Producto encontrado: " + productoEncontrado);
                System.out.print("¿Desea actualizar este producto? (s/n): ");
                String respuesta = scanner.nextLine();

                if (respuesta.equalsIgnoreCase("s")) {
                    int idBuscar = productoEncontrado.getId();
                    procesarActualizacionProducto(idBuscar);
                }
            } else {
                System.out.println("❌ No se encontró ningún producto con NOMBRE " + nombreBuscar);
            }
        } else {
            System.out.println("Opción de búsqueda no válida. Volviendo al menú.");
        }
    }

    private void procesarActualizacionProducto(int idBuscar) {
        System.out.println("  ¿Qué desea actualizar?");
        System.out.println("  1) Actualizar Nombre");
        System.out.println("  2) Actualizar Precio");
        System.out.println("  3) Actualizar Stock");
        System.out.println("  4) Volver al menú");
        System.out.print("Opción: ");
        String opcion = scanner.nextLine();

        switch (opcion) {
            case "1":
                System.out.print("Ingrese el nuevo nombre: ");
                String nuevoNombre = scanner.nextLine();
                gestorInventario.actualizarNombreProducto(idBuscar, nuevoNombre);
                break;
            case "2":
                System.out.print("Ingrese el nuevo precio: ");
                try {
                    double nuevoPrecio = Double.parseDouble(scanner.nextLine());
                    gestorInventario.actualizarPrecioProducto(idBuscar, nuevoPrecio);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Entrada inválida. Asegúrese de ingresar un número válido para el precio. Operación cancelada.");
                }
                break;
            case "3":
                System.out.print("Ingrese el nuevo stock: ");
                try {
                    int nuevoStock = Integer.parseInt(scanner.nextLine());
                    gestorInventario.actualizarStockProducto(idBuscar, nuevoStock);
                } catch (NumberFormatException e) {
                    System.out.println("❌ Entrada inválida. Asegúrese de ingresar un número válido para el stock. Operación cancelada.");
                }
                break;
            case "4":
                System.out.println("Volviendo al menú principal...");
                break;
            default:
                System.out.println("❌ Opción no válida.");
        }
    }


    private void gestionarEliminarProducto() {
        System.out.println("\n ------ 4. ELIMINAR PRODUCTO ------");
        try{
            System.out.print("Ingrese el ID del producto a eliminar: ");
            int idEliminar = Integer.parseInt(scanner.nextLine());

            Producto producto = gestorInventario.buscarProductoPorId(idEliminar);
            if (producto == null) {
                System.out.println("❌ No se encontró ningún producto con ID " + idEliminar);
                return;
            }

            System.out.println("Producto encontrado: " + producto);
            System.out.print("¿Está seguro que desea eliminar este producto? (s/n): ");
            String confirm = scanner.nextLine();

            if (confirm.equalsIgnoreCase("s")) {
                gestorInventario.eliminarProducto(idEliminar);
                System.out.println("Producto eliminado correctamente.");
            } else {
                System.out.println("Operación cancelada. No se eliminó el producto.");
            }
        } catch (NumberFormatException e) {
            System.out.println("❌ Entrada inválida. Asegúrese de ingresar un número válido para el ID. Operación Cancelada");
        }

    }


    private void gestionarCrearPedido() {
        System.out.println("\n ------ 5. CREAR UN PEDIDO ------");
        Pedido nuevoPedido = gestorPedidos.iniciarPedido();
        boolean agregandoProductos = true;

        while (agregandoProductos) {
            System.out.println("Agregando productos al Pedido ID: " + nuevoPedido.getId());
            gestorInventario.mostrarProductos();
            System.out.print("Ingrese el ID del producto a agregar al pedido (o '0' para finalizar): ");
            try{
                int idProducto = Integer.parseInt(scanner.nextLine());

                if (idProducto == 0) {
                    agregandoProductos = false;
                    continue;
                }

                System.out.print("Ingrese la cantidad deseada: ");
                int cantidad = Integer.parseInt(scanner.nextLine());
                try{
                    gestorPedidos.agregarLineaAlPedido(nuevoPedido, idProducto, cantidad);
                } catch (StockInsuficienteException sie) {
                    System.out.println("❌ No se pudo agregar: " + sie.getMessage());
                }
                System.out.println("------------------------------");
            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Asegúrese de ingresar números válidos para ID y cantidad. Operación Cancelada");
            }
        }

        gestorPedidos.confirmarPedido(nuevoPedido);
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






