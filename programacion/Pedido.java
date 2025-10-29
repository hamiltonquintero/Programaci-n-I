package com.mycompany.programacion;

import java.util.Date;
import java.util.List;

public class Pedido {
    private Cliente cliente; // aqui se da la asociacion con cliente
    private List<ItemCarrito> items; // aqui se da la composición (copia de los items del Carrito)
    private Date fechaPedido;
    private String estado;
    private InformacionEnvio informacionEnvio; // Composición (ya existe)
    private double total;

    public Pedido(Cliente cliente, CarritoDeCompras carrito, String tipoEnvio) {
        this.cliente = cliente;
        // Obtenemos los ítems del carrito
        this.items = carrito.getItems(); 
        this.fechaPedido = new Date();
        this.estado = "Pendiente";
        this.total = carrito.calcularTotal();
        
        // se da paso a la creacion de la composicion informacionenvio
        this.informacionEnvio = new InformacionEnvio(tipoEnvio, cliente.getDireccion());
    }

    public void actualizarEstado(String nuevoEstado) {
        this.estado = nuevoEstado;
        System.out.println("PEDIDO: El estado del pedido de " + cliente.getNombre() + " ha cambiado a: " + nuevoEstado);
    }
    
    public void generarFactura() {
        double subtotal = total;
        double costoEnvio = informacionEnvio.getCostoEnvio();
        double totalFinal = subtotal + costoEnvio;
        
        System.out.println("\n--- FACTURA ---");
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.printf("Subtotal: $%.2f\n", subtotal);
        System.out.printf("Costo Envío: $%.2f\n", costoEnvio);
        System.out.printf("Total Final: $%.2f\n", totalFinal);
        System.out.println("Estado: " + estado);
        System.out.println("-----------------");
    }
    
    public double getTotal() { return total; }
    public InformacionEnvio getInformacionEnvio() { return informacionEnvio; }
    public Cliente getCliente() { return cliente; }

}
