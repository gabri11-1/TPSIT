import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Gestisce la simulazione della gara, creando le auto e tenendo traccia della classifica.
 * <p>
 * Ogni automibile diventa un thread, che parte e viene pushata sulla
 * classifica una volta tagliato il traguardo.
 * </p>
 */
public class GameManager {
    //  classification = new CopyOnWriteArrayList<>(); Con questo posso togliere il syncronized 
    private final List<Car> classification;
    private final int pathLength;

    /**
     * Crea una nuova istanza del GameManager e avvia la gara.
     *
     * @param pathLength lunghezza del percorso
     * @param carsNumber numero di auto partecipanti
     */
    public GameManager(int pathLength, int carsNumber) {
        this.pathLength = pathLength;

        // Struttura dati che mi permette di creare una lista di classificati senza dover fare utto il metodo synchro sull'add
        classification = Collections.synchronizedList(new ArrayList<>());
        
        Random rand = new Random();
        for (int i = 0; i < carsNumber; i++)
            new Thread(new Car(i + 1, rand.nextInt(5) + 5, this)).start();
    }

    /**
     * Aggiunge un'auto alla classifica una volta che ha terminato la gara.
     * Il metodo è sincronizzato per evitare che due o piu thread acquisiscano la risorsa in contemporanea.
     *
     * @param car l'auto che ha concluso la gara
     */
    public void addToClassification(Car car) {
        System.out.println("La macchina " + car.getId() + " ha tagliato il traguardo!");
        
        classification.add(car);
        printClassification();
    }

    /**
     * Stampa la classifica delle auto ogni volta che una nuova auto raggiunge il traguardo
     */
    private void printClassification() {
        System.out.println("Classifica attuale:");

        int pos = 0;

        synchronized (classification) {
            for (Car c : classification) 
                System.out.println(++pos + " posto: Auto " + c.getId()); 
        }
        
        System.out.println();
    }

    /**
     * Restituisce la lunghezza del percorso.
     * 
     * @return lunghezza del percorso
     */
    public int getPathLength() {
        return pathLength;
    }
}