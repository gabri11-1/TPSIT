import java.io.IOException;
import java.net.Socket;

public class MainServer {
    public static void main(String[] args) throws IOException {
        int porta = 12345;
        Server server = new Server(porta);
        server.attendi();
        System.out.println("Server connesso al client");
        server.scrivi();
        server.leggi();
        server.chiudi();
    }
}