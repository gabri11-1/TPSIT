package esercitazione_n_4;

// Classe che rappresenta un utente con id, nome ed email
public class User {

    // Campo id dell'utente
    private int id;
    // Campo nome dell'utente
    private String name;
    // Campo email dell'utente
    private String email;

    // COSTRUTTORE VUOTO
    // Obbligatorio per Jackson perché serve al framework per creare oggetti tramite
    // reflection
    public User() {
    }

    // GETTER & SETTER
    // Permettono di leggere e modificare i campi privati dell'oggetto

    // Restituisce l'id dell'utente
    public int getId() {
        return id;
    }

    // Imposta l'id dell'utente
    public void setId(int id) {
        this.id = id;
    }

    // Restituisce il nome dell'utente
    public String getName() {
        return name;
    }

    // Imposta il nome dell'utente
    public void setName(String name) {
        this.name = name;
    }

    // Restituisce l'email dell'utente
    public String getEmail() {
        return email;
    }

    // Imposta l'email dell'utente
    public void setEmail(String email) {
        this.email = email;
    }
}
