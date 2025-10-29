package com.mycompany.programacion;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CarritoDeCompras {
    private String cliente;
    
    private List<ItemCarrito> items; 
    private Date fechaCreacion;
    private double total;

    public CarritoDeCompras(String cliente) {
        this.cliente = cliente;
        this.items = new ArrayList<>();
        this.fechaCreacion = new Date();
        this.total = 0.0;
    }

    public void agregarItem(Producto producto, int cantidad) {
        // se usa el método verificarDisponibilidad de la clase Producto
        if (producto.verificarDisponibilidad(cantidad)) {
            items.add(new ItemCarrito(producto, cantidad));
            calcularTotal();
        } else {
            System.out.println("AVISO: Stock insuficiente para " + producto.getNombre());
        }
    }

    public double calcularTotal() {
        this.total = 0.0;
        for (ItemCarrito item : items) {
            this.total += item.calcularSubtotal();
        }
        return this.total;
    }
    
    public void mostrarResumen() {
        System.out.println("\n--- Resumen del Carrito para " + cliente + " ---");
        
        System.out.printf("Total Final: $%.2f\n", this.total);
    }
    
    public List<ItemCarrito> getItems() { return items; }
    public double getTotal() { return total; }
    public void vaciarCarrito() { this.items.clear(); this.total = 0.0; }

}
