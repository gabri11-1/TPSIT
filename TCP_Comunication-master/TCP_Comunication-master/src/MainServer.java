//import java.io.IOException;
//import java.net.Socket;

public class MainServer {
    public static void main(String[] args) {
        Server server = new Server(12345);
        server.attendi();

        // Ciclo infinito di ascolto/risposta
        while (true) {
            server.leggi(); // Il server resta qui finché il client non manda qualcosa
            server.scrivi(); // Risponde "Ciao" (o quello che vuoi)
        }
        // Nota: in un server reale aggiungeresti una condizione di uscita

        //prossimo step mettere server.attendi() 
        // dentro un ciclo infinito per accettare più client
        //  e poi gestire ogni client in un thread separato (multithreading)
    
    }
}