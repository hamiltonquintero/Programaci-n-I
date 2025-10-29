package com.mycompany.programacion;

public interface IProcesadorPago {
    // se dice que el metodo debe ser implementado por cada clase de pago
    boolean procesar(double monto);

}
