package com.mycompany.programacion;

import java.util.Date;
import java.util.Scanner; // Importar la clase Scanner

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

// 3. Interacción: Preguntar por los datos COMPLETOS del cliente
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
        
        // Decidimos qué clase de procesador usar basado en la entrada del usuario
        if (metodoPagoString.equalsIgnoreCase("Tarjeta")) {
            procesadorSeleccionado = new TarjetaProcesador();
        } else { // Si es "Efectivo", o cualquier otra cosa no reconocida, usamos Efectivo
            procesadorSeleccionado = new EfectivoProcesador();
        }
        
        // ¡CORRECCIÓN DEL ERROR! Ahora pasamos los 3 argumentos al constructor de Pago
        // (Pedido, IProcesadorPago, String)
        Pago pago = new Pago(nuevoPedido, procesadorSeleccionado, metodoPagoString); 
        
        // ******* FIN DEL POLIMORFISMO *******

        if (pago.procesarPago()) {
            inventarioPrincipal.disminuirStock(lapiz, cantidadLapiz);
            inventarioPrincipal.disminuirStock(cuaderno, cantidadCuaderno);
            carrito.vaciarCarrito();
        } else {
             System.out.println("\n*** Transacción CANCELADA por fallo en el pago. ***");
        }
        
        // 5. Final
        inventarioPrincipal.listarInventario();
        scanner.close();
        
        // 5. Final
        inventarioPrincipal.listarInventario();
        scanner.close(); // Cerrar el scanner al finalizar
    }
}