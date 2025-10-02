import java.util.Scanner;

public class CamaleonBasico {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();

        System.out.print("Cantidad de números a validar: ");
        int cantidad = sc.nextInt();

        for (int i = 0; i < cantidad; i++) {
            int numero = rand.nextInt(90000) + 10000; 
            int suma = 0;
            int invertido = 0;
            int copia = numero;

            while (copia > 0) {
                int digito = copia % 10;
                suma += digito;
                invertido = invertido * 10 + digito;
                copia /= 10;
            }

            boolean esCamaleon = (suma % 2 == 0) && (invertido % 3 == 0);
            System.out.println(numero + " > " + (esCamaleon ? "Sí" : "No"));
        }

        sc.close();
    }
}

