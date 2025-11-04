import java.util.Random;

public class Auto {

    private String colore;
    private String targa;
    private int posizione;

    public Auto(String colore, String targa, int posizione) {
        this.colore=colore;
        this.targa=targa;
        this.posizione=posizione;
    }

        @Override
    public void run() {
        System.out.println("start");
    }

    private int step() {
        Random rand = new Random();
        posizione += rand.nextInt(3) + 1;
    }

    public int getPosizione() {return posizione;}

    public String getTarga() { return targa; }
}
