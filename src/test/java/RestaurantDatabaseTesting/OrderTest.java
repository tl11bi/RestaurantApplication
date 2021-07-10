package RestaurantDatabaseTesting;

import Restaurant.Employee;
import Restaurant.EmployeeType;
import Restaurant.Order;
import Restaurant.RestTable;

import javax.persistence.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    static EntityManager em;

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ItemPU");
        em = emf.createEntityManager();
        createMultiple();
        //query();
    }

    private static void createMultiple() {
        em.getTransaction().begin();
        Employee employee = new Employee("Connor", "Li", EmployeeType.Manager);
        em.persist(employee);
        RestTable restTable = new RestTable("hahahaha", 10);
        em.persist(restTable);
        Order order = new Order(employee,restTable);
        System.out.println(order);
        em.persist(order);
        //em.persist(new Order(employee, restTable));
        //em.persist(new Order(employee, restTable));
        em.getTransaction().commit();
        em.close();
    }

    private static void query() {
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT t FROM  REST_ORDER t", Order.class);
        List<Order> Orders = q.getResultList();

        for(Order t : Orders) {
            System.out.println(t);
        }

        em.close();
    }
}