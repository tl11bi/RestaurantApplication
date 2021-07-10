package Restaurant;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
@NamedQueries({
        @NamedQuery(name = "REST_ORDER.findAll", query = "SELECT o FROM REST_ORDER o")
})
@Table(name = "REST_ORDER")
@Entity(name = "REST_ORDER")
public class Order {
    @Id
    @Column(name = "ORDER_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "order_id_gen")
    @SequenceGenerator(name = "order_id_gen", sequenceName = "order_seq", allocationSize = 1)
    private long orderID;
    @ManyToOne
    @JoinColumn(name = "EMP_ID", nullable = false)
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "TABLE_ID", nullable = false)
    private RestTable restTable;
    @ManyToMany(cascade = { CascadeType.DETACH }, fetch=FetchType.EAGER)
    @JoinTable(name = "REST_ORDER_MENU_ITEM",
            joinColumns =
            @JoinColumn(name = "ORDER_ID"),
            inverseJoinColumns =
            @JoinColumn(name = "MENU_ITEM_ID")
    )
    private List<MenuItem> menuItems;

    public Order(Employee employee, RestTable restTable) {
        this.employee = employee;
        this.restTable = restTable;
        employee.addOrder(this);
        restTable.addOrder(this);
        menuItems = new ArrayList<>(10);
    }

    public Order() {
        menuItems = new ArrayList<>(10);
    }


    //relation table method
    public void addOrderItem(MenuItem... menuItems) {
        this.menuItems.addAll(Arrays.asList(menuItems));
    }


    public void removeOrderItem(int index) {
        menuItems.remove(index);
    }
    //property method

    public BigDecimal getTotalPrice() {
        return BigDecimal.ZERO;
    }

    public long getOrderID() {
        return orderID;
    }



    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public RestTable getRestTable() {
        return restTable;
    }

    public void setRestTable(RestTable restTable) {
        this.restTable = restTable;
    }

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
    }


    @Override
    public String toString() {
        return "Order{" +
                "orderID=" + orderID +
                ", employee=" + employee +
                ", restTable=" + restTable +
                ", menuItems=" + menuItems +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return orderID == order.orderID && employee.equals(order.employee) && restTable.equals(order.restTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderID, employee, restTable);
    }
}
