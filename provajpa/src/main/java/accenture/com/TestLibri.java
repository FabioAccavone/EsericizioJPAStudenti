package accenture.com;

import java.time.LocalDate;
import java.util.Scanner;

import accenture.com.dao.AutoreDao;
import accenture.com.dao.LibroDao;
import accenture.com.entity.Autore;
import accenture.com.entity.Libro;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class TestLibri {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaDemo");

        try (EntityManager em = emf.createEntityManager();
             Scanner sc = new Scanner(System.in)) {

            LibroDao libroDao = new LibroDao(em);
            AutoreDao autoreDao = new AutoreDao(em);

            int scelta;

            do {
                System.out.println("\n===== MENU LIBRERIA =====");
                System.out.println("1. Crea Autore");
                System.out.println("2. Lista Autori");
                System.out.println("3. Cerca Autore per ID");
                System.out.println("4. Aggiorna Autore");
                System.out.println("5. Elimina Autore");

                System.out.println("6. Crea Libro");
                System.out.println("7. Lista Libri");
                System.out.println("8. Cerca Libro per ID");
                System.out.println("9. Aggiorna Libro");
                System.out.println("10. Elimina Libro");
                System.out.println("11. Recupera i libri di un autore");
                System.out.println("0. Esci");

                System.out.print("Scelta: ");

                while (!sc.hasNextInt()) {
                    System.out.println("Inserisci un numero valido!");
                    sc.next();
                }

                scelta = sc.nextInt();
                sc.nextLine();

                switch (scelta) {

                    // ================= AUTORE =================

                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Cognome: ");
                        String cognome = sc.nextLine();

                        System.out.print("Data nascita (YYYY-MM-DD): ");
                        LocalDate data = LocalDate.parse(sc.nextLine());

                        Autore a = new Autore();
                        a.setNome(nome);
                        a.setCognome(cognome);
                        a.setDataNascita(data);

                        autoreDao.save(a);
                    }

                    case 2 -> autoreDao.findAll().forEach(System.out::println);

                    case 3 -> {
                        System.out.print("ID autore: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        System.out.println(autoreDao.findById(id));
                    }

                    case 4 -> {
                        System.out.print("ID autore: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Autore a = autoreDao.findById(id);

                        if (a != null) {
                            System.out.print("Nuovo nome: ");
                            a.setNome(sc.nextLine());

                            System.out.print("Nuovo cognome: ");
                            a.setCognome(sc.nextLine());

                            autoreDao.update(a);
                        }
                    }

                    case 5 -> {
                        System.out.print("ID autore: ");
                        long id = sc.nextLong();

                        autoreDao.delete(id);
                    }

                    // ================= LIBRO =================

                    case 6 -> {
                        System.out.print("Titolo: ");
                        String titolo = sc.nextLine();

                        System.out.print("Anno pubblicazione: ");
                        int anno = sc.nextInt();
                        sc.nextLine();

                        System.out.print("ISBN: ");
                        String isbn = sc.nextLine();

                        System.out.print("ID Autore: ");
                        long idAutore = sc.nextLong();
                        sc.nextLine();

                        Autore a = autoreDao.findById(idAutore);

                        if (a != null) {

                            Libro l = new Libro();
                            l.setTitolo(titolo);
                            l.setAnnoPubblicazione(anno);
                            l.setIsbn(isbn);

                            // 🔥 relazione corretta
                            l.setAutore(a);

                            libroDao.save(l);

                            System.out.println("Libro creato correttamente.");
                        } else {
                            System.out.println("Autore non trovato.");
                        }
                    }

                    case 7 -> libroDao.findAll().forEach(System.out::println);

                    case 8 -> {
                        System.out.print("ID libro: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        System.out.println(libroDao.findById(id));
                    }

                    case 9 -> {
                        System.out.print("ID libro: ");
                        long id = sc.nextLong();
                        sc.nextLine();

                        Libro l = libroDao.findById(id);

                        if (l != null) {
                            System.out.print("Nuovo titolo: ");
                            l.setTitolo(sc.nextLine());

                            System.out.print("Nuovo anno: ");
                            l.setAnnoPubblicazione(sc.nextInt());
                            sc.nextLine();

                            System.out.print("Nuovo ISBN: ");
                            l.setIsbn(sc.nextLine());

                            libroDao.update(l);
                        }
                    }

                    case 10 -> {
                        System.out.print("ID libro: ");
                        long id = sc.nextLong();

                        libroDao.delete(id);
                    }

                    case 11 -> {
                        System.out.print("Id Autore: ");
                        long idAutore = sc.nextLong();
                        sc.nextLine();

                        Autore a = autoreDao.findById(idAutore);

                        System.out.println(a.getLibri());
                    }
                    case 0 -> System.out.println("Uscita...");
                    default -> System.out.println("Scelta non valida!");
                }

            } while (scelta != 0);

        } finally {
            emf.close();
        }
    }
}