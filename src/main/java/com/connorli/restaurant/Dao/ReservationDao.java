package com.connorli.restaurant.Dao;

import com.connorli.restaurant.domain.Reservation;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

public class ReservationDao {

    private DbConnection connection;

    public ReservationDao(){
        super();
        connection = DbConnection.getInstance();
    }

    public Reservation create(Reservation reservation) {
        EntityManager em = connection.getEntityManager();
        em.getTransaction().begin();
        em.persist(reservation);
        em.getTransaction().commit();
        em.close();
        return reservation;
    }


    public Reservation remove(long id) {
        EntityManager em = connection.getEntityManager();
        Reservation foundReservation = em.find(Reservation.class, id);
        em.getTransaction().begin();
        em.remove(foundReservation);
        em.getTransaction().commit();
        em.close();
        return foundReservation;
    }


    public Reservation update(Reservation reservation) {
        EntityManager em = connection.getEntityManager();
        Reservation foundReservation = em.find(Reservation.class, reservation.getReservationID());
        em.getTransaction().begin();

        if(reservation.getFirstName() != null && !reservation.getFirstName().equals("")
                && reservation.getLastName() != null && !reservation.getLastName().equals("")
                && reservation.getRestTable() != null && reservation.getNumberOfPeople() !=0
                && reservation.getTime()!=null) {
            foundReservation.setFirstName(reservation.getFirstName());
            foundReservation.setLastName(reservation.getLastName());
            foundReservation.setRestTable(reservation.getRestTable());
            foundReservation.setNumberOfPeople(reservation.getNumberOfPeople());
            foundReservation.setTime(reservation.getTime());
        }
        em.getTransaction().commit();
        em.close();
        return foundReservation;
    }


    public Reservation findById(long id) {
        EntityManager em = connection.getEntityManager();
        Reservation reservation = em.find(Reservation.class, id);
        em.close();
        return reservation;
    }

    public List<Reservation> getReservations(){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Reservation> query = em.createNamedQuery("REST_RESERVATION.findAll", Reservation.class);
        List<Reservation> reservations = query.getResultList();
        em.close();
        return reservations;
    }

    public List<Reservation> findByFirstName(String firstName){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Reservation> query = em.createNamedQuery("REST_RESERVATION.findByFirstName", Reservation.class);
        query.setParameter("firstName", firstName);
        List<Reservation> reservations = query.getResultList();
        em.close();
        return reservations;
    }


    public List<Reservation> findByLastName(String lastName){
        EntityManager em = connection.getEntityManager();
        TypedQuery<Reservation> query = em.createNamedQuery("REST_RESERVATION.findByLastName", Reservation.class);
        query.setParameter("lastName", lastName);
        List<Reservation> reservations = query.getResultList();
        em.close();
        return reservations;
    }
}
