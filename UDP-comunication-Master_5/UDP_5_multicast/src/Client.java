import java.net.*;

public class Client {

    private String groupAddress;
    private int port;

    public Client(String groupAddress, int port) {
        this.groupAddress = groupAddress;
        this.port = port;
    }

    public void start() {

        try {

            MulticastSocket socket = new MulticastSocket(port);
            InetAddress group = InetAddress.getByName(groupAddress);

            socket.joinGroup(group);

            System.out.println("Client in ascolto sul gruppo multicast...");

            byte[] buffer = new byte[1024];

            while (true) {

                DatagramPacket packet =
                        new DatagramPacket(buffer, buffer.length);

                socket.receive(packet);

                String message =
                        new String(packet.getData(), 0, packet.getLength());

                System.out.println("Messaggio ricevuto: " + message);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}