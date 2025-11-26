public class Giocatore extends Thread{
    // attributi
    int punteggio = 0;
    String nome;
    // metodi
    public Giocatore(String nome){
        this.punteggio = 0;
        this.nome=nome;


    }
    public void comunica(){
        System.out.println("sono il giocatore " + nome + (" e ho iniziato il gioco "));

        System.out.println(this);
    }

    @Override
    public void run(){
        comunica();
    }
}
