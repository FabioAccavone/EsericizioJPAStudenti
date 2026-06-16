package accenture.com.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "profili")
public class Profilo {
    
    @Id
    @GeneratedValue
    private long id;

    private String nome;
    private String cognome;
    private String telefono;

    @OneToOne
    @JoinColumn(name = "utente_id")
    private Utente utente;

    public Profilo() {
    }

    public Profilo(long id, String nome, String cognome, String telefono, Utente utente) {
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.telefono = telefono;
        this.utente = utente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    @Override
    public String toString() {
        return "id Profilo: " + id + ", nome: " + nome + " " + cognome + ", telefono: " + telefono + "\n    utente: "
                + utente;
    }

    

}
