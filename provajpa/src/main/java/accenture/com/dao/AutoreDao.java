package accenture.com.dao;

import java.util.List;

import accenture.com.entity.Autore;
import jakarta.persistence.EntityManager;

public class AutoreDao {
    
    private EntityManager em;

    public AutoreDao(EntityManager em){
        this.em = em;
    }

    public void save(Autore autore){
        em.getTransaction().begin();
        em.persist(autore);
        em.getTransaction().commit();
    }

    public Autore findById(Long id){
        Autore a = em.find(Autore.class, id);

        if(a != null){
            return a;
        }
        else{
            System.out.println("Autore non trovato!!");
            return null;
        }
    }

    public List<Autore> findAll(){
        return em.createQuery("SELECT a FROM Autore a", Autore.class).getResultList();
    }

    public void update(Autore autore){
        Autore a = em.find(Autore.class, autore.getId());

        if(a != null){
            em.merge(autore);
        }
        else{
            System.out.println("Autore non trovato!!");
        }
    }

    public void delete(Long id){
        em.getTransaction().begin();
        Autore a = em.find(Autore.class, id);
        if (a != null) em.remove(a);
        em.getTransaction().commit();       
    }
}
