public class MainClient {

    public static void main(String[] args) {

        Client client = new Client("230.0.0.1", 9876);

        client.start();

    }
}