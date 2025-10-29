package com.mycompany.programacion;

import java.util.Date;

public class Pago {
    private Pedido pedido; 
    private IProcesadorPago procesador; 
    private String metodoPago; 
    private Date fechaPago;
    private double monto;

    public Pago(Pedido pedido, IProcesadorPago procesador, String metodoPago) {
        this.pedido = pedido;
        this.metodoPago = metodoPago;
        this.procesador = procesador;
        this.monto = pedido.getTotal() + pedido.getInformacionEnvio().getCostoEnvio(); 
    }

    public boolean procesarPago() {
        System.out.printf("Iniciando pago de $%.2f con %s...\n", monto, metodoPago);
        
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
    
    public Pedido getPedido() { return pedido; }
    public String getMetodoPago() { return metodoPago; }
    public Date getFechaPago() { return fechaPago; }
    public double getMonto() { return monto; }

    public void emitirRecibo() {
        System.out.println("Recibo emitido para el pedido " + pedido.getCliente().getNombre());
    }

}
