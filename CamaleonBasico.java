import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("¿Cuántos números deseas ingresar? ");
        int cantidad = sc.nextInt();
        sc.nextLine(); // Limpiar el buffer

        System.out.print("Ingresa los " + cantidad + " números separados por comas: ");
        String entrada = sc.nextLine();
        String[] partes = entrada.split(",");

        if (partes.length != cantidad) {
            System.out.println("La cantidad de números ingresados no coincide con lo solicitado.");
            return;
        }

        int camaleones = 0;

        for (String parte : partes) {
            parte = parte.trim(); // Eliminar espacios
            int numero;

            try {
                numero = Integer.parseInt(parte);
            } catch (NumberFormatException e) {
                System.out.println(parte + " → Entrada inválida.");
                continue;
            }

            if (numero < 100 || numero > 99999) {
                System.out.println(numero + " → Número inválido. Debe tener entre 3 y 5 cifras.");
                continue;
            }

            int suma = 0, invertido = 0, copia = numero;

            while (copia > 0) {
                int digito = copia % 10;
                suma += digito;
                invertido = invertido * 10 + digito;
                copia /= 10;
            }

            boolean sumaPar = (suma % 2 == 0);
            boolean invertidoDiv3 = (invertido % 3 == 0);
            boolean esCamaleon = sumaPar && invertidoDiv3;

            if (esCamaleon) camaleones++;

            System.out.println(numero + " → " + (esCamaleon ? "Sí" : "No"));
        }

        System.out.println("Suma = " + camaleones);
    }
}

