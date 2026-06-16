package accenture.com.dao;

import java.util.List;

import accenture.com.entity.Utente;
import jakarta.persistence.EntityManager;

public class UtenteDAO {
    
    EntityManager em;

    public UtenteDAO(EntityManager em) {
        this.em = em;
    }

    public void save(Utente utente){
        em.getTransaction().begin();
        em.persist(utente);
        em.getTransaction().commit();
    }

    public void update(Utente utente){
        em.getTransaction().begin();
        Utente u = em.find(Utente.class, utente.getId());

        if(u != null){
            em.merge(utente);
        }
        else{
            System.out.println("Utente non trovato!!");
        }
        em.getTransaction().commit();
    }

    public Utente findById(Long id){
        Utente u = em.find(Utente.class, id);

        if(u != null){
            return u;
        }
        else{
            System.out.println("Utente non trovato!!");
            return null;
        }
    }

    public List<Utente> findAll(){
        return em.createQuery(
            "SELECT u FROM Utente u", Utente.class
        ).getResultList();
    }

public void delete(Long id){
    em.getTransaction().begin();

    Utente u = em.find(Utente.class, id);

    if (u != null) {

        // 🔥 STACCA RELAZIONE BIDIREZIONALE
        if (u.getProfilo() != null) {
            u.getProfilo().setUtente(null);
            u.setProfilo(null);
        }

        em.remove(u);
    }

    em.getTransaction().commit();
}

}
