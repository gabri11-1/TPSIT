public class Prova2 {
    public static void main(String[] args) {
       int a = 10;
        int b = 0;
        int c = a / b;
        System.out.println(c);
        
        try{
            c = a/b;
            System.out.println("questo testo non verrà stampato");
        } catch (ArithmeticException e){
            System.out.println("divisione per 0");
        }
}
}



/**Exception in thread "main" java.lang.ArithmeticException: / by zero
        at Prova2.main(Prova2.java:5)
PS C:\Users\studente\Desktop\TPSIT\prove_codice_05_12_25> 


È importante fare alcune osservazioni:
una dichiarazione try e le corrispondente catch formano un'unità;
l'ambito della dichiarazione catch è limitato alle dichiarazioni specificate dalla dichiarazione try immediatamente precedente;
se in un blocco try troviamo più istruzioni, quelle successive all'eccezione non verranno mai eseguite;
i blocchi catch possono essere anche multipli in funzione del tipo di eccezione;
in alcuni casi si usa anche la direttiva finally;
in alcuni casi è più efficiente usare il costrutto try-with-resources



*/