package com.mycompany.programacion;

import java.util.ArrayList;
import java.util.List;

// Cliente extiende de Usuario (Herencia)
public class Cliente extends Usuario {
    private String direccion;
    private String telefono;
    private double saldoCuenta;
    
    // Asociaciones (Causarán errores hasta que CarritoDeCompras y Pedido existan)
    private CarritoDeCompras carritoActivo; 
    private List<Pedido> historialPedidos; 

    public Cliente(String nombre, String email, String contrasena, String direccion, String telefono) {
        // Llama al constructor de la clase base Usuario
        super(nombre, email, contrasena); 
        this.direccion = direccion;
        this.telefono = telefono;
        this.saldoCuenta = 0.0;
        
        // Inicialización de asociaciones
        // Puedes comentar estas líneas si dan error grave por ahora, pero la estructura es esta:
        // this.carritoActivo = new CarritoDeCompras(nombre); 
        // this.historialPedidos = new ArrayList<>();
    }
    
    // Implementación obligatoria del método abstracto de Usuario (Polimorfismo)
    @Override
    public void actualizarDatos() {
        System.out.println("CLIENTE: Actualizando datos detallados de: " + nombre);
    }
    
    // Método para agregar pedidos al historial (Asociación)
    public void agregarPedido(Pedido pedido) {
        // this.historialPedidos.add(pedido);
    }
    
    // Getters
    public CarritoDeCompras getCarritoActivo() {
        return carritoActivo;
    }
    public String getDireccion() { return direccion; }
    // ... otros getters
}