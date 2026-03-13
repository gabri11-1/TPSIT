public class MainServer {

    public static void main(String[] args) {

        Server server = new Server("230.0.0.1", 9876);

        server.start();

    }
}