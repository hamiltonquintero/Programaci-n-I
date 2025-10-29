package com.mycompany.programacion;

// Definición de la interfaz
public interface IProcesadorPago {
    // Este método debe ser implementado por cada clase de pago
    boolean procesar(double monto);
}