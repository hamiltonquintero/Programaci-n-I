package com.mycompany.programacion;

import java.util.Date;
import java.util.Scanner;
// Asegúrate de que IProcesadorPago, EfectivoProcesador, y TarjetaProcesador existan

public class MainDemo {
    public static void main(String[] args) {
        System.out.println("--- DEMOSTRACIÓN INTERACTIVA DE E-COMMERCE ---");

        // 0. Inicializar recursos
        Inventario inventarioPrincipal = new Inventario();
        Scanner scanner = new Scanner(System.in);

        // 1. Crear Productos (Inicializar inventario)
        Producto lapiz = new Producto("Lápiz HB", 1.00, 50, "Papelería");
        Producto cuaderno = new Producto("Cuaderno A4", 5.00, 20, "Papelería");
        
        Date inicio = new Date(System.currentTimeMillis() - 86400000); 
        Date fin = new Date(System.currentTimeMillis() + 86400000 * 5); 
        Descuento descuentoCuaderno = new Descuento("50% OFF", 0.50, inicio, fin);
        cuaderno.setDescuento(descuentoCuaderno);

        inventarioPrincipal.agregarProducto(lapiz);
        inventarioPrincipal.agregarProducto(cuaderno);

        // 2. Interacción: Preguntar por los datos COMPLETOS del cliente
        System.out.println("\n--- REGISTRO DEL NUEVO CLIENTE ---");
        
        System.out.print("Nombre completo: ");
        String nombreCliente = scanner.nextLine();
        
        System.out.print("Email: ");
        String emailCliente = scanner.nextLine();
        
        System.out.print("Contraseña: ");
        String passwordCliente = scanner.nextLine();
        
        System.out.print("Dirección de envío: ");
        String direccionCliente = scanner.nextLine();
        
        System.out.print("Teléfono: ");
        String telefonoCliente = scanner.nextLine();
        
        // Creamos el objeto Cliente usando TODAS las entradas del usuario
        Cliente clienteActual = new Cliente(
            nombreCliente,
            emailCliente, 
            passwordCliente, 
            direccionCliente, 
            telefonoCliente
        );
        
        // Intentamos iniciar sesión con la contraseña proporcionada
        clienteActual.iniciarSesion(passwordCliente);
        
        CarritoDeCompras carrito = new CarritoDeCompras(nombreCliente);

        // 3. Interacción: Simular la adición de productos
        System.out.println("\n-- INICIANDO COMPRA --");
        inventarioPrincipal.listarInventario();

        System.out.print("¿Cuántos Lápices HB quieres comprar? (Stock: " + lapiz.getStock() + "): ");
        int cantidadLapiz = scanner.nextInt();
        if (cantidadLapiz > 0) {
             carrito.agregarItem(lapiz, cantidadLapiz);
        }

        System.out.print("¿Cuántos Cuadernos A4 quieres comprar? (Stock: " + cuaderno.getStock() + " | Precio con descuento: $" + cuaderno.calcularPrecioTotal() + "): ");
        int cantidadCuaderno = scanner.nextInt();
        if (cantidadCuaderno > 0) {
             carrito.agregarItem(cuaderno, cantidadCuaderno);
        }
        
        // Consumir el salto de línea pendiente después de nextInt()
        scanner.nextLine(); 

       // 4. Procesar Pedido y Pago (Interactivo)
        carrito.mostrarResumen();
        
        System.out.print("Introduce el tipo de envío (Estandar o Express): ");
        String tipoEnvio = scanner.nextLine();
        
        System.out.print("Introduce el método de pago (Efectivo o Tarjeta): ");
        String metodoPagoString = scanner.nextLine(); 
        
        Pedido nuevoPedido = new Pedido(clienteActual, carrito, tipoEnvio);
        nuevoPedido.generarFactura();
        
        // ******* ZONA DE POLIMORFISMO: CREACIÓN DEL PROCESADOR CORRECTO *******
        
        IProcesadorPago procesadorSeleccionado;
        
        if (metodoPagoString.equalsIgnoreCase("Tarjeta")) {
            procesadorSeleccionado = new TarjetaProcesador();
        } else { 
            procesadorSeleccionado = new EfectivoProcesador();
        }
        
        Pago pago = new Pago(nuevoPedido, procesadorSeleccionado, metodoPagoString); 
        
        // ******* FIN DEL POLIMORFISMO *******

        if (pago.procesarPago()) {
            // CORRECCIÓN DE STOCK: Iteramos sobre los ítems REALES comprados.
            for (ItemCarrito item : carrito.getItems()) {
                inventarioPrincipal.disminuirStock(item.getProducto(), item.getCantidad());
            }
            carrito.vaciarCarrito();
        } else {
             System.out.println("\n*** Transacción CANCELADA por fallo en el pago. ***");
        }
        
        // --- 5. DEMOSTRACIÓN DE RESEÑAS ---
      // --- 5. DEMOSTRACIÓN INTERACTIVA DE RESEÑAS ---
        System.out.println("\n\n--- INGRESO DE RESEÑA (OPCIONAL) ---");
        
        System.out.print("¿Deseas dejar una reseña para el producto \"" + lapiz.getNombre() + "\"? (s/n): ");
        String respuesta = scanner.nextLine();
        
        if (respuesta.equalsIgnoreCase("s")) {
            
            int calificacion = 0;
            // Bucle para validar que la calificación sea un número entre 1 y 5
            while (calificacion < 1 || calificacion > 5) {
                System.out.print("Introduce tu calificación (1 a 5): ");
                // Usamos nextInt() para leer el número
                if (scanner.hasNextInt()) {
                    calificacion = scanner.nextInt();
                } else {
                    // Si no es un número, consumimos la línea para evitar bucles infinitos
                    scanner.nextLine(); 
                    System.out.println("Entrada inválida. Por favor, introduce un número.");
                }
            }
            // Consumir el salto de línea pendiente después de nextInt()
            scanner.nextLine(); 
            
            System.out.print("Escribe tu comentario: ");
            String comentario = scanner.nextLine();

            try {
                // Creamos la reseña con los datos del usuario
                Resena nuevaResena = new Resena(clienteActual.getNombre(), calificacion, comentario);
                lapiz.agregarResena(nuevaResena);
                System.out.println("\nReseña agregada con éxito por " + clienteActual.getNombre() + ".");
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: No se pudo crear la reseña: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha agregado ninguna reseña.");
        }
        
        // MOSTRAR EL PRODUCTO CON LA NUEVA RESEÑA
        lapiz.mostrarResenas();

        // 6. Final
        inventarioPrincipal.listarInventario();
        scanner.close();
    }
}
