import java.io.IOException;
import java.net.Socket;

public class MainServer {
    // Il metodo main lancia IOException perché le operazioni di rete possono fallire
    public static void main(String[] args) throws IOException {
        
        // 1. Definiamo la porta: deve essere la stessa usata dal Client (12345)
        int porta = 12345;
        
        // 2. Istanza della classe Server: inizializza il ServerSocket sulla porta scelta
        Server server = new Server(porta);
        
        // 3. Fase di ascolto: il programma si ferma qui finché un client non si connette
        // (Metodo "accept" all'interno di attendi)
        server.attendi();
        
        // Una volta che attendi() ha finito, significa che il "handshake" è riuscito
        System.out.println("Server connesso al client");
        
        // 4. Fase di Output: il server invia un messaggio al client
        server.scrivi();
        
        // 5. Fase di Input: il server legge quello che il client ha inviato (es. "Hello world")
        server.leggi();
        
        // 6. Chiusura: rilascia la porta e chiude i flussi di dati
        server.chiudi();
    }
}