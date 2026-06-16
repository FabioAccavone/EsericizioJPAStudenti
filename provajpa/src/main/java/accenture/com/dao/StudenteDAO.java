package accenture.com.dao;

import accenture.com.entity.Esame;
import accenture.com.entity.Studente;
import jakarta.persistence.EntityManager;

import java.util.List;

public class StudenteDAO {

    private EntityManager em;

    public StudenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Studente s) {
        em.getTransaction().begin();
        em.persist(s);
        em.getTransaction().commit();
    }

    public Studente findById(Long id) {
        return em.find(Studente.class, id);
    }

    public List<Studente> findAll() {
        return em.createQuery("SELECT s FROM Studente s", Studente.class)
                .getResultList();
    }


    public void update(Studente s) {
        em.getTransaction().begin();
        em.merge(s);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Studente s = em.find(Studente.class, id);
        if (s != null) em.remove(s);
        em.getTransaction().commit();
    }
}