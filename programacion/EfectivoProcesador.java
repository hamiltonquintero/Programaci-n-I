package com.mycompany.programacion;

public class EfectivoProcesador implements IProcesadorPago {
    @Override
    public boolean procesar(double monto) {
        System.out.println("LOG: Verificando monto en efectivo...");
        // se dice que si en efectivo, el pago siempre es exitoso siempre y cuando el monto sea mayo que cero
        //si es mayor que 0 se considera que el cliente no tiene suficiente efectivo
        return monto > 0;
    }
}

