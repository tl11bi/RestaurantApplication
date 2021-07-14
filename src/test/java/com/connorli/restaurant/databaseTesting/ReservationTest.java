package com.connorli.restaurant.databaseTesting;

import com.connorli.restaurant.domain.Reservation;
import com.connorli.restaurant.domain.RestTable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

class ReservationTest {
    static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ItemPU");
        em = emf.createEntityManager();
        createMultiple();
        //query();
    }

    private static void createMultiple() {
        em.getTransaction().begin();
        RestTable restTable = new RestTable("haha", 5);
        em.persist(restTable);
        System.out.println(restTable);
        Reservation reservation = new Reservation( "connor", "li", Timestamp.valueOf("2020-08-20 16:20:05"), 5, restTable);
        System.out.println(reservation);
        em.persist(reservation);

        em.getTransaction().commit();

        em.close();
    }

    private static void query() {
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT t FROM  REST_MENU_ITEM t", Reservation.class);
        List<Reservation> Reservations = q.getResultList();

        for(Reservation t : Reservations) {
            System.out.println(t);
        }

        em.close();
    }
}