package UDA1.gara;


import java.util.*;

/**
 * Il Giudice di gara controlla la classifica e registra gli arrivi.
 * Tutte le auto chiamano Giudice.registraArrivo() quando tagliano il traguardo.
 */
public class Giudice {

    // Lista sincronizzata per evitare problemi tra thread
    private static List<Auto> classifica = Collections.synchronizedList(new ArrayList<>());

    /**
     * Registra un'auto arrivata al traguardo.
     * @param a auto da aggiungere alla classifica
     */
    public static synchronized void registraArrivo(Auto a) {
        if (!classifica.contains(a)) {
            classifica.add(a);
        }
    }

    /** Stampa la classifica finale dopo la fine della gara */
    public static void printClassifica() {
        System.out.println("\n===== CLASSIFICA FINALE =====");
        int pos = 1;
        for (Auto a : classifica) {
            System.out.println(pos + "° posto -> Auto " + a.getId() + " (" + a.getColore() + ")");
            pos++;
        }
    }

    /** Ritorna true se almeno un’auto ha concluso la gara */
    public static boolean checkWin() {
        return !classifica.isEmpty();
    }
}
