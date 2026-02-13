public class MainClient {
    public static void main(String[] args) {
        // 1. Istanza della classe Client: creiamo l'oggetto passando il nome identificativo
        Client client = new Client("Client-1");

        // 2. Connessione: proviamo a collegarci al server. 
        // "localhost" indica che il server gira sulla stessa macchina del client.
        // 12345 è la porta su cui il server deve essere in ascolto.
        client.connetti("localhost", 12345);

        // 3. Fase di Output: invia la stringa "Hello world" definita nel metodo scrivi()
        client.scrivi();

        // 4. Fase di Input: resta in attesa di una risposta dal server e la stampa a video
        client.leggi();
        
        client.chiudi();
    }
}