package com.connorli.restaurant.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Restaurant {
    private List<Employee> employeeList;
    private List<Order> orderList;
    private List<RestTable> restTableList;
    private List<MenuItem> menuItemList;
    private List<Reservation> reservationList;

    private Restaurant() {
        employeeList = new ArrayList<>(10);
        orderList = new ArrayList<>(10);
        restTableList = new ArrayList<>(10);
        menuItemList = new ArrayList<>(10);
        reservationList = new ArrayList<>(10);

    }

    public Restaurant getInstance() {
        if (instance == null) {
            instance = new Restaurant();
        }
        return instance;
    }




    public void addEmployee(String firstName, String lastName, EmployeeType employeeType) {
        employeeList.add(new Employee(firstName, lastName, employeeType));
    }

    public void removeEmployee(Employee employee) {
        employeeList.remove(employee);
    }


    public void addOrder(Employee employee, RestTable restTable) {
        orderList.add(new Order(employee, restTable));
    }

    public void removeOrder(Order order) {
        orderList.remove(order);
    }


    public void addTable(String tableName, int capacity) {
        restTableList.add(new RestTable(tableName, capacity));
    }

    public void removeTable(RestTable restTable) {
        restTableList.remove(restTable);
    }


    public void addMenuItem(String menuItemName, BigDecimal price) {
        menuItemList.add(new MenuItem(menuItemName, price));
    }

    public void removeMenuItem(MenuItem menuItem) {
        menuItemList.remove(menuItem);
    }


    public void addReservation(Date time, String firstName, String lastName, int numberOfPeople, RestTable restTable) {
        reservationList.add(new Reservation(firstName, lastName, time, numberOfPeople, restTable));
    }

    public void removeReservation(Reservation reservation) {
        reservationList.remove(reservation);
    }


    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public List<RestTable> getTableList() {
        return restTableList;
    }

    public List<MenuItem> getMenuItemList() {
        return menuItemList;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }


    private static Restaurant instance;
    

    public List<RestTable> getFreeTables() {
        return restTableList.stream().filter(restTable -> restTable.isOccupied() == false).collect(Collectors.toList());
    }
}
