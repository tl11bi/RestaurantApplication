package com.connorli.restaurant.databaseTesting;

import com.connorli.restaurant.domain.Employee;
import com.connorli.restaurant.domain.EmployeeType;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;


class EmployeeTest {

    static EntityManager em;


    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("ItemPU");
        em = emf.createEntityManager();
        create();
        // create2();
//        namedQuery();
//        query();
        //read();

    }

    private static void create(){
        em.getTransaction().begin();
        Employee employee = new Employee("Connor", "Li", EmployeeType.Administrator);
        em.persist(employee);
        System.out.println(employee);
        em.getTransaction().commit();
        em.close();
    }




    private static void create2() {
        em.getTransaction().begin();
        em.persist(new Employee("firstName1", "lastName1", EmployeeType.Manager));
        em.persist(new Employee("firstName2", "lastName2", EmployeeType.Server));
        em.persist(new Employee("firstName3", "lastName3", EmployeeType.Server));

        em.getTransaction().commit();
        em.close();
    }

    /*
     * Named Queries are really useful because they will match up
     * with your use cases...
     * e.g. if I have a banking application, a use case will be
     * "FIND accounts by user"
     */
    private static void namedQuery() {
        em.getTransaction().begin();

        System.out.println("Testing FindAll:");
        Query q = em.createNamedQuery("REST_EMPLOYEE.findAll");
        List<Employee> employees = q.getResultList();
        for(Employee e : employees) {
            System.out.println(e);
        }

        System.out.println("Testing FindByName");
        List<Employee> namedTheaters = em.createNamedQuery("REST_EMPLOYEE.findByFirstName").setParameter("FIRST_NAME", "Connor").getResultList();
        for(Employee t : namedTheaters) {
            System.out.println(t);
        }

        em.close();
    }

    private static void query() {
        em.getTransaction().begin();

        Query q = em.createQuery("SELECT t FROM REST_EMPLOYEE t", Employee.class);
        List<Employee> Employees = q.getResultList();

        for(Employee t : Employees) {
            System.out.println(t);
        }

        em.close();
    }

    private static void read() {
        em.getTransaction().begin();
        Employee employee = em.find(Employee.class, ((long)100));
        System.out.println(employee);
        em.close();
    }






}