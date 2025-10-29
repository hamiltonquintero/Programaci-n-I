package com.mycompany.programacion;

public class TarjetaProcesador implements IProcesadorPago {
    // Podrías agregar aquí atributos como numeroTarjeta, cvv, etc.
    
    @Override
    public boolean procesar(double monto) {
        // Lógica de validación compleja (simulada)
        System.out.println("LOG: Contactando pasarela de pago y verificando tarjeta...");
        // Simulamos un 90% de éxito en pagos con tarjeta
        return Math.random() < 0.9;
    }
}