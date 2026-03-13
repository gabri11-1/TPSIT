import java.net.*;
import java.util.Scanner;

public class Client {

    private String host;
    private int port;

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() {

        try {

            DatagramSocket socket = new DatagramSocket();
            socket.setSoTimeout(5000);

            InetAddress serverAddress = InetAddress.getByName(host);

            Scanner scanner = new Scanner(System.in);

            System.out.print("Inserisci messaggio: ");
            String message = scanner.nextLine();

            byte[] sendData = message.getBytes();

            DatagramPacket sendPacket =
                    new DatagramPacket(sendData, sendData.length, serverAddress, port);

            socket.send(sendPacket);

            byte[] receiveData = new byte[1024];

            DatagramPacket receivePacket =
                    new DatagramPacket(receiveData, receiveData.length);

            socket.receive(receivePacket);

            String response =
                    new String(receivePacket.getData(),0,receivePacket.getLength());

            System.out.println("Risposta dal server: " + response);

            socket.close();

        }
        catch(SocketTimeoutException e){
            System.out.println("Il server non risponde.");
        }
        catch(UnknownHostException e){
            System.out.println("Host non trovato.");
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}