
package com.mycompany.programacion;

import java.util.Date;

public class Descuento {
    private String descripcion;
    private double porcentaje;
    private Date fechaInicio;
    private Date fechaFin;

    public Descuento(String descripcion, double porcentaje, Date fechaInicio, Date fechaFin) {
        this.descripcion = descripcion;
        this.porcentaje = porcentaje;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public boolean estaActivo() {
        Date ahora = new Date();
        // Comprueba si la fecha actual está entre la de inicio y fin
        return ahora.after(fechaInicio) && ahora.before(fechaFin);
    }

    public double aplicarDescuento(double precioOriginal) {
        if (estaActivo()) {
            // Aplica el porcentaje (ej: 0.20 -> 20% de descuento)
            return precioOriginal * (1.0 - porcentaje); 
        }
        return precioOriginal;
    }
}
