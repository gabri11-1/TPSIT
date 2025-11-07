package UDA1.gara;

/**
 * Classe che rappresenta un'auto che partecipa alla gara.
 * Ogni Auto è eseguita in un thread separato (implementa Runnable),
 * quindi più auto possono muoversi contemporaneamente.
 */
public class Auto implements Runnable {

    // ===========================
    // ATTRIBUTI
    // ===========================
    private String colore;        // Colore dell'auto
    private int id;               // Identificativo numerico
    private double velMax;        // Velocità massima (metri/secondo)
    private int litriBenzina;     // Carburante disponibile
    private double posizione = 0; // Distanza percorsa in metri

    // Stati della gara
    private boolean finita = false;   // True se ha raggiunto la fine
    private boolean ritirata = false; // True se si è ritirata
    private boolean incidente = false;// True se ha avuto incidente

    // Modificatore di velocità (per favorire o sfavorire)
    private double bonusVelocita = 1.0;

    /**
     * Costruttore.
     * @param colore colore dell'auto
     * @param id identificativo
     * @param velMax velocità massima
     * @param litriBenzina carburante iniziale
     */
    public Auto(String colore, int id, double velMax, int litriBenzina) {
        this.colore = colore;
        this.id = id;
        this.velMax = velMax;
        this.litriBenzina = litriBenzina;
    }

    /**
     * Metodo principale eseguito dal thread.
     * Finché l’auto non termina, non si ritira e non finisce la pista, avanza passo per passo.
     */
    @Override
    public void run() {
        System.out.println("🚗 Auto " + id + " (" + colore + ") è pronta!");

        // Ciclo di vita dell'auto durante la gara
        while (!finita && !ritirata && posizione < GestioneGioco.lunghezzaPista) {
            try {
                step(); // esegue un avanzamento
                Thread.sleep(1000); // attende 1 secondo (simula il tempo che passa)
            } catch (InterruptedException e) {
                System.out.println("⚠️ Auto " + id + " interrotta.");
                return;
            }
        }

        // Quando finisce il ciclo, se non si è ritirata o incidentata, segna il traguardo
        if (!ritirata && !incidente) {
            finita = true;
            System.out.println("🏁 Auto " + id + " ha terminato la gara!");
            Giudice.registraArrivo(this);
        }
    }

    /**
     * Avanza di un passo (ogni secondo).
     * Calcola un avanzamento casuale basato sulla velocità e riduce il carburante.
     */
    public void step() {
        if (litriBenzina <= 0) {
            System.out.println("⛽ Auto " + id + " ha terminato la benzina!");
            ritirata = true;
            return;
        }
        if (incidente) {
            System.out.println("🚨 Auto " + id + " ferma per incidente!");
            return;
        }

        // Avanzamento proporzionale alla velocità e al bonus (favorito/sfavorito)
        double avanzamento = velMax * bonusVelocita * Math.random();
        posizione += avanzamento;
        litriBenzina--;
        System.out.printf("Auto %d -> %.2f m (benzina: %d)%n", id, posizione, litriBenzina);
    }

    /**
     * Simula la cifratura della password del pilota.
     * In un vero gioco qui si leggerebbe la password da file.
     */
    public void encrypt() {
        String sample = "passwordAuto" + id;
        String key = "GARA";
        String enc = PasswordManager.encryptVigenere(sample, key);
        System.out.println("🔒 Password Auto " + id + " cifrata: " + enc);
    }

    // ===========================
    // GESTIONE STATI E EVENTI
    // ===========================
    public void favorisci() { bonusVelocita = 1.5; }
    public void sfavorisci() { bonusVelocita = 0.5; }
    public void ritira() { ritirata = true; }
    public void incidente() { incidente = true; }
    public void riprendiDopoIncidente() { incidente = false; }

    // ===========================
    // GETTER
    // ===========================
    public int getId() { return id; }
    public String getColore() { return colore; }
    public double getPosizione() { return posizione; }
    public boolean isFinita() { return finita; }
    public boolean isRitirata() { return ritirata; }
}
