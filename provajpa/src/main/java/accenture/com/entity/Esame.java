package accenture.com.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "esami")
public class Esame {

    @Id
    @GeneratedValue
    private Long id;

    private String materia;
    private int voto;

    @ManyToOne
    @JoinColumn(name = "studente_id")
    private Studente studente;

    public Esame() {}

    public Esame(String materia, int voto) {
        this.materia = materia;
        this.voto = voto;
    }

    public Long getId() { return id; }

    public String getMateria() { return materia; }

    public void setMateria(String materia) { this.materia = materia; }

    public int getVoto() { return voto; }

    public void setVoto(int voto) { this.voto = voto; }

    public Studente getStudente() { return studente; }

    public void setStudente(Studente studente) { this.studente = studente; }

    @Override
    public String toString() {
        return  "\nid: " + id +
                ", materia: '" + materia + '\'' +
                ", voto: " + voto;
    }
}