# ☕ Sistema de Gestión de Inventario y Pedidos - Techlab

Este es un proyecto de consola desarrollado en Java que simula un sistema de gestión básico para un bazar o tienda. La aplicación permite administrar un inventario de productos y procesar pedidos de clientes, validando el stock disponible.

Fue creado como un ejercicio práctico para aplicar los conceptos fundamentales de la Programación Orientada a Objetos (POO) y la gestión de código en Java.

## 🚀 Funcionalidades

* **Gestión de Inventario (CRUD):**
    * **Agregar:** Añadir nuevos productos al inventario.
    * **Listar:** Ver todos los productos disponibles, su precio y stock.
    * **Actualizar:** Modificar el precio o stock de un producto existente.
    * **Eliminar:** Quitar un producto del inventario.
* **Gestión de Pedidos:**
    * **Crear Pedido:** Iniciar un nuevo pedido, agregar productos y cantidades.
    * **Validación de Stock:** El sistema impide que se pidan más productos de los que hay disponibles.
    * **Actualización de Inventario:** El stock se descuenta automáticamente al confirmar un pedido.
    * **Listar Pedidos:** Ver un historial de todos los pedidos realizados y su costo total.
* **Menú Interactivo:** Navegación por consola amigable para el usuario.

## 🛠️ Tecnologías y Conceptos AplicADOS

* **Lenguaje:** Java (JDK 21)
* **IDE:** IntelliJ IDEA
* **Programación Orientada a Objetos (POO):**
    * **Encapsulamiento:** Atributos privados con getters/setters.
* **Colecciones:** Uso de `ArrayList` para gestionar las listas de productos y pedidos.
* **Manejo de Excepciones:**
    * Uso de `try-catch` para validar la entrada del usuario (`NumberFormatException`).
    * Creación de una excepción personalizada (`StockInsuficienteException`) para la lógica de negocio.
* **Organización:** Estructura de código limpia basada en Paquetes (modelo, servicios, principal, excepciones).

## 🏁 Cómo Empezar

### Prerrequisitos

* Tener instalado el JDK de Java (versión 21 o superior).
* Un IDE como [IntelliJ IDEA](https://www.jetbrains.com/idea/) o [VS Code](https://code.visualstudio.com/) con soporte para Java.

### Ejecución

1.  Clona o descarga este repositorio.
2.  Abre el proyecto en tu IDE (IntelliJ lo reconocerá automáticamente).
3.  Navega al archivo `src/com/MiTiendita/principal/Main.java`.
4.  Haz clic derecho en `Main.java` y selecciona **"Run 'Main.main()'"**.
5.  ¡Interactúa con el menú en la consola!

## 📂 Estructura del Proyecto

El proyecto está organizado en los siguientes paquetes para una correcta separación de responsabilidades:

src/ 
└── com/ 
    └── MiTiendita/ 
        ├── excepciones/ (Excepciones personalizadas como StockInsuficienteException) 
        ├── modelo/ (Clases Producto, Pedido, LineaPedido) 
        ├── principal/ (Clase Main para ejecutar la app y mostrar el menú) 
        └── servicios/ (Lógica de negocio: GestionarInventario, GestionPedidos)


## 👤 Erika Oropeza (2025)

* *(https://github.com/erika310313)*