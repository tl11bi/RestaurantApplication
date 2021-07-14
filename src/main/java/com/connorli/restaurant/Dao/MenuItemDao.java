package com.connorli.restaurant.Dao;

import com.connorli.restaurant.domain.MenuItem;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

public class MenuItemDao {

    private DbConnection connection;

    public MenuItemDao(){
        super();
        connection = DbConnection.getInstance();
    }

    public MenuItem create(MenuItem menuItem) {
        EntityManager em = connection.getEntityManager();
        em.getTransaction().begin();
        em.persist(menuItem);
        em.getTransaction().commit();
        em.close();
        return menuItem;
    }


    public MenuItem remove(long id) {
        EntityManager em = connection.getEntityManager();
        MenuItem foundMenuItem = em.find(MenuItem.class, id);
        em.getTransaction().begin();
        em.remove(foundMenuItem);
        em.getTransaction().commit();
        em.close();
        return foundMenuItem;
    }



    public MenuItem update(MenuItem menuItem) {
        EntityManager em = connection.getEntityManager();
        MenuItem foundMenuItem = em.find(MenuItem.class, menuItem.getMenuItemID());
        em.getTransaction().begin();

        if(menuItem.getMenuItemName() != null && !menuItem.getMenuItemName().equals("")
                && (!menuItem.getPrice().equals( BigDecimal.ZERO))) {
            foundMenuItem.setMenuItemName(menuItem.getMenuItemName());
            foundMenuItem.setPrice(menuItem.getPrice());
        }
        em.getTransaction().commit();
        em.close();
        return foundMenuItem;
    }


    public MenuItem findById(long id) {
        EntityManager em = connection.getEntityManager();
        MenuItem menuItem = em.find(MenuItem.class, id);
        em.close();
        return menuItem;
    }

    public List<MenuItem> getMenuItems(){
        EntityManager em = connection.getEntityManager();
        em.getTransaction().begin();
        Query q = em.createQuery("SELECT mt FROM REST_MENU_ITEM mt", MenuItem.class);
        List<MenuItem> emps = q.getResultList();
        em.close();
        return emps;
    }
}
