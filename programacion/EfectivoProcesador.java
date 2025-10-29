package com.mycompany.programacion;

public class EfectivoProcesador implements IProcesadorPago {
    @Override
    public boolean procesar(double monto) {
        System.out.println("LOG: Verificando monto en efectivo...");
        // se dice que si en efectivo, el pafo siempre es exitoso siempre y cuando el monto sea mayo que cero
        return monto > 0;
    }
}
