import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    // ServerSocket serve per restare in ascolto di nuove chiamate
    private ServerSocket serverSocket;
    // clientSocket rappresenta la connessione specifica con un client accettato
    private Socket clientSocket;
    private int porta = 12345;

    // Costruttore: apre il server sulla porta specificata
    public Server(int porta){
        this.porta = porta;
        try{
            // Tenta di riservare la porta nel sistema operativo
            serverSocket = new ServerSocket(porta);
            System.out.println("Il server attende richieste");

        } catch (IOException e){
            // Fallisce se la porta è già occupata da un altro programma
            System.err.println("Errore creazione del serverSocket");
        }
    }

    /**
     * Blocca l'esecuzione finché un client non si connette
     */
    public Socket attendi(){
        try{
            // accept() è un metodo bloccante: il server "dorme" finché non bussa un client
            clientSocket = serverSocket.accept();
            System.out.println("Il server ha accettato la richiesta del client");
            return clientSocket;
        } catch (IOException e){
            System.err.println("errore nell'attesa");
        }
        return null;
    }

    /**
     * Invia dati al client trasmutando la stringa in byte
     */
    public void scrivi(){
        try{
            // Scrive direttamente nello stream di uscita
            // getBytes() converte la stringa "Ciao" in un array di byte per la rete
            clientSocket.getOutputStream().write("Ciao\n".getBytes()); 
            // flush() assicura che i byte escano dal buffer e vadano sul cavo
            clientSocket.getOutputStream().flush();
        } catch(IOException e){
            System.err.println("Impossibile scrivere al client");
        }
    }

    /**
     * Riceve e stampa i dati inviati dal client
     */
    public void leggi(){
        try {
            // Prepara gli strumenti per leggere il flusso di byte in ingresso
            InputStream inputstream = clientSocket.getInputStream();
            // BufferedReader permette di leggere intere righe di testo (fino al \n)
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
            
            // Legge la stringa inviata dal client (es: "Hello world")
            String testo = reader.readLine();
            System.out.println("Client: " + testo);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossibile leggere il client");
        }
    }

    /**
     * Chiude la connessione con il singolo client
     */
    public void chiudi(){
        try {
            if (clientSocket != null) clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Spegne definitivamente il server liberando la porta
     */
    public void termina(){
        try {
            if (serverSocket != null) serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}