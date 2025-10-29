package com.mycompany.programacion;

import java.util.ArrayList;
import java.util.List;

public class Inventario {
    private List<Producto> productos; // Asociación con Producto

    public Inventario() {
        this.productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto) {
        this.productos.add(producto);
    }

    public void disminuirStock(Producto producto, int cantidad) {
        producto.disminuirStock(cantidad);
    }

    public void listarInventario() {
        System.out.println("\n--- Inventario Actual ---");
        for (Producto p : productos) {
            System.out.println(p.getNombre() + " | Precio: $" + p.calcularPrecioTotal() + " | Stock: " + p.getStock());
        }
        System.out.println("-------------------------");
    }
}