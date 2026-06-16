package accenture.com.dao;

import accenture.com.entity.Esame;
import jakarta.persistence.EntityManager;

import java.util.List;

public class EsameDAO {

    private EntityManager em;

    public EsameDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Esame e) {
        em.getTransaction().begin();
        em.persist(e);
        em.getTransaction().commit();
    }

    public Esame findById(Long id) {
        return em.find(Esame.class, id);
    }

    public List<Esame> findAll() {
        return em.createQuery("SELECT e FROM Esame e", Esame.class)
                .getResultList();
    }

    public void update(Esame e) {
        em.getTransaction().begin();
        em.merge(e);
        em.getTransaction().commit();
    }

    public void delete(Long id) {
        em.getTransaction().begin();
        Esame e = em.find(Esame.class, id);
        if (e != null) em.remove(e);
        em.getTransaction().commit();
    }
}