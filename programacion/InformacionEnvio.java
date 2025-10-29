package com.mycompany.programacion;

import java.util.Date;

public class InformacionEnvio {
    private String tipoEnvio;
    private double costoEnvio;
    private String direccionEntrega;
    private Date fechaEstimadaEntrega;

    public InformacionEnvio(String tipoEnvio, String direccionEntrega) {
        this.tipoEnvio = tipoEnvio;
        this.direccionEntrega = direccionEntrega;
        this.costoEnvio = calcularCostoEnvio(); 
        // Ejemplo: 3 días desde ahora
        this.fechaEstimadaEntrega = new Date(System.currentTimeMillis() + 86400000 * 3); 
    }

    public double calcularCostoEnvio() {
        return (tipoEnvio.equalsIgnoreCase("Express")) ? 10.00 : 5.00;
    }
    
    // Getters
    public double getCostoEnvio() { return costoEnvio; }
}