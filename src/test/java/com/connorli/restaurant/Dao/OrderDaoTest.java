package com.connorli.restaurant.Dao;

import RestaurantApplication.domain.*;
import com.connorli.restaurant.domain.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderDaoTest {

    static OrderDao oDao = new OrderDao();;
    static EmployeeDao eDao = new EmployeeDao();
    static RestTableDao tDao = new RestTableDao();
    static MenuItemDao mDao = new MenuItemDao();





    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_orderDao_create_remove_getAllOrders() {
        Employee e1 = new Employee("Manager", "One", EmployeeType.Manager);
        Employee e2 = new Employee("Admin", "One", EmployeeType.Manager);
        RestTable t1 = new RestTable("Table1", 5);
        RestTable t2 = new RestTable("Table2", 7);
        Assertions.assertDoesNotThrow(()->eDao.create(e1));
        Assertions.assertDoesNotThrow(()->eDao.create(e2));
        assertDoesNotThrow(()->tDao.create(t1));
        assertDoesNotThrow(()->tDao.create(t2));

        Order o1 = new Order(e1, t1);
        Order o2 = new Order(e2, t1);
        Order o3 = new Order(e1, t2);

        Assertions.assertDoesNotThrow(()->oDao.create(o1));
        Assertions.assertDoesNotThrow(()->oDao.create(o2));
        Assertions.assertDoesNotThrow(()->oDao.create(o3));

        List<Order> orders =  Assertions.assertDoesNotThrow(()->oDao.getOrders());
        System.out.println(orders);

        Assertions.assertDoesNotThrow(()->oDao.remove(o1.getOrderID()));
        Assertions.assertDoesNotThrow(()->oDao.remove(o2.getOrderID()));
        Assertions.assertDoesNotThrow(()->oDao.remove(o3.getOrderID()));
        orders =  Assertions.assertDoesNotThrow(()->oDao.getOrders());
        System.out.println(orders);
    }



    @Test
    void test_orderDao_update() {
        Employee e1 = new Employee("Manager", "One", EmployeeType.Manager);
        RestTable t1 = new RestTable("Table1", 5);

        MenuItem m1 = new MenuItem("MenuItem1", BigDecimal.valueOf(15));
        MenuItem m2 = new MenuItem("MenuItem2", BigDecimal.valueOf(15));
        MenuItem m3 = new MenuItem("MenuItem3", BigDecimal.valueOf(15));
        eDao.create(e1);
        tDao.create(t1);
        mDao.create(m1);
        mDao.create(m2);
        mDao.create(m3);
        Order o1 = new Order(e1, t1);
        oDao.create(o1);
        o1.addOrderItem(m1, m2, m3);
        oDao.update(o1);
        Order result = oDao.findById(o1.getOrderID());
        assertTrue(result.getMenuItems().contains(m1));
        List<Order> orders =  Assertions.assertDoesNotThrow(()->oDao.getOrders());
        System.out.println(orders);
    }


    @Test
    void findById() {
        Employee e1 = new Employee("Manager", "One", EmployeeType.Manager);
        RestTable t1 = new RestTable("Table1", 5);
        eDao.create(e1);
        tDao.create(t1);

        Order o1 = new Order(e1, t1);

        oDao.create(o1);
        List<Order> orders =  oDao.getOrders();
        System.out.println(orders);
        Order result = Assertions.assertDoesNotThrow(()->oDao.findById(o1.getOrderID()));
        assertEquals(o1, result);
        System.out.println(o1);
        System.out.println(result);
        oDao.remove(o1.getOrderID());
        orders =  oDao.getOrders();
        System.out.println(orders);
    }


}