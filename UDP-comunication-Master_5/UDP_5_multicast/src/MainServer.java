// Classe principale che avvia il server
public class MainServer {

    public static void main(String[] args) {

        // Creazione dell'oggetto Server
        // indirizzo multicast 230.0.0.1 e porta 9876
        Server server = new Server("230.0.0.1", 9876);

        // Avvio del server
        server.start();

    }
}