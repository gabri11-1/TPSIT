import java.net.*;
import java.util.Scanner;

public class Server {

    private String groupAddress;
    private int port;

    public Server(String groupAddress, int port) {
        this.groupAddress = groupAddress;
        this.port = port;
    }

    public void start() {

        try {

            MulticastSocket socket = new MulticastSocket();
            InetAddress group = InetAddress.getByName(groupAddress);

            Scanner scanner = new Scanner(System.in);

            System.out.println("Server multicast avviato...");

            while (true) {

                System.out.print("Inserisci messaggio: ");
                String message = scanner.nextLine();

                byte[] buffer = message.getBytes();

                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length, group, port);

                socket.send(packet);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}