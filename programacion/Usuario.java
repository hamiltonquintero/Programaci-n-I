
package com.mycompany.programacion;

import java.util.Date;

public abstract class Usuario {
    protected String nombre;
    protected String email;
    protected String contrasena;
    protected Date fechaRegistro;
    protected boolean estadoLogin;

    public Usuario(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        // Asignamos la fecha actual en la creación
        this.fechaRegistro = new Date(); 
        this.estadoLogin = false;
    }

    // Método polimórfico de inicio de sesión
    public boolean iniciarSesion(String contrasena) {
        if (this.contrasena.equals(contrasena)) {
            this.estadoLogin = true;
            System.out.println(nombre + " ha iniciado sesión.");
            return true;
        }
        System.out.println("Fallo de inicio de sesión para " + nombre);
        return false;
    }

    public void cerrarSesion() {
        this.estadoLogin = false;
        System.out.println(nombre + " ha cerrado sesión.");
    }
    
    // Método abstracto (fuerza a las subclases Cliente y Administrador a implementarlo)
    public abstract void actualizarDatos();
    
    // Getters esenciales
    public String getNombre() { return nombre; }
}