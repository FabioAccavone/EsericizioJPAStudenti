package accenture.com.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "libri")
public class Libro {
    
    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String titolo;

    @Column(name = "anno_pubblicazione")
    private int annoPubblicazione;

    @Column(unique = true)
    private String isbn;

    @ManyToOne
    @JoinColumn(name = "autore_id")
    private Autore autore; 

    public Libro() {
    }

    public Libro(String titolo, int annoPubblicazione, String isbn) {
        this.titolo = titolo;
        this.annoPubblicazione = annoPubblicazione;
        this.isbn = isbn;
    }

    public long getId() {
        return id;
    }

    public String getTitolo() {
        return titolo;
    }

    public int getAnnoPubblicazione() {
        return annoPubblicazione;
    }

    public String getIsbn() {
        return isbn;
    }

    public Autore getAutore() {
        return autore;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setAnnoPubblicazione(int annoPubblicazione) {
        this.annoPubblicazione = annoPubblicazione;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAutore(Autore autore) {
        this.autore = autore;
    }

    @Override
    public String toString() {
        return "\nid Libro: " + id + ", titolo: " + titolo + ", anno pubblicazione: " + annoPubblicazione + ", isbn: " + isbn
                + ", autore: " + autore;
    }
    
}
