
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
        
        return ahora.after(fechaInicio) && ahora.before(fechaFin);
    }

    public double aplicarDescuento(double precioOriginal) {
        if (estaActivo()) {
           
            return precioOriginal * (1.0 - porcentaje); 
        }
        return precioOriginal;
    }
}

