public class MainClient {

    public static void main(String[] args) {

        Client client = new Client("localhost", 9876);

        client.start();

    }
}