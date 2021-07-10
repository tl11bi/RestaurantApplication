package Dao;

import Restaurant.RestTable;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RestTableDaoTest {
    static RestTableDao tDao = new RestTableDao();

    private static void read() {
        List<RestTable> restTables = tDao.getRestTables();
        System.out.println(restTables);
    }




    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_restTableDao_create_read_remove() {

        RestTable t1 = new RestTable("Table1", 5);
        RestTable t2 = new RestTable("Table2", 7);
        RestTable t3 = new RestTable("Table3", 9);
        RestTable t4 = new RestTable("Table4", 10);

        assertDoesNotThrow(()->tDao.create(t1));
        assertDoesNotThrow(()->tDao.create(t2));
        assertDoesNotThrow(()->tDao.create(t3));
        assertDoesNotThrow(()->tDao.create(t4));

        read();

        assertDoesNotThrow(()->tDao.remove(t1.getTableID()));
        assertDoesNotThrow(()->tDao.remove(t2.getTableID()));
        assertDoesNotThrow(()->tDao.remove(t3.getTableID()));
        assertDoesNotThrow(()->tDao.remove(t4.getTableID()));
        read();


    }



    @Test
    void test_restTableDao_findById() {
        RestTable t1 = new RestTable("Table1", 5);
        RestTable t2 = new RestTable("Table2", 7);
        RestTable t3 = new RestTable("Table3", 9);
        RestTable t4 = new RestTable("Table4", 10);

       tDao.create(t1);
       tDao.create(t2);
       tDao.create(t3);
       tDao.create(t4);
        read();

        RestTable restTableResult = assertDoesNotThrow(()->tDao.findById(t1.getTableID()));
        assertEquals(t1, restTableResult);
       tDao.remove(t1.getTableID());
       tDao.remove(t2.getTableID());
       tDao.remove(t3.getTableID());
       tDao.remove(t4.getTableID());
        read();

    }

}
