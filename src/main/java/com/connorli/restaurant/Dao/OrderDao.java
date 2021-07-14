package com.connorli.restaurant.Dao;

import com.connorli.restaurant.domain.Order;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class OrderDao{
    private DbConnection connection;

    public OrderDao(){
        super();
        connection = DbConnection.getInstance();
    }
    public Order create(Order order) {
        EntityManager em = connection.getEntityManager();
        em.getTransaction().begin();
        em.persist(order);
        em.getTransaction().commit();
        em.close();
        return order;
    }
    
    public Order remove(long id) {
        EntityManager em = connection.getEntityManager();
        Order foundOrder = em.find(Order.class, id);
        em.getTransaction().begin();
        em.remove(foundOrder);
        em.getTransaction().commit();
        em.close();
        return foundOrder;
    }

    public void update(Order order) {
        EntityManager em = connection.getEntityManager();
        Order foundOrder = em.find(Order.class, order.getOrderID());
        em.getTransaction().begin();

        if(order.getEmployee() != null
                && order.getRestTable() != null
                && order.getEmployee() != null
                && order.getMenuItems() != null) {
            foundOrder.setRestTable(order.getRestTable());
            foundOrder.setEmployee(order.getEmployee());
            foundOrder.setMenuItems(order.getMenuItems());
        }
        em.getTransaction().commit();
        em.close();
    }

    public Order findById(long id) {
        EntityManager em = connection.getEntityManager();
        Order order = em.find(Order.class, id);
        em.close();
        return order;
    }

    public List<Order> getOrders(){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Order> query = em.createNamedQuery("REST_ORDER.findAll", Order.class);
        List<Order> orders = query.getResultList();
        em.close();
        return orders;
    }
}
