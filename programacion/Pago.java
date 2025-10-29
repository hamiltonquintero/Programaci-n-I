package com.mycompany.programacion;

import java.util.Date;

public class Pago {
    private Pedido pedido; 
    // CAMBIO 1: El atributo ahora es de tipo Interfaz
    private IProcesadorPago procesador; 
    private String metodoPago; 
    private Date fechaPago;
    private double monto;

    // CAMBIO 2: El constructor recibe un objeto que implementa la interfaz
    public Pago(Pedido pedido, IProcesadorPago procesador, String metodoPago) {
        this.pedido = pedido;
        this.metodoPago = metodoPago;
        this.procesador = procesador;
        this.monto = pedido.getTotal() + pedido.getInformacionEnvio().getCostoEnvio(); 
    }

    // CAMBIO 3: Delega el trabajo. Usa el método procesar() de la Interfaz.
    public boolean procesarPago() {
        System.out.printf("Iniciando pago de $%.2f con %s...\n", monto, metodoPago);
        
        // Llama al método procesar() del objeto específico (TarjetaProcesador o EfectivoProcesador)
        if (procesador.procesar(monto)) {
            this.fechaPago = new Date();
            pedido.actualizarEstado("Pagado"); 
            System.out.println("Pago exitoso. Fecha: " + fechaPago);
            return true;
        } else {
            pedido.actualizarEstado("Fallo de Pago");
            System.out.println("ERROR: El pago con " + metodoPago + " ha fallado.");
            return false;
        }
    }
    
    // Getters
    public Pedido getPedido() { return pedido; }
    public String getMetodoPago() { return metodoPago; }
    public Date getFechaPago() { return fechaPago; }
    public double getMonto() { return monto; }

    public void emitirRecibo() {
        System.out.println("Recibo emitido para el pedido " + pedido.getCliente().getNombre());
    }
}