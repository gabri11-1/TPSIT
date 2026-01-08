package esercitazione_n_4;

// Importa la classe ObjectMapper di Jackson per leggere e scrivere JSON
import com.fasterxml.jackson.databind.ObjectMapper;
// Importa la classe File per gestire file sul filesystem
import java.io.File;

public class Main {
    public static void main(String[] args) {
        try {
            // Crea un'istanza di ObjectMapper, il cuore di Jackson per il mapping JSON ↔
            // oggetto Java
            ObjectMapper mapper = new ObjectMapper();

            // 1️ Leggi il file JSON e crea l'oggetto User
            // Qui viene passato il percorso assoluto del file user.json
            // Jackson mapperà automaticamente il contenuto JSON nell'oggetto User
            User user = mapper.readValue(
                    new File("C:\\Users\\ranig\\Downloads\\scuola2025-2026\\TPSIT\\esercitazione_n_4\\user.json"),
                    User.class);

            // Stampa un messaggio informativo
            System.out.println("Utente letto dal file JSON:");
            // Stampa l'oggetto User letto dal file (richiama il toString() di User)
            System.out.println(user);

            // 2️ Modifica qualche campo dell'oggetto User
            // Cambia il nome da quello letto dal file a "Luigi"
            user.setName("Luigi");
            // Cambia l'email a "luigi@email.it"
            user.setEmail("luigi@email.it");

            // 3 Riscrivi l'oggetto modificato nel file JSON
            // Qui scrive su "user.json" nella cartella corrente del progetto
            // writerWithDefaultPrettyPrinter() serve a rendere il JSON leggibile e
            // indentato
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File("user.json"), user);

            // Messaggio di conferma sul successo dell'operazione
            System.out.println("\nFile JSON aggiornato con successo!");
        } catch (Exception e) {
            // Stampa lo stack trace in caso di errore (es. file non trovato, problemi di
            // lettura/scrittura)
            e.printStackTrace();
        }
    }
}
