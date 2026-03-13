// Import delle classi necessarie per il networking e input da tastiera
import java.net.*;
import java.util.Scanner;

public class Server {

    // Indirizzo del gruppo multicast
    private String groupAddress;

    // Porta utilizzata per la comunicazione
    private int port;

    // Costruttore della classe Server
    // riceve indirizzo multicast e porta
    public Server(String groupAddress, int port) {
        this.groupAddress = groupAddress;
        this.port = port;
    }

    // Metodo che avvia il server
    public void start() {

        try {

            // Creazione del socket multicast
            MulticastSocket socket = new MulticastSocket();

            // Conversione dell'indirizzo del gruppo da stringa a oggetto InetAddress
            InetAddress group = InetAddress.getByName(groupAddress);

            // Scanner per leggere messaggi dalla tastiera
            Scanner scanner = new Scanner(System.in);

            System.out.println("Server multicast avviato...");

            // Ciclo infinito: il server continua a inviare messaggi
            while (true) {

                // Chiede all'utente di inserire un messaggio
                System.out.print("Inserisci messaggio: ");
                String message = scanner.nextLine();

                // Converte il messaggio in array di byte
                byte[] buffer = message.getBytes();

                // Creazione del pacchetto UDP
                // contiene dati, lunghezza, indirizzo multicast e porta
                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length, group, port);

                // Invio del pacchetto a tutto il gruppo multicast
                socket.send(packet);

            }

        } catch (Exception e) {

            // Stampa errore se qualcosa va storto
            e.printStackTrace();
        }
    }
}