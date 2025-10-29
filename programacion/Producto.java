package com.mycompany.programacion;

public class Producto {
    private String nombre;
    private String descripcion;
    protected double precio; 
    private int stock;
    private String categoria;
    private Descuento descuentoAplicado; 

    public Producto(String nombre, double precio, int stock, String categoria) {
        this.nombre = nombre;
        this.precio = precio;
        this.stock = stock;
        this.categoria = categoria;
    }

    public double calcularPrecioTotal() {
        double precioFinal = precio;
        if (descuentoAplicado != null && descuentoAplicado.estaActivo()) {
             precioFinal = descuentoAplicado.aplicarDescuento(precio);
        }
        return precioFinal;
    }

    public boolean verificarDisponibilidad(int cantidad) {
        return this.stock >= cantidad;
    }
    
    public void disminuirStock(int cantidad) {
        this.stock -= cantidad;
    }
    
    public void setDescuento(Descuento descuento) {
        this.descuentoAplicado = descuento;
    }

  
    public String getNombre() { return nombre; }
    public double getPrecioBase() { return precio; }
    public int getStock() { return stock; }

}
