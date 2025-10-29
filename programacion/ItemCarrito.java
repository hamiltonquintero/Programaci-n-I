package com.mycompany.programacion;

public class ItemCarrito {
    // Asociación con Producto (ya existe)
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        // Usa el método polimórfico de Producto
        return producto.calcularPrecioTotal() * cantidad;
    }
    
    // Getters
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }
}