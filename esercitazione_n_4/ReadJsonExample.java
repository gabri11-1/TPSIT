package esercitazione_n_4;

// Importa ObjectMapper di Jackson per convertire JSON in oggetti Java e viceversa
import com.fasterxml.jackson.databind.ObjectMapper;
// Importa la classe File per rappresentare un file sul filesystem
import java.io.File;

public class ReadJsonExample {

    public static void main(String[] args) throws Exception {

        // Crea un'istanza di ObjectMapper, necessario per leggere JSON
        ObjectMapper mapper = new ObjectMapper();

        // Legge il file "user.json" e mappa il contenuto in un oggetto User
        // readValue() prende il file e la classe target dell'oggetto
        User user = mapper.readValue(
                new File("user.json"),
                User.class);

        // Stampa solo il nome dell'utente letto dal file JSON
        System.out.println(user.getName());
    }
}
