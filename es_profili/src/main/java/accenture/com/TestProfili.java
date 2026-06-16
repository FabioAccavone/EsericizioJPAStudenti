package accenture.com;

import java.util.Scanner;

import accenture.com.dao.ProfiloDAO;
import accenture.com.dao.UtenteDAO;
import accenture.com.entity.Profilo;
import accenture.com.entity.Utente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestProfili {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaDemo");

        try (
            EntityManager em = emf.createEntityManager();
            Scanner sc = new Scanner(System.in)
        ) {

            UtenteDAO utenteDAO = new UtenteDAO(em);
            ProfiloDAO profiloDAO = new ProfiloDAO(em);

            int scelta;

            do {

                System.out.println("\n========== MENU ==========");
                System.out.println("1. Inserisci Utente");
                System.out.println("2. Visualizza tutti gli Utenti");
                System.out.println("3. Cerca Utente per ID");
                System.out.println("4. Modifica Utente");
                System.out.println("5. Elimina Utente");

                System.out.println();

                System.out.println("6. Inserisci Profilo");
                System.out.println("7. Visualizza tutti i Profili");
                System.out.println("8. Cerca Profilo per ID");
                System.out.println("9. Modifica Profilo");
                System.out.println("10. Elimina Profilo");

                System.out.println();

                System.out.println("11. Visualizza Profilo di un Utente");
                System.out.println("12. Visualizza Utente associato ad un Profilo");

                System.out.println();
                System.out.println("0. Esci");

                System.out.print("\nScelta: ");
                scelta = sc.nextInt();
                sc.nextLine();

                switch (scelta) {

                    // ================= UTENTE =================

                    case 1 -> {

                        System.out.print("Username: ");
                        String username = sc.nextLine();

                        System.out.print("Email: ");
                        String email = sc.nextLine();

                        Utente u = new Utente();
                        u.setUsername(username);
                        u.setEmail(email);

                        utenteDAO.save(u);

                        System.out.println("Utente salvato correttamente.");
                    }

                    case 2 -> {
                        utenteDAO.findAll()
                                .forEach(System.out::println);
                    }

                    case 3 -> {

                        System.out.print("ID Utente: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Utente u = utenteDAO.findById(id);

                        if (u != null) {
                            System.out.println(u);
                        }
                    }

                    case 4 -> {

                        System.out.print("ID Utente da modificare: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Utente u = utenteDAO.findById(id);

                        if (u != null) {

                            System.out.print("Nuovo username: ");
                            u.setUsername(sc.nextLine());

                            System.out.print("Nuova email: ");
                            u.setEmail(sc.nextLine());

                            utenteDAO.update(u);

                            System.out.println("Utente aggiornato.");
                        }
                    }

                    case 5 -> {

                        System.out.print("ID Utente da eliminare: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        utenteDAO.delete(id);

                        System.out.println("Utente eliminato.");
                    }

                    // ================= PROFILO =================

                    case 6 -> {

                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Cognome: ");
                        String cognome = sc.nextLine();

                        System.out.print("Telefono: ");
                        String telefono = sc.nextLine();

                        System.out.print("ID Utente associato: ");
                        long idUtente = sc.nextLong();
                        sc.nextLine();

                        Utente u = utenteDAO.findById(idUtente);

                        if (u != null) {

                            Profilo p = new Profilo();
                            p.setNome(nome);
                            p.setCognome(cognome);
                            p.setTelefono(telefono);

                            // relazione bidirezionale
                            p.setUtente(u);
                            u.setProfilo(p);

                            profiloDAO.save(p);

                            System.out.println("Profilo salvato correttamente.");
                        }
                    }

                    case 7 -> {
                        profiloDAO.findAll()
                                .forEach(System.out::println);
                    }

                    case 8 -> {

                        System.out.print("ID Profilo: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Profilo p = profiloDAO.findById(id);

                        if (p != null) {
                            System.out.println(p);
                        }
                    }

                    case 9 -> {

                        System.out.print("ID Profilo da modificare: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Profilo p = profiloDAO.findById(id);

                        if (p != null) {

                            System.out.print("Nuovo nome: ");
                            p.setNome(sc.nextLine());

                            System.out.print("Nuovo cognome: ");
                            p.setCognome(sc.nextLine());

                            System.out.print("Nuovo telefono: ");
                            p.setTelefono(sc.nextLine());

                            profiloDAO.update(p);

                            System.out.println("Profilo aggiornato.");
                        }
                    }

                    case 10 -> {

                        System.out.print("ID Profilo da eliminare: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        profiloDAO.delete(id);

                        System.out.println("Profilo eliminato.");
                    }

                    // ================= RELAZIONE =================

                    case 11 -> {

                        System.out.print("ID Utente: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Utente u = utenteDAO.findById(id);

                        if (u != null) {

                            if (u.getProfilo() != null) {
                                System.out.println(u.getProfilo());
                            } else {
                                System.out.println("Nessun profilo associato.");
                            }
                        }
                    }

                    case 12 -> {

                        System.out.print("ID Profilo: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Profilo p = profiloDAO.findById(id);

                        if (p != null) {

                            if (p.getUtente() != null) {
                                System.out.println(p.getUtente());
                            } else {
                                System.out.println("Nessun utente associato.");
                            }
                        }
                    }

                    case 0 -> System.out.println("Programma terminato.");

                    default -> System.out.println("Scelta non valida.");
                }

            } while (scelta != 0);

        } finally {
            emf.close();
        }
    }
}