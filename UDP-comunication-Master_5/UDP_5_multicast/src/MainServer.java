public class MainServer {

    public static void main(String[] args) {

        Server server = new Server(9876);

        server.start();

    }
}