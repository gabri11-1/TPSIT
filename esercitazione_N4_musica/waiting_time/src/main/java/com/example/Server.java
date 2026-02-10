package com.example;

public class Server {

    private boolean dragonAlive = true;

    public String handleRequest(String request) {

        switch (request.toUpperCase()) {
            case "LOOK":
                return "Ti guardi intorno. Il drago è lontano.";
            case "ATTACK":
                if (dragonAlive) {
                    dragonAlive = false;
                    return "Hai sconfitto il drago! Hai vinto!";
                } else {
                    return "Il drago è già morto.";
                }
            case "QUIT":
                return "Gioco terminato.";
            default:
                return "Comando non riconosciuto.";
        }
    }




    public void leggi(){
        try{
            InputStream InputStream = clientSocket.getInputStream();
            bufferedReader bufferedReader = new BufferedReader(new InputStreamReader(InputStream));
            String request = bufferedReader.readLine();
            String testo = br.readLine();
            System.out.println("CLIENT: " + testo);
            }catch (IOException e){
                e.printStackTrace();

            }
    }
}
