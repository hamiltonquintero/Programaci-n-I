package com.mycompany.programacion;
//Esta calse representa un item dentro del carrito de compras,compuesto por un producto y su cantidad
public class ItemCarrito {
    private Producto producto;
    private int cantidad;

    public ItemCarrito(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }
//Calcula el subtotal usando el precio total del producto(polimorfismo)
    public double calcularSubtotal() {
        // se da uso al metodo polimorfismo de producto 
        return producto.calcularPrecioTotal() * cantidad;
    }
    //Devuelve producto asociado
    public Producto getProducto() { return producto; }
    public int getCantidad() { return cantidad; }

}

