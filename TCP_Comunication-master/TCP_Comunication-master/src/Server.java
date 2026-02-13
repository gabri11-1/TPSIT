import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private int port;


    public Server(int port){
        this.port = port;
        try{
            serverSocket = new ServerSocket(port);
            System.out.println("Il server attende richieste");

        } catch (IOException e){
            System.err.println("Errore creazione serverSocket");
        }
    }


    public Socket attendi(){
        try{
            clientSocket = serverSocket.accept();
            System.out.println("Il server ha accettato la richiesta del client");
            return clientSocket;
        } catch (IOException e){
            System.err.println("errore nell'attesa");
        }
        return null;
    }

    public void scrivi(){
        try{
            clientSocket.getOutputStream().write("Ciao".getBytes());
            clientSocket.getOutputStream().flush();
        } catch(IOException e){
            System.err.println("Impossibile scrivere al client");
        }
    }

    public void leggi(){
        try {
            InputStream inputstream = clientSocket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream));
            String text = reader.readLine();
            System.out.println("Client: " + text);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossibile leggere il client");
        }
    }

    public void chiudi(){
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void termina(){
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}