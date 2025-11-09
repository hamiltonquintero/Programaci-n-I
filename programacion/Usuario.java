
package com.mycompany.programacion;

import java.util.Date;
//Clase abstracta para todos los usuarios del sistema(cliente,administrador)
public abstract class Usuario {
    protected String nombre;
    protected String email;
    protected String contrasena;
    protected Date fechaRegistro;
    protected boolean estadoLogin;
//Incia los datos del usuario y asigna la fecha actual como fecha de registro
    public Usuario(String nombre, String email, String contrasena) {
        this.nombre = nombre;
        this.email = email;
        this.contrasena = contrasena;
        // aqui hacemos la asignacion de la fecha actual en la creacion
        this.fechaRegistro = new Date(); 
        this.estadoLogin = false;
    }
// verifica la contraseña y el estado de seccion si es correcta
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
    //Metodo abstracto que obliga las subclases a implementar su propia logica de actualizacion
    // se da paso al metodo abstracto donde fuerza a las subclases cliente y administrador a implementarlo
    public abstract void actualizarDatos();
    

    public String getNombre() { return nombre; }

}

