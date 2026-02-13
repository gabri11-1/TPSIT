import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {
    private Socket socket;
    private String nome;
    private String colore;

    public Client(String nome){
        this.nome = nome;
    }
    public Client(String nome, String colore){
        this.nome = nome;
        this.colore = colore;
    }

    public void connetti(String nomeServer, int portaServer){
        try {
            socket = new Socket(nomeServer,portaServer);
            System.out.println("Il client " + nome + " si è connesso con il server");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void scrivi(){
        try {
            OutputStream outputstream = socket.getOutputStream();
            PrintWriter printer = new PrintWriter(outputstream);
            printer.print("Hello world\n");
            printer.flush();
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossibile scrivere al server");
        }
    }

    public void leggi(){
        try {
            InputStream inputStream = socket.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String text = reader.readLine();
            System.out.println("Server: "+ text);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Impossibile leggere dal server");
        }
        
    }

    public void chiudi(){
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

