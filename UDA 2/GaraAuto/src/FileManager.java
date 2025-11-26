import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Gestisce la scrittura della classifica su file.
 */
public class FileManager {

    /**
     * Salva la classifica su un file di testo.
     *
     * @param classification lista di auto in ordine di arrivo
     * @param filename nome del file di output
     */
    public static void saveClassification(List<Car> classification, String filename) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            int pos = 1;
            // Scrive ogni auto in ordine di arrivo
            for (Car car : classification) {
                bw.write(pos + "° posto: Auto " + car.getId());
                bw.newLine();
                pos++;
            }
            System.out.println("Classifica salvata su file " + filename);
        } catch (IOException e) {
            System.err.println("Errore scrittura file: " + e.getMessage());
        }
    }
}
