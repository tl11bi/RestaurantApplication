package Restaurant;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
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
        @NamedQuery(name = "REST_RESERVATION.findAll", query = "SELECT r FROM REST_RESERVATION r"),
        @NamedQuery(name = "REST_RESERVATION.findByFirstName", query = "SELECT r FROM REST_RESERVATION r WHERE r.firstName = :firstName"),
        @NamedQuery(name = "REST_RESERVATION.findByLastName", query = "SELECT r FROM REST_RESERVATION r WHERE r.lastName = :lastName")
})
@Entity(name = "REST_RESERVATION")
@Table(name = "REST_RESERVATION")
public class Reservation {
    @Id
    @Column(name = "RESERVATION_ID")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_id_gen")
    @SequenceGenerator(name = "reservation_id_gen", sequenceName = "reservation_seq", allocationSize = 1)
    private long reservationID;
    @Column(name = "RESERVATION_FIRST_NAME")
    private String firstName;
    @Column(name = "RESERVATION_LAST_NAME")
    private String lastName;
    @Column(name = "RESERVATION_TIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date time;
    @Column(name = "RESERVATION_NUMBER_OF_PEOPLE")
    private int numberOfPeople;
    @ManyToOne
    @JoinColumn(name = "TABLE_ID")
    private RestTable restTable;

    public Reservation() {
    }

    public Reservation(String firstName, String lastName, Date time, int numberOfPeople, RestTable restTable) {
        this.time = time;
        this.firstName = firstName;
        this.lastName = lastName;
        this.numberOfPeople = numberOfPeople;
        this.restTable = restTable;
        restTable.addReservation(this);
    }


    //Property method
    public long getReservationID() {
        return reservationID;
    }

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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getNumberOfPeople() {
        return numberOfPeople;
    }

    public void setNumberOfPeople(int numberOfPeople) {
        this.numberOfPeople = numberOfPeople;
    }

    public RestTable getRestTable() {
        return restTable;
    }

    public void setRestTable(RestTable restTable) {
        this.restTable = restTable;
    }


    @Override
    public String toString() {
        return "Reservation{" +
                "reservationID=" + reservationID +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", time=" + time +
                ", numberOfPeople=" + numberOfPeople +
                ", restTable=" + restTable +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Reservation)) return false;
        Reservation that = (Reservation) o;
        return reservationID == that.reservationID && numberOfPeople == that.numberOfPeople && firstName.equals(that.firstName) && lastName.equals(that.lastName) && time.equals(that.time) && restTable.equals(that.restTable);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservationID, firstName, lastName, time, numberOfPeople, restTable);
    }

}
