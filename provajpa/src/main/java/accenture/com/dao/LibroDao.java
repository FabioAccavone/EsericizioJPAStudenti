package accenture.com.dao;

import java.util.List;

import accenture.com.entity.Libro;
import jakarta.persistence.EntityManager;

public class LibroDao {

    private EntityManager em;

    public LibroDao(EntityManager em) {
        this.em = em;
    }

    //CREATE
    public void save(Libro libro){
        em.getTransaction().begin();
        em.persist(libro);
        em.getTransaction().commit();
    }

    //READ
    public Libro findById(Long id){
        Libro l = em.find(Libro.class, id);

        if (l != null) {
            return l;
        }
        else{
            System.out.println("Libro non esiste!!");
            return null;
        }
    }

    //READ ALL
    public List<Libro> findAll(){
        return em.createQuery("SELECT l FROM Libro l", Libro.class)
                 .getResultList();                      
    }

    //UPDATE
    public void update(Libro libro){
        Libro l = em.find(Libro.class, libro.getId());

        if (l != null) {
            em.merge(libro);
        }
        else{
            System.out.println("Libro" + libro.getTitolo() + " non esiste!!");
        }
    }

    //DELETE
    public void delete(Long id){
        em.getTransaction().begin();
        Libro l = em.find(Libro.class, id);
        if (l != null) em.remove(l);
        em.getTransaction().commit();
    }

    public List<Libro> findByAutore(String autore) {

        return em.createQuery(
                "SELECT l FROM Libro l WHERE l.autore = :autore",
                Libro.class)
                .setParameter("autore", autore)
                .getResultList();
    }
}
