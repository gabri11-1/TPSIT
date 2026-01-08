package esercitazione_n_4;

// Importa ObjectMapper di Jackson per convertire oggetti Java in JSON e viceversa
import com.fasterxml.jackson.databind.ObjectMapper;
// Importa la classe File per rappresentare file sul filesystem
import java.io.File;

public class WriteJsonExample {

    public static void main(String[] args) throws Exception {

        // Crea un'istanza di ObjectMapper, l'oggetto principale per lavorare con JSON
        ObjectMapper mapper = new ObjectMapper();

        // Crea un nuovo oggetto User
        User user = new User();
        // Imposta l'ID dell'utente
        user.setId(2);
        // Imposta il nome dell'utente
        user.setName("Luigi");
        // Imposta l'email dell'utente
        user.setEmail("luigi@email.it");

        // Scrive l'oggetto User nel file JSON
        // writerWithDefaultPrettyPrinter() serve a formattare il JSON in modo leggibile
        // (indentato)
        // writeValue() scrive su "user.json" nella cartella corrente del progetto
        mapper.writerWithDefaultPrettyPrinter()
                .writeValue(new File("user.json"), user);
    }
}
