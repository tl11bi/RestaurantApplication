package Dao;

import Restaurant.RestTable;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

public class RestTableDao {


    private DbConnection connection;

    public RestTableDao() {
        super();
        connection = DbConnection.getInstance();
    }

    public RestTable create(RestTable restTable) {
        EntityManager em = connection.getEntityManager();
        em.getTransaction().begin();
        em.persist(restTable);
        em.getTransaction().commit();
        em.close();
        return restTable;
    }


    public RestTable remove(long id) {
        EntityManager em = connection.getEntityManager();
        RestTable foundRestTable = em.find(RestTable.class, id);
        em.getTransaction().begin();
        em.remove(foundRestTable);
        em.getTransaction().commit();
        em.close();
        return foundRestTable;
    }


    public RestTable update(RestTable restTable) {
        EntityManager em = connection.getEntityManager();
        RestTable foundRestTable = em.find(RestTable.class, restTable.getTableID());
        em.getTransaction().begin();

        if (restTable.getTableName() != null && !restTable.getTableName().equals("")
                && restTable.getCapacity() != 0) {
            foundRestTable.setTableName(restTable.getTableName());
            foundRestTable.setCapacity(restTable.getCapacity());
        }
        em.getTransaction().commit();
        em.close();
        return foundRestTable;
    }


    public RestTable findById(long id) {
        EntityManager em = connection.getEntityManager();
        RestTable restTable = em.find(RestTable.class, id);
        em.close();
        return restTable;
    }

    public List<RestTable> getRestTables() {
        EntityManager em = connection.getEntityManager();
        TypedQuery<RestTable> query = em.createNamedQuery("REST_TABLE.findAll", RestTable.class);
        List<RestTable> restTables = query.getResultList();
        em.close();
        return restTables;
    }
}
