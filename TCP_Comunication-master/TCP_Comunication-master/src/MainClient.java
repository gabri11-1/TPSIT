public class MainClient {
    public static void main(String[] args) {
        Client client = new Client("Client1");
        client.connetti("localhost", 12345);
        client.scrivi();
        client.leggi();
    }
}
