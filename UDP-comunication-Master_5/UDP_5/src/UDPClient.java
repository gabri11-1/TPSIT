// Import delle classi necessarie per usare UDP e gestire input
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) {
        try {
            // Creazione del socket UDP del client
            DatagramSocket clientSocket = new DatagramSocket();

            // Ottiene l'indirizzo IP del server (in questo caso localhost)
            InetAddress serverAddress = InetAddress.getByName("localhost");

            // Scanner per leggere l'input dell'utente da tastiera
            Scanner scanner = new Scanner(System.in);

            // Chiede all'utente di inserire un messaggio
            System.out.print("Inserisci un messaggio: ");
            String message = scanner.nextLine();

            // Converte il messaggio in array di byte per poterlo inviare
            byte[] sendData = message.getBytes();

            // Buffer per ricevere la risposta dal server
            byte[] receiveData = new byte[1024];

            // Creazione del pacchetto UDP da inviare al server
            // contiene: dati, lunghezza dati, indirizzo server e porta
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, 9876);

            // Invio del pacchetto al server
            clientSocket.send(sendPacket);

            // Creazione del pacchetto per ricevere la risposta dal server
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

            // Attende e riceve la risposta dal server
            clientSocket.receive(receivePacket);

            // Converte i dati ricevuti da byte a stringa
            String response = new String(receivePacket.getData(), 0, receivePacket.getLength());

            // Stampa la risposta ricevuta dal server
            System.out.println("Risposta dal server: " + response);

            // Chiude il socket del client
            clientSocket.close();

        } catch (Exception e) {
            // Gestione degli errori: stampa lo stack trace se qualcosa va storto
            e.printStackTrace();
        }
    }
}