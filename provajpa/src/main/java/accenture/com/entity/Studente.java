package accenture.com.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "studenti")
public class Studente {

    @Id
    @GeneratedValue
    private Long matricola;

    private String nome;
    private String cognome;

    @OneToMany(mappedBy = "studente")
    private List<Esame> esami = new ArrayList<>();

    public Studente() {}

    public Studente(String nome, String cognome) {
        this.nome = nome;
        this.cognome = cognome;
    }

    // helper per relazione bidirezionale
    public void addEsame(Esame esame) {
        esami.add(esame);
        esame.setStudente(this);
    }

    public void removeEsame(Esame esame) {
        esami.remove(esame);
        esame.setStudente(null);
    }

    // getter e setter
    public Long getMatricola() { return matricola; }

    public String getNome() { return nome; }

    public void setNome(String nome) { this.nome = nome; }

    public String getCognome() { return cognome; }

    public void setCognome(String cognome) { this.cognome = cognome; }

    public List<Esame> getEsami() { return esami; }

    @Override
    public String toString() {
        return  "matricola: " + matricola +
                ", nome: '" + nome + '\'' +
                ", cognome: '" + cognome + '\'';
    }
}