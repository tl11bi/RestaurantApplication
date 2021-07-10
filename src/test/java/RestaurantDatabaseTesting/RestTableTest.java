package RestaurantDatabaseTesting;

import Restaurant.RestTable;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RestTableTest {
    static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ItemPU");
        em = emf.createEntityManager();
        //createMultiple();
        query();
    }

    private static void createMultiple() {
        em.getTransaction().begin();
        em.persist(new RestTable("RestTable1", 5));
        em.persist(new RestTable("RestTable2", 6));
        em.persist(new RestTable("RestTable3", 7));
        em.getTransaction().commit();
        em.close();
    }

    private static void query() {
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT t FROM  REST_TABLE t", RestTable.class);
        List<RestTable> RestTables = q.getResultList();

        for(RestTable t : RestTables) {
            System.out.println(t);
        }

        em.close();
    }

}