package com.mycompany.programacion;

import java.util.ArrayList;
import java.util.List;

// la clase cliente se extiende  de Usuario (Herencia)
public class Cliente extends Usuario {
    private String direccion;
    private String telefono;
    private double saldoCuenta;
    
    private CarritoDeCompras carritoActivo; 
    private List<Pedido> historialPedidos; 

    public Cliente(String nombre, String email, String contrasena, String direccion, String telefono) {
        //se hace llamado al constructor de la clase base Usuario
        super(nombre, email, contrasena); 
        this.direccion = direccion;
        this.telefono = telefono;
        this.saldoCuenta = 0.0;
        
    
    }
    
    @Override
    public void actualizarDatos() {
        System.out.println("CLIENTE: Actualizando datos detallados de: " + nombre);
    }
    
    // Método para agregar pedidos al historial (Asociación)
    public void agregarPedido(Pedido pedido) {
        
    }
    

    //delvuelve el carrito activo del cliente
    public CarritoDeCompras getCarritoActivo() {
        return carritoActivo;
    }

    //devuelve la direcion registrada del cliente 
    public String getDireccion() { return direccion; }
   
}

