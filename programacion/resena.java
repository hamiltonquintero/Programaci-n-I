package com.mycompany.programacion;

import java.util.Date;

public class Resena {
    private String clienteNombre;
    private int calificacion; // Escala de 1 a 5
    private String comentario;
    private Date fechaResena;

    public Resena(String clienteNombre, int calificacion, String comentario) {
        // Validación básica
        if (calificacion < 1 || calificacion > 5) {
            throw new IllegalArgumentException("La calificación debe estar entre 1 y 5.");
        }
        this.clienteNombre = clienteNombre;
        this.calificacion = calificacion;
        this.comentario = comentario;
        this.fechaResena = new Date();
    }

    public int getCalificacion() { return calificacion; }
    public String getComentario() { return comentario; }
    
    public void mostrarResena() {
        System.out.println("  > Cliente: " + clienteNombre + " | Calificación: " + calificacion + "/5");
        System.out.println("    Comentario: \"" + comentario + "\" (Fecha: " + fechaResena + ")");
    }
}
