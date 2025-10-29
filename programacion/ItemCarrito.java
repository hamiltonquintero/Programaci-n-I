package com.mycompany.programacion;

public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public double calcularSubtotal() {
        // se da uso al metodo polimorfismo de producto 
        return producto.calcularPrecioTotal() * cantidad;
    }
    
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

}
