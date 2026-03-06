// Import delle classi necessarie per lavorare con i socket UDP
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static void main(String[] args) {
        try {
            // Crea il socket del server sulla porta 9876
            // Il server resterà in ascolto su questa porta
            DatagramSocket serverSocket = new DatagramSocket(9876);
            System.out.println("Server avviato...");

            // Buffer per ricevere i dati dal client
            byte[] receiveData = new byte[1024];

            // Buffer che conterrà i dati da inviare come risposta
            byte[] sendData;

            // Ciclo infinito: il server resta sempre in ascolto
            while (true) {

                // Creazione del pacchetto per ricevere i dati
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                // Il server attende la ricezione di un pacchetto dal client
                serverSocket.receive(receivePacket);

                // Converte i dati ricevuti (byte) in una stringa
                String message = new String(receivePacket.getData(), 0, receivePacket.getLength());

                // Stampa il messaggio ricevuto dal client
                System.out.println("Messaggio ricevuto: " + message);

                // Crea una risposta trasformando il messaggio ricevuto in maiuscolo
                String response = "Server ha ricevuto: " + message.toUpperCase();

                // Converte la risposta in byte per poterla inviare
                sendData = response.getBytes();

                // Ottiene l'indirizzo IP del client che ha inviato il messaggio
                InetAddress clientAddress = receivePacket.getAddress();

                // Ottiene la porta del client
                int clientPort = receivePacket.getPort();

                // Crea il pacchetto di risposta da inviare al client
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                // Invio della risposta al client
                serverSocket.send(sendPacket);
            }

        } catch (Exception e) {
            // Gestione degli errori: stampa le informazioni sull'errore
            e.printStackTrace();
        }
    }
}