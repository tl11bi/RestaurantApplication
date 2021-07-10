package Dao;

import Restaurant.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDaoTest {


    static EmployeeDao eDao = new EmployeeDao();

    @BeforeEach
    void setUp() {

    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_EmployeeDao_create_multipleEmployees() {
        Employee e1 = new Employee("Manager", "One", EmployeeType.Manager);
        Employee e2 = new Employee("Admin", "One", EmployeeType.Manager);
        Employee e3 = new Employee("Server", "One", EmployeeType.Server);
        Employee e4 = new Employee("Server", "Two", EmployeeType.Server);
        assertDoesNotThrow(()->eDao.create(e1));
        assertDoesNotThrow(()->eDao.create(e2));
        assertDoesNotThrow(()->eDao.create(e3));
        assertDoesNotThrow(()->eDao.create(e4));

    }

    @Test
    void test_EmployeeDao_remove() {
        Employee e1 = new Employee("Manager", "Three00000000000", EmployeeType.Manager);
        eDao.create(e1);
        Employee em1 = eDao.remove(e1.getEmployeeID());
        assertEquals(e1.getEmployeeID(), em1.getEmployeeID());
    }

    @Test
    void test_EmployeeDao_update() {
        Employee e1 = new Employee("Manager", "Three", EmployeeType.Manager);
        eDao.create(e1);
        e1.setFirstName("New");
        e1.setLastName("Last Name");
        assertDoesNotThrow(()->eDao.update(e1));
    }

    @Test
    void test_EmployeeDao_findById() {
        Employee e1 = new Employee("Manager", "Three", EmployeeType.Manager);
        eDao.create(e1);
        Employee returnedValue = eDao.findById(e1.getEmployeeID());
        assertEquals(e1.getEmployeeID(), returnedValue.getEmployeeID());
    }

    @Test
    void test_EmployeeDao_getEmployees() {
        assertDoesNotThrow(()->eDao.getEmployees());
        System.out.println(eDao.getEmployees());

    }

    @Test
    void test_EmployeeDao_findByFirstName() {
        String value = "testName";
        Employee e1 = new Employee(value, "Three", EmployeeType.Manager);
        eDao.create(e1);
        List<Employee> employees = eDao.findByFirstName(value);
        assertTrue(employees.contains(e1));
    }

    @Test
    void test_EmployeeDao_findByLastName() {
        String value = "testName";
        Employee e1 = new Employee("Manager", value, EmployeeType.Manager);
        eDao.create(e1);
        List<Employee> employees = eDao.findByLastName(value);
        assertTrue(employees.contains(e1));
    }

    @Test
    void test_EmployeeDao_findByEmpType() {
        Employee e1 = new Employee("Manager", "One", EmployeeType.Manager);
        List<Employee> employees = eDao.findByEmpType(e1.getType());
        assertEquals(e1, employees.get(0));
    }
}