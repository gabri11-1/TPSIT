// Import delle classi necessarie per il networking
import java.net.*;

public class Client {

    // Indirizzo del gruppo multicast
    private String groupAddress;

    // Porta di comunicazione
    private int port;

    // Costruttore del client
    public Client(String groupAddress, int port) {
        this.groupAddress = groupAddress;
        this.port = port;
    }

    // Metodo che avvia il client
    public void start() {

        try {

            // Creazione del socket multicast sulla porta indicata
            MulticastSocket socket = new MulticastSocket(port);

            // Ottiene l'indirizzo del gruppo multicast
            InetAddress group = InetAddress.getByName(groupAddress);

            // Il client si unisce al gruppo multicast
            socket.joinGroup(group);

            System.out.println("Client in ascolto sul gruppo multicast...");

            // Buffer per ricevere i dati
            byte[] buffer = new byte[1024];

            // Ciclo infinito: il client resta sempre in ascolto
            while (true) {

                // Creazione del pacchetto per ricevere dati
                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);

                // Attende la ricezione di un messaggio dal gruppo
                socket.receive(packet);

                // Converte i dati ricevuti da byte a stringa
                String message =
                        new String(packet.getData(), 0, packet.getLength());

                // Stampa il messaggio ricevuto
                System.out.println("Messaggio ricevuto: " + message);
            }

        } catch (Exception e) {

            // Gestione degli errori
            e.printStackTrace();
        }
    }
}