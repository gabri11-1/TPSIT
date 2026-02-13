import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    // Oggetto Socket per gestire la comunicazione di rete
    private Socket socket;
    private String nome;
    private String colore;

    // Costruttore base con solo il nome
    public Client(String nome){
        this.nome = nome;
    }

    // Costruttore avanzato con nome e colore
    public Client(String nome, String colore){
        this.nome = nome;
        this.colore = colore;
    }

    /**
     * Tenta di stabilire una connessione TCP con il server
     */
    public void connetti(String nomeServer, int portaServer){
        try {
            // Crea il socket: qui avviene il "handshake" con il server
            socket = new Socket(nomeServer, portaServer);
            System.out.println("Il client " + nome + " si è connesso con il server");
        } catch (UnknownHostException e) {
            // Lanciato se l'indirizzo IP del server non è risolvibile
            e.printStackTrace();
        } catch (IOException e) {
            // Lanciato per errori di connessione generici (server spento, porta chiusa, ecc.)
            e.printStackTrace();
        }
    }

    /**
     * Invia un messaggio predefinito al server
     */
    public void scrivi(){
        try {
            // Ottiene lo stream di uscita dal socket per inviare dati
            OutputStream outputstream = socket.getOutputStream();
            // PrintWriter permette di scrivere testo in modo semplice (come System.out)
            PrintWriter printer = new PrintWriter(outputstream);
            
            // Scrive il messaggio (importante il \n per indicare la fine della riga)
            printer.print("Hello world\n");
            // Svuota il buffer e forza l'invio fisico dei dati sulla rete
            printer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossibile scrivere al server");
        }
    }

    /**
     * Legge una riga di testo inviata dal server
     */
    public void leggi(){
        try {
            // Ottiene lo stream di ingresso dal socket per ricevere dati
            InputStream inputStream = socket.getInputStream();
            // BufferedReader legge il testo riga per riga in modo efficiente
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            
            // Il programma si ferma qui (bloccante) finché il server non invia qualcosa
            String testo = reader.readLine();
            System.out.println("Server: "+ testo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossibile leggere dal server");
        }
    }

    /**
     * Chiude la connessione e rilascia le risorse di rete
     */
    public void chiudi(){
        try {
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}