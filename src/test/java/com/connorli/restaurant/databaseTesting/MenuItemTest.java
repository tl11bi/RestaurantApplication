package com.connorli.restaurant.databaseTesting;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.connorli.restaurant.domain.MenuItem;

import java.math.BigDecimal;
import java.util.List;

class MenuItemTest {

    static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ItemPU");
        em = emf.createEntityManager();
        createMultiple();
        query();
    }

    private static void createMultiple() {
        em.getTransaction().begin();
        em.persist(new MenuItem("MenuItem1", BigDecimal.valueOf(20)));
        em.persist(new MenuItem("MenuItem2", BigDecimal.valueOf(30)));
        em.persist(new MenuItem("MenuItem3", BigDecimal.valueOf(40)));
        em.getTransaction().commit();

        em.close();
    }

    private static void query() {
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT t FROM  REST_MENU_ITEM t", MenuItem.class);
        List<MenuItem> MenuItems = q.getResultList();

        for(MenuItem t : MenuItems) {
            System.out.println(t);
        }

        em.close();
    }

}