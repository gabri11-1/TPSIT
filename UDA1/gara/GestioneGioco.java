package UDA1.gara;


public class GestioneGioco {

    public static double lunghezzaPista = 500; // metri
    private Auto[] auto;

    public GestioneGioco(int numAuto) {
        auto = new Auto[numAuto];
        for (int i = 0; i < numAuto; i++) {
            auto[i] = new Auto("Colore" + (i + 1), i + 1, 50 + Math.random() * 50, 20);
        }
    }

    public void startRace() {
        System.out.println("=== INIZIO GARA ===");
        for (Auto a : auto) {
            Thread t = new Thread(a);
            t.start();
        }
    }

    public static void main(String[] args) {
        GestioneGioco gioco = new GestioneGioco(3);
        gioco.startRace();

        // Attesa fine gara
        while (!Giudice.checkWin()) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }

        Giudice.printClassifica();
        System.out.println("=== FINE GARA ===");
    }
}
