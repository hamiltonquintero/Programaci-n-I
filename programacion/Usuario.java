
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
        // aqui hacemos la asignacion de la fecha actual en la creacion
        this.fechaRegistro = new Date(); 
        this.estadoLogin = false;
    }

    // se da inicio al metoo polimórfico de inicio de sesión segun lo visto en clase 
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
    
    // se da paso al metodo abstracto donde fuerza a las subclases cliente y administrador a implementarlo
    public abstract void actualizarDatos();
    

    public String getNombre() { return nombre; }

}
