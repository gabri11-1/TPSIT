import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Gestisce la simulazione della gara, la classifica e la scrittura su file.
 */
public class GameManager {
    private final List<Car> classification; // Lista sincronizzata delle auto arrivate
    private final int pathLength; // Lunghezza del percorso
    private final int totalCars; // Numero totale di auto

    /**
     * Costruttore: crea le auto e le avvia come thread
     */
    public GameManager(int pathLength, int carsNumber) {
        this.pathLength = pathLength;
        this.totalCars = carsNumber;

        // Lista sincronizzata per gestire più thread
        classification = Collections.synchronizedList(new ArrayList<>());

        Random rand = new Random();
        // Crea e avvia le auto
        for (int i = 0; i < carsNumber; i++) {
            new Thread(new Car(i + 1, rand.nextInt(5) + 5, this)).start();
        }
    }

    /**
     * Aggiunge un'auto alla classifica e, se tutte hanno finito, salva su file
     */
    public synchronized void addToClassification(Car car) {
        classification.add(car);
        System.out.println("Auto " + car.getId() + " ha tagliato il traguardo!");

        // Se tutte le auto hanno finito, stampa e salva la classifica
        if (classification.size() == totalCars) {
            System.out.println("\nClassifica finale:");
            int pos = 1;
            for (Car c : classification) {
                System.out.println(pos + "° posto: Auto " + c.getId());
                pos++;
            }
            FileManager.saveClassification(classification, "classifica.txt");
        }
    }

    public int getPathLength() {
        return pathLength;
    }
}
