package com.mycompany.programacion;

public class TarjetaProcesador implements IProcesadorPago {
    
    
    @Override
    public boolean procesar(double monto) {
       
        System.out.println("LOG: Contactando pasarela de pago y verificando tarjeta...");
        
        return Math.random() < 0.9;
    }

}
