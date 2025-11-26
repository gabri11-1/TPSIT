import java.util.Random;

/**
 * Rappresenta un'auto partecipante alla gara.
 * Implementa Runnable per poter essere eseguita come thread indipendente.
 */
public class Car implements Runnable {
    private final int id; // Identificativo dell’auto
    private final int speed; // Velocità massima
    private float position; // Posizione attuale sul percorso
    private final GameManager gameManager;
    private final Random rand = new Random();

    /**
     * Costruttore: imposta ID, velocità e riferimento al GameManager
     */
    public Car(int id, int speed, GameManager gameManager) {
        this.id = id;
        this.speed = speed;
        this.position = 0;
        this.gameManager = gameManager;
    }

    public int getId() {
        return id;
    }

    /**
     * Metodo eseguito dal thread.
     * Finché la macchina non raggiunge il traguardo, avanza.
     * Quando finisce chiama GameManager per entrare in classifica.
     */
    @Override
    public void run() {
        while (position < gameManager.getPathLength()) {
            step();
        }
        gameManager.addToClassification(this);
    }

    /**
     * Avanza la macchina di un numero casuale tra 1 e speed
     * Poi attende un secondo per simulare il tempo reale.
     */
    private void step() {
        position += rand.nextInt(speed) + 1;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }
}
