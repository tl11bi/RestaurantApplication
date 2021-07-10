package Dao;

import Restaurant.MenuItem;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MenuItemDaoTest {
    static MenuItemDao mDao = new MenuItemDao();


    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void test_menuItem_create_remove() {
        MenuItem m1 = new MenuItem("MenuItem1", BigDecimal.valueOf(15));
        MenuItem m2 = new MenuItem("MenuItem2", BigDecimal.valueOf(15));
        MenuItem m3 = new MenuItem("MenuItem3", BigDecimal.valueOf(15));
        assertDoesNotThrow(()->mDao.create(m1));
        assertDoesNotThrow(()->mDao.create(m2));
        assertDoesNotThrow(()->mDao.create(m3));
        System.out.println(mDao.getMenuItems());
        assertDoesNotThrow(()->mDao.remove(m1.getMenuItemID()));
        assertDoesNotThrow(()->mDao.remove(m2.getMenuItemID()));
        assertDoesNotThrow(()->mDao.remove(m3.getMenuItemID()));
        System.out.println(mDao.getMenuItems());
    }



    @Test
    void test_menuItem_update() {
        MenuItem m1 = new MenuItem("MenuItem1", BigDecimal.valueOf(15));
        assertDoesNotThrow(()->mDao.create(m1));
        System.out.println(mDao.getMenuItems());
        m1.setMenuItemName("MenuItemNew1");
        mDao.update(m1);
        System.out.println(mDao.getMenuItems());
        assertDoesNotThrow(()->mDao.remove(m1.getMenuItemID()));
        System.out.println(mDao.getMenuItems());
    }

    @Test
    void test_menuItem_findById() {
        MenuItem m1 = new MenuItem("MenuItem1", BigDecimal.valueOf(15));
        assertDoesNotThrow(()->mDao.create(m1));
        MenuItem mm1 = mDao.findById(m1.getMenuItemID());
        assertEquals(m1, mm1);
        assertDoesNotThrow(()->mDao.remove(m1.getMenuItemID()));
    }

    @Test
    void test_menuItem_getMenuItems() {

        List<MenuItem> menuItems = assertDoesNotThrow(()->mDao.getMenuItems());
        System.out.println(menuItems);
    }
}