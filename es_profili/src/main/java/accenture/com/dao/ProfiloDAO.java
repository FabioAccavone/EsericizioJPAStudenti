package accenture.com.dao;

import java.util.List;

import accenture.com.entity.Profilo;
import jakarta.persistence.EntityManager;

public class ProfiloDAO {
    
    EntityManager em;

    public ProfiloDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Profilo profilo){

        em.getTransaction().begin();
        em.persist(profilo);
        em.getTransaction().commit();
    }

    public void update(Profilo profilo){
        em.getTransaction().begin();
        Profilo p = em.find(Profilo.class, profilo.getId());

        if(p != null){
            em.merge(profilo);
        }
        else{
            System.out.println("Profilo non trovato!!");
        }
        em.getTransaction().commit();
    }

    public Profilo findById(Long id){
        Profilo p = em.find(Profilo.class, id);

        if (p != null) {
            return p;
        }
        else{
            System.out.println("Profilo non trovato!!");
            return null;
        }
    }

    public List<Profilo> findAll(){
        return em.createQuery(
            "SELECT p FROM Profilo p ", Profilo.class)
            .getResultList();
    }

public void delete(Long id){

    em.getTransaction().begin();

    Profilo p = em.find(Profilo.class, id);

    if (p != null) {

        // 🔥 STACCA RELAZIONE BIDIREZIONALE
        if (p.getUtente() != null) {
            p.getUtente().setProfilo(null);
            p.setUtente(null);
        }

        em.remove(p);
    }

    em.getTransaction().commit();
}
}
