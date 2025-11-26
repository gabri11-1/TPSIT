import java.util.Random;
import java.util.Scanner;

public class Gioca_numeri {
    static Random random = new Random(); // <--- aggiungi static

    public static void main(String[] args) {
        System.out.println("Hello, World!");

        Giocatore g1 = new Giocatore("mario");
        g1.start();

        Giocatore g2 = new Giocatore("luigi");
        g2.start();

        try {
            g2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("fine gioco");

        genera_numeri();

    }

    public static void genera_numeri() {
        Scanner scanner = new Scanner(System.in);
        int numeroSegreto = random.nextInt(100);
        System.out.println(numeroSegreto); // <--- corretto
        int tentativi=0;
        boolean indovinato = false;
        while (!indovinato) {
            System.out.print("Inserisci un numero (0-99): ");
            String linea = scanner.nextLine().trim();
            tentativi++;

            // convertire l'input in intero gestendo input non numerici
            int guess;
            try {
                guess = Integer.parseInt(linea);
            } catch (NumberFormatException e) {
                System.out.println("Non hai inserito un numero valido. Riprova.");
                continue;
            }

            if (guess < 0 || guess > 99) {
                System.out.println("Numero fuori intervallo. Inserisci un numero tra 0 e 99.");
                continue;
            }

            if (guess == numeroSegreto) {
                System.out.println("Bravo! Hai indovinato il numero " + numeroSegreto + " in " + tentativi + " tentativi.");
                indovinato = true;
            } else if (guess < numeroSegreto) {
                System.out.println("Troppo basso — prova un numero più alto.");
            } else { // guess > numeroSegreto
                System.out.println("Troppo alto — prova un numero più basso.");
            }
        }


    }
}
