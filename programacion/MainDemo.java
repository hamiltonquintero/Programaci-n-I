package com.mycompany.programacion;

import java.util.Date;
import java.util.Scanner;

public class MainDemo {
    public static void main(String[] args) {
        System.out.println("--- DEMOSTRACION CARRITO DE COMPRAS ---");

       //se inicia recursos 
        Inventario inventarioPrincipal = new Inventario();
        Scanner scanner = new Scanner(System.in);

        // se crea producto y se da inicio a inventario
        Producto lapiz = new Producto("Lapiz HB", 1.00, 50, "Papeleria");
        Producto cuaderno = new Producto("Cuaderno A4", 5.00, 20, "Papeleria");
        
        Date inicio = new Date(System.currentTimeMillis() - 86400000); 
        Date fin = new Date(System.currentTimeMillis() + 86400000 * 5); 
        Descuento descuentoCuaderno = new Descuento("50% OFF", 0.50, inicio, fin);
        cuaderno.setDescuento(descuentoCuaderno);

        inventarioPrincipal.agregarProducto(lapiz);
        inventarioPrincipal.agregarProducto(cuaderno);

        // se pregunta por los datos al cliente
        System.out.println("\n--- REGISTRO DEL NUEVO CLIENTE ---");
        
        System.out.print("Nombre completo: ");
        String nombreCliente = scanner.nextLine();
        
        System.out.print("Email: ");
        String emailCliente = scanner.nextLine();
        
        System.out.print("Contrasena: ");
        String passwordCliente = scanner.nextLine();
        
        System.out.print("Direccion de envio: ");
        String direccionCliente = scanner.nextLine();
        
        System.out.print("Telefono: ");
        String telefonoCliente = scanner.nextLine();
        
        // aqui se crea el objeto Cliente usando TODAS las entradas del usuario segun lo visto en clase 
        Cliente clienteActual = new Cliente(
            nombreCliente,
            emailCliente, 
            passwordCliente, 
            direccionCliente, 
            telefonoCliente
        );
        
        // aqui intentamos iniciar sesión con la contraseña proporcionada
        clienteActual.iniciarSesion(passwordCliente);
        
        CarritoDeCompras carrito = new CarritoDeCompras(nombreCliente);

        // se da paso a la Simulacion la adición de productos
        System.out.println("\n-- INICIANDO COMPRA --");
        inventarioPrincipal.listarInventario();

        System.out.print("¿Cuantos Lápices HB quieres comprar? (Stock: " + lapiz.getStock() + "): ");
        int cantidadLapiz = scanner.nextInt();
        if (cantidadLapiz > 0) {
             carrito.agregarItem(lapiz, cantidadLapiz);
        }

        System.out.print("¿Cuantos Cuadernos A4 quieres comprar? (Stock: " + cuaderno.getStock() + " | Precio con descuento: $" + cuaderno.calcularPrecioTotal() + "): ");
        int cantidadCuaderno = scanner.nextInt();
        if (cantidadCuaderno > 0) {
             carrito.agregarItem(cuaderno, cantidadCuaderno);
        }
        
        scanner.nextLine(); 

       // se procesa el pedido y el pago
        carrito.mostrarResumen();
        
        System.out.print("Introduce el tipo de envio (Estandar o Express): ");
        String tipoEnvio = scanner.nextLine();
        
        System.out.print("Introduce el metodo de pago (Efectivo o Tarjeta): ");
        String metodoPagoString = scanner.nextLine(); 
        
        Pedido nuevoPedido = new Pedido(clienteActual, carrito, tipoEnvio);
        nuevoPedido.generarFactura();
        
        //segun lo visto en clase, aqui se da inicoo al polimorfismo 
        
        IProcesadorPago procesadorSeleccionado;
        
        if (metodoPagoString.equalsIgnoreCase("Tarjeta")) {
            procesadorSeleccionado = new TarjetaProcesador();
        } else { 
            procesadorSeleccionado = new EfectivoProcesador();
        }
        
        Pago pago = new Pago(nuevoPedido, procesadorSeleccionado, metodoPagoString); 
        
        // aqui termina el  polimorfismo

        if (pago.procesarPago()) {
            
            for (ItemCarrito item : carrito.getItems()) {
                inventarioPrincipal.disminuirStock(item.getProducto(), item.getCantidad());
            }
            carrito.vaciarCarrito();
        } else {
             System.out.println("\n*** Transaccion CANCELADA por fallo en el pago. ***");
        }
        
     
      // aqui se da demostracion interactiva de reseñas
        System.out.println("\n\n--- INGRESO DE RESEÑA (OPCIONAL) ---");
        
        System.out.print("¿Deseas dejar una reseña para el producto \"" + lapiz.getNombre() + "\"? (s/n): ");
        String respuesta = scanner.nextLine();
        
        if (respuesta.equalsIgnoreCase("s")) {
            
            int calificacion = 0;
            
            while (calificacion < 1 || calificacion > 5) {
                System.out.print("Introduce tu calificacion (1 a 5): ");
              
                if (scanner.hasNextInt()) {
                    calificacion = scanner.nextInt();
                } else {
                  
                    scanner.nextLine(); 
                    System.out.println("Entrada invalida. Por favor, introduce un numero.");
                }
            }
            
            scanner.nextLine(); 
            
            System.out.print("Escribe tu comentario: ");
            String comentario = scanner.nextLine();

            try {
                // se crea la reseña con los datos del usuario 
                Resena nuevaResena = new Resena(clienteActual.getNombre(), calificacion, comentario);
                lapiz.agregarResena(nuevaResena);
                System.out.println("\nReseña agregada con exito por " + clienteActual.getNombre() + ".");
            } catch (IllegalArgumentException e) {
                System.out.println("ERROR: No se pudo crear la resena: " + e.getMessage());
            }
        } else {
            System.out.println("No se ha agregado ninguna resena.");
        }
        
        // se procede a mostrar el producto con la reseña 
        lapiz.mostrarResenas();

        inventarioPrincipal.listarInventario();
        scanner.close();
    }
}
