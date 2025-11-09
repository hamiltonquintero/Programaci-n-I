
package com.mycompany.programacion;

import java.util.Date;
//revisando por (nombre) el (fecha) 
//esta clase representa un descuento aplicable a productos dentro de un rango de fechas 

//se inicia los datos de descuento incluyendo su vigencia y porcentaje 
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
    

    //se aplica el descuento solo si esta activo. Si no, delvuelve el precio original (no esta activo)
    public double aplicarDescuento(double precioOriginal) {
        if (estaActivo()) {
           
            return precioOriginal * (1.0 - porcentaje); 
        }
        return precioOriginal;
    }
}


