import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Rappresenta un'auto che partecipa alla gara.
 * <p>
 * Ogni auto ha un identificativo, una velocità massima, una password casuale e una parola chiave casuale
 * utilizzate per generare e salvare una password cifrata su file.
 * </p>
 * Implementa l'interfaccia {@link Runnable} per poter essere eseguita su un thread separato.
 */
public class Car implements Runnable {
    private final int id;
    private final String password;
    private final String keyword;
    private final int speed;
    private final GameManager gameManager;
    private float position;

    private final Random rand = new Random();

    /**
     * Crea una nuova auto con identificativo e velocità specificata.
     * Salva le credenziali cifrate con vigenere su file
     * 
     * @param id identificativo univoco dell'auto
     * @param speed velocità massima dell'auto
     */
    public Car(int id, int speed, GameManager gameManager) {
        this.id = id;
        this.speed = speed;
        this.gameManager = gameManager;

        password = randomString(16);
        keyword = randomString(16);
        position = 0;

        savePasswordToFile();
    }

    /**
     * Restituisce l'identificativo dell'auto.
     * 
     * @return id dell'auto
     */
    public int getId() {
        return id;
    }

    /**
     * Simula la velocita della macchina fino al traguardo, una volta finita va in classifica
     */

    @Override
    public void run() {
        while (position < gameManager.getPathLength()) {
            step();  
        }
        gameManager.addToClassification(this);
    }

    /**
     * Simula un passo dell'auto, incrementando casualmente la posizione
     * in base alla velocità e attendendo un secondo (velocità in m/s).
     */
    private void step() {
        position += rand.nextInt(speed) + 1;

        try {
            Thread.sleep(1000);
        } 
        catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    /**
     * Genera una stringa casuale.
     * 
     * @param length lunghezza della stringa
     * @return stringa casuale generata
     */
    private String randomString(int length) {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < length; i++)
            result.append(characters.charAt(rand.nextInt(26)));

        return result.toString();
    }

    /**
     * Cifra un testo con una parola chiave, usando vignere.
     * 
     * @param plaintext testo da cifrare
     * @param keyword parola chiave
     * @return testo cifrato
     */
    private String encrypt(String plaintext, String keyword) {
        StringBuilder ciphertext = new StringBuilder();
        for (int i = 0; i < plaintext.length(); i++) {
            ciphertext.append((char) ((plaintext.charAt(i) + keyword.charAt(i)) % 256));
        }
        return ciphertext.toString();
    }

    /**
     * Salva la password cifrata dell'auto in un file di testo.
     * Ogni riga contiene l'id dell'auto e la password cifrata.
     */
    private void savePasswordToFile() {
        String encryptedPassword = encrypt(password, keyword);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter("passwords.txt", true))) {
            bw.write("Car " + id + ": " + encryptedPassword);
            bw.newLine();
        } 
        catch (IOException e) {
            System.err.println(e);
        }
    }
}