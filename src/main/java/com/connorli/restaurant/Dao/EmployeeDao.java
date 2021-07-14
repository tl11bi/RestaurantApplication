package com.connorli.restaurant.Dao;

import com.connorli.restaurant.domain.Employee;
import com.connorli.restaurant.domain.EmployeeType;


import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class EmployeeDao {
    private DbConnection connection;
    
    public EmployeeDao(){
        super();
        connection = DbConnection.getInstance();
    }
    
    public Employee create(Employee employee) {
        EntityManager em = connection.getEntityManager();
        em.getTransaction().begin();
        em.persist(employee);
        em.getTransaction().commit();
        em.close();
        return employee;
    }

    
    public Employee remove(long id) {
        EntityManager em = connection.getEntityManager();
        Employee foundEmployee = em.find(Employee.class, id);
        em.getTransaction().begin();
        em.remove(foundEmployee);
        em.getTransaction().commit();
        em.close();
        return foundEmployee;
    }


    public void update(Employee employee) {
        EntityManager em = connection.getEntityManager();
        Employee foundEmployee = em.find(Employee.class, employee.getEmployeeID());
        em.getTransaction().begin();

        if(employee.getFirstName() != null && !employee.getFirstName().equals("")
        && employee.getLastName() != null && !employee.getLastName().equals("")
                && employee.getType() != null) {
            foundEmployee.setFirstName(employee.getFirstName());
            foundEmployee.setLastName(employee.getLastName());
            foundEmployee.setType(employee.getType());
        }
        em.getTransaction().commit();
        em.close();
    }

    
    public Employee findById(long id) {
        EntityManager em = connection.getEntityManager();
        Employee employee = em.find(Employee.class, id);
        em.close();
        return employee;
    }
    
    public List<Employee> getEmployees(){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Employee> query = em.createNamedQuery("REST_EMPLOYEE.findAll", Employee.class);
        List<Employee> emps = query.getResultList();
        em.close();
        return emps;
    }
    
    public List<Employee> findByFirstName(String firstName){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Employee> query = em.createNamedQuery("REST_EMPLOYEE.findByFirstName", Employee.class);
        query.setParameter("firstName", firstName + "%");
        List<Employee> companies = query.getResultList();
        em.close();
        return companies;
    }


    public List<Employee> findByLastName(String lastName){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Employee> query = em.createNamedQuery("REST_EMPLOYEE.findByLastName", Employee.class);
        query.setParameter("lastName", lastName + "%");
        List<Employee> employees = query.getResultList();
        em.close();
        return employees;
    }


    public List<Employee> findByEmpType(EmployeeType employeeType){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Employee> query = em.createNamedQuery("REST_EMPLOYEE.findByEmpType", Employee.class);
        query.setParameter("empType", employeeType + "%");
        List<Employee> employees = query.getResultList();
        em.close();
        return employees;
    }
    
    
    
}
