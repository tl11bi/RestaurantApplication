package Dao;

import Restaurant.Employee;
import Restaurant.Reservation;
import Restaurant.RestTable;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservationDaoTest {
    static ReservationDao rDao = new ReservationDao();
    static RestTableDao tDao = new RestTableDao();


    @Test
    void test_reservationDaoTest_create_remove_read() {
        RestTable table1 = new RestTable("table1", 10);
        RestTable table2 = new RestTable("table2", 6);
        Reservation reservation1 = new Reservation("resFirst1", "resLast1", Timestamp.valueOf("2020-08-19 14:20:05"),5, table1);
        Reservation reservation2 = new Reservation("resFirst2", "resLast2", Timestamp.valueOf("2020-09-20 15:20:05"),6, table2);
        Reservation reservation3 = new Reservation("resFirst3", "resLast3", Timestamp.valueOf("2020-10-21 16:20:05"),8, table1);
        tDao.create(table1);
        tDao.create(table2);
        assertDoesNotThrow(()->rDao.create(reservation1));
        assertDoesNotThrow(()->rDao.create(reservation2));
        assertDoesNotThrow(()->rDao.create(reservation3));
        printReservations();
        assertDoesNotThrow(()->rDao.remove(reservation1.getReservationID()));
        assertDoesNotThrow(()->rDao.remove(reservation2.getReservationID()));
        assertDoesNotThrow(()->rDao.remove(reservation3.getReservationID()));
        printReservations();
    }

    private void printReservations() {
        List<Reservation> menuItems = assertDoesNotThrow(()->rDao.getReservations());
        System.out.println(menuItems);
    }


    @Test
    void test_reservationDaoTest_update_findById() {
        RestTable table1 = new RestTable("table1", 10);
        RestTable table2 = new RestTable("table2", 6);
        Reservation reservation1 = new Reservation("resFirst1", "resLast1", Timestamp.valueOf("2020-08-19 14:20:05"),5, table2);
        Reservation reservation2 = new Reservation("resFirst2", "resLast2", Timestamp.valueOf("2020-09-20 15:20:05"),6, table2);
        Reservation reservation3 = new Reservation("resFirst3", "resLast3", Timestamp.valueOf("2020-10-21 16:20:05"),8, table2);
        tDao.create(table1);
        tDao.create(table2);
        rDao.create(reservation1);
        rDao.create(reservation2);
        rDao.create(reservation3);
        printReservations();
        reservation1.setRestTable(table1);
        reservation1.setFirstName("new name 1");
        reservation2.setRestTable(table1);
        reservation3.setRestTable(table1);
        assertDoesNotThrow(()->rDao.update(reservation1));
        assertDoesNotThrow(()->rDao.update(reservation2));
        assertDoesNotThrow(()->rDao.update(reservation3));
        assertEquals(rDao.findById(reservation1.getReservationID()), reservation1);
        assertEquals(rDao.findById(reservation2.getReservationID()), reservation2);
        assertEquals(rDao.findById(reservation3.getReservationID()), reservation3);
        printReservations();
        rDao.remove(reservation1.getReservationID());
        rDao.remove(reservation2.getReservationID());
        rDao.remove(reservation3.getReservationID());
        tDao.remove(table1.getTableID());
        tDao.remove(table2.getTableID());
    }


    @Test
    void test_reservationDaoTest_findByFirstName() {
        String input1 = "resFirst1";
        String input2 = "resFirst2";
        String input3 = "resFirst3";

        RestTable table1 = new RestTable("table1", 10);
        RestTable table2 = new RestTable("table2", 6);
        Reservation reservation1 = new Reservation(input1, "resLast1", Timestamp.valueOf("2020-08-19 14:20:05"),5, table2);
        Reservation reservation2 = new Reservation(input2, "resLast2", Timestamp.valueOf("2020-09-20 15:20:05"),6, table2);
        Reservation reservation3 = new Reservation(input3, "resLast3", Timestamp.valueOf("2020-10-21 16:20:05"),8, table2);
        tDao.create(table1);
        tDao.create(table2);
        rDao.create(reservation1);
        rDao.create(reservation2);
        rDao.create(reservation3);
        printReservations();

        List<Reservation> reservations = rDao.findByFirstName(input1);
        assertTrue(reservations.contains(reservation1));
        reservations = rDao.findByFirstName(input2);
        assertTrue(reservations.contains(reservation2));
        reservations = rDao.findByFirstName(input3);
        assertTrue(reservations.contains(reservation3));
        printReservations();
        rDao.remove(reservation1.getReservationID());
        rDao.remove(reservation2.getReservationID());
        rDao.remove(reservation3.getReservationID());
        tDao.remove(table1.getTableID());
        tDao.remove(table2.getTableID());
    }

    @Test
    void test_reservationDaoTest_findByLastName() {
        String input1 = "resLast1";
        String input2 = "resLast2";
        String input3 = "resLast3";

        RestTable table1 = new RestTable("table1", 10);
        RestTable table2 = new RestTable("table2", 6);
        Reservation reservation1 = new Reservation("resFirst1", input1, Timestamp.valueOf("2020-08-19 14:20:05"),5, table2);
        Reservation reservation2 = new Reservation("resFirst2", input2, Timestamp.valueOf("2020-09-20 15:20:05"),6, table2);
        Reservation reservation3 = new Reservation("resFirst3", input3, Timestamp.valueOf("2020-10-21 16:20:05"),8, table2);
        tDao.create(table1);
        tDao.create(table2);
        rDao.create(reservation1);
        rDao.create(reservation2);
        rDao.create(reservation3);
        printReservations();

        List<Reservation> reservations = rDao.findByLastName(input1);
        assertTrue(reservations.contains(reservation1));
        reservations = rDao.findByLastName(input2);
        assertTrue(reservations.contains(reservation2));
        reservations = rDao.findByLastName(input3);
        assertTrue(reservations.contains(reservation3));
        printReservations();
        rDao.remove(reservation1.getReservationID());
        rDao.remove(reservation2.getReservationID());
        rDao.remove(reservation3.getReservationID());
        tDao.remove(table1.getTableID());
        tDao.remove(table2.getTableID());
    }
}