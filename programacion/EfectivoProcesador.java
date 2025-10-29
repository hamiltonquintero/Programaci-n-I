package com.mycompany.programacion;

public class EfectivoProcesador implements IProcesadorPago {
    @Override
    public boolean procesar(double monto) {
        System.out.println("LOG: Verificando monto en efectivo...");
        // En efectivo, el pago siempre es exitoso si el monto es > 0.
        return monto > 0;
    }
}