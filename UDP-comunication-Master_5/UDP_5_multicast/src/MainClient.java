// Classe principale che avvia il client
public class MainClient {

    public static void main(String[] args) {

        // Creazione dell'oggetto Client
        // stesso indirizzo multicast e stessa porta del server
        Client client = new Client("230.0.0.1", 9876);

        // Avvio del client
        client.start();

    }
}