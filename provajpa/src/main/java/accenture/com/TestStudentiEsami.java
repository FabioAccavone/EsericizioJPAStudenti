package accenture.com;

import accenture.com.dao.EsameDAO;
import accenture.com.dao.StudenteDAO;
import accenture.com.entity.Esame;
import accenture.com.entity.Studente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;
import java.util.Scanner;

public class TestStudentiEsami {

    public static void main(String[] args) {

        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("jpaDemo");

        try (EntityManager em = emf.createEntityManager();
             Scanner sc = new Scanner(System.in)) {

            StudenteDAO studenteDAO = new StudenteDAO(em);
            EsameDAO esameDAO = new EsameDAO(em);

            int scelta;

            do {
                System.out.println("\n========= MENU =========");
                System.out.println("1. Crea Studente");
                System.out.println("2. Lista Studenti");
                System.out.println("3. Trova Studente per ID");
                System.out.println("4. Elimina Studente");

                System.out.println();

                System.out.println("5. Aggiungi Esame a Studente");
                System.out.println("6. Lista Esami");
                System.out.println("7. Esami di uno Studente");
                System.out.println("8. Elimina Esame");

                System.out.println("0. Esci");

                scelta = sc.nextInt();
                sc.nextLine();

                switch (scelta) {

                    // ================= STUDENTE =================

                    case 1 -> {
                        System.out.print("Nome: ");
                        String nome = sc.nextLine();

                        System.out.print("Cognome: ");
                        String cognome = sc.nextLine();

                        Studente s = new Studente(nome, cognome);
                        studenteDAO.save(s);

                        System.out.println("Studente creato.");
                    }

                    case 2 -> {
                        studenteDAO.findAll()
                                .forEach(System.out::println);
                    }

                    case 3 -> {
                        System.out.print("ID Studente: ");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        Studente s = studenteDAO.findById(id);

                        if (s != null) {
                            System.out.println(s);
                        }
                    }

                    case 4 -> {
                        System.out.print("ID Studente: ");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        studenteDAO.delete(id);

                        System.out.println("Studente eliminato.");
                    }

                    // ================= ESAMI =================

                    case 5 -> {
                        System.out.print("ID Studente: ");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        Studente s = studenteDAO.findById(id);

                        if (s != null) {

                            System.out.print("Materia: ");
                            String materia = sc.nextLine();

                            System.out.print("Voto: ");
                            int voto = sc.nextInt();
                            sc.nextLine();

                            Esame e = new Esame(materia, voto);

                            e.setStudente(s);
                            // relazione bidirezionale corretta
                            s.addEsame(e);

                            esameDAO.update(e);

                            System.out.println("Esame aggiunto.");
                        } else {
                            System.out.println("Studente non trovato.");
                        }
                    }

                    case 6 -> {
                        esameDAO.findAll()
                                .forEach(System.out::println);
                    }

                    case 7 -> {
                        System.out.print("ID Studente: ");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        Studente s = studenteDAO.findById(id);
                        System.out.println(s.getEsami());
                    }

                    case 8 -> {
                        System.out.print("ID Esame: ");
                        Long id = sc.nextLong();
                        sc.nextLine();

                        esameDAO.delete(id);

                        System.out.println("Esame eliminato.");
                    }
                }

            } while (scelta != 0);

        } finally {
            emf.close();
        }
    }
}