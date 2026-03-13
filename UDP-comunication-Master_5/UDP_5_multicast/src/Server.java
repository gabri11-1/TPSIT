import java.net.*;

public class Server {

    private int port;

    public Server(int port){
        this.port = port;
    }

    public void start(){

        try{

            DatagramSocket serverSocket = new DatagramSocket(port);

            System.out.println("Server avviato sulla porta " + port);

            byte[] receiveData = new byte[1024];

            while(true){

                DatagramPacket receivePacket =
                        new DatagramPacket(receiveData, receiveData.length);

                serverSocket.receive(receivePacket);

                String message =
                        new String(receivePacket.getData(),0,receivePacket.getLength());

                System.out.println("Messaggio ricevuto: " + message);

                String response = "Server ha ricevuto: " + message.toUpperCase();

                byte[] sendData = response.getBytes();

                InetAddress clientAddress = receivePacket.getAddress();
                int clientPort = receivePacket.getPort();

                DatagramPacket sendPacket =
                        new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);

                serverSocket.send(sendPacket);

            }

        }
        catch(BindException e){
            System.out.println("Errore: server già avviato sulla porta.");
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}