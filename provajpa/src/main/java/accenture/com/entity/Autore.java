package accenture.com.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/*
    3 QUERY CUSTOM
*/
@Entity
@Table(name = "autori")
public class Autore {
    
    @Id
    @GeneratedValue
    private long id;

    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cognome", nullable = false)
    private String cognome;

    @Column(name = "data_nascita")
    LocalDate dataNascita;

    @OneToMany(mappedBy = "autore")
    private List<Libro> libri = new ArrayList<>();

    public Autore() {
    }

    public Autore(String nome, String cognome, LocalDate dataNascita) {
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
    }

    public long getId() {
        return id;
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

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    

    @Override
    public String toString() {
        return "\nid Autore: " + id + ", nome: " + nome + " " + cognome + ", data nascita: " + dataNascita;
    }

    public List<Libro> getLibri() {
        return libri;
    }

    
}
