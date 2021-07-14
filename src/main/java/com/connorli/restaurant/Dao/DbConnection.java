package com.connorli.restaurant.Dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class DbConnection {

    private static final String JPA_UNIT_NAME = "ItemPU";
    private static DbConnection connection = null;
    private EntityManagerFactory emf;

    private DbConnection() {
        init();
    }

    private void init() {
        if(emf == null || !emf.isOpen()) {
            emf = Persistence.createEntityManagerFactory(JPA_UNIT_NAME);
        }
    }

    public static DbConnection getInstance() {
        if(connection == null) {
            connection = new DbConnection();
        }
        return connection;
    }

    public EntityManager getEntityManager() {
        init();
        return emf.createEntityManager();
    }

    public void close() {
        emf.close();
    }
}
