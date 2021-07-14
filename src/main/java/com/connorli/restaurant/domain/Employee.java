package com.connorli.restaurant.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@NamedQueries({
        @NamedQuery(name = "REST_EMPLOYEE.findAll", query = "SELECT e FROM REST_EMPLOYEE e"),
        @NamedQuery(name = "REST_EMPLOYEE.findByFirstName", query = "SELECT e FROM REST_EMPLOYEE e WHERE e.firstName = :firstName"),
        @NamedQuery(name = "REST_EMPLOYEE.findByLastName", query = "SELECT e FROM REST_EMPLOYEE e WHERE e.lastName = :lastName"),
        @NamedQuery(name = "REST_EMPLOYEE.findByEmpType", query = "SELECT e FROM REST_EMPLOYEE e WHERE e.employeeType = :empType")
})
@Entity(name = "REST_EMPLOYEE")
@Table(name = "REST_EMPLOYEE")
public class Employee {
    @Id
    @Column(name = "EMP_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emp_id_gen")
    @SequenceGenerator(name = "emp_id_gen", sequenceName = "emp_seq", allocationSize = 1)
    private long employeeID;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "EMP_TYPE")
    @Enumerated(EnumType.STRING)
    private EmployeeType employeeType;
    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL)
    private List<Order> orders = new ArrayList<>(10);

    public Employee() {
    }


    public Employee(String firstName, String lastName, EmployeeType employeeType) {
        this.firstName = firstName;
        this.lastName = lastName;

        this.employeeType = employeeType;
    }


    //RELATION TABLE REFERENCES
    protected void addOrder(Order order) {
        orders.add(order);
    }

    protected void removeOrder(Order order) {
        orders.remove(order);
    }


    //PROPERTY METHODS
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public EmployeeType getType() {
        return employeeType;
    }

    public void setType(EmployeeType employeeType) {
        this.employeeType = employeeType;
    }

    public long getEmployeeID() {
        return employeeID;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return employeeID == employee.employeeID && firstName.equals(employee.firstName) && lastName.equals(employee.lastName) && employeeType == employee.employeeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, firstName, lastName, employeeType);
    }


    @Override
    public String toString() {
        return "Employee{" +
                "employeeID=" + employeeID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", type=" + employeeType +
                '}';
    }


}
