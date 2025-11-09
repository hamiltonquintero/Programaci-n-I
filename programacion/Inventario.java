package com.mycompany.programacion;

import java.util.ArrayList;
import java.util.List;
//Esta clase gestiona el inventario del producto disponible en el sistema
//Permite agregar productos,disminuir stock y mostrar el inventario actual
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
//Muestra todos los productos del inventario con su stock actual
    public void listarInventario() {
        System.out.println("\n--- Inventario Actual ---");
        for (Producto p : productos) {
            System.out.println(p.getNombre() + " | Precio: $" + p.calcularPrecioTotal() + " | Stock: " + p.getStock());
        }
        System.out.println("-------------------------");
    }

}
