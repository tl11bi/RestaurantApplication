package Restaurant;

import javax.persistence.*;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
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
        @NamedQuery(name = "REST_MENU_ITEM.findAll", query = "SELECT mt FROM REST_MENU_ITEM mt")
})
@Entity(name = "REST_MENU_ITEM")
@Table(name = "REST_MENU_ITEM")
public class MenuItem {
    @Id
    @Column(name = "MENU_ITEM_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "menu_id_gen")
    @SequenceGenerator(name = "menu_id_gen", sequenceName = "menu_seq", allocationSize = 1)
    private long menuItemID;
    @Column(name = "MENU_ITEM_NAME")
    private String menuItemName;
    @Column(name = "MENU_ITEM_PRICE")
    private BigDecimal price;
    @ManyToMany(mappedBy = "menuItems", fetch=FetchType.LAZY)
    private List<Order> orders = new ArrayList<>(10);

    public MenuItem(String menuItemName, BigDecimal price) {
        this.menuItemName = menuItemName;
        this.price = price;
    }


    public MenuItem() {
    }

    //for relation table
    protected void addOrder(Order order){
        orders.add(order);
    }

    protected void removeOrder(Order order){
        orders.remove(order);
    }

    //property methods
    public long getMenuItemID() {
        return menuItemID;
    }

    public String getMenuItemName() {
        return menuItemName;
    }

    public void setMenuItemName(String menuItemName) {
        this.menuItemName = menuItemName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }


    @Override
    public String toString() {
        return "MenuItem{" +
                "menuItemID=" + menuItemID +
                ", menuItemName='" + menuItemName + '\'' +
                ", price=" + price +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem menuItem = (MenuItem) o;
        return menuItemID == menuItem.menuItemID && menuItemName.equals(menuItem.menuItemName) && price.equals(menuItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItemID, menuItemName, price);
    }

}
