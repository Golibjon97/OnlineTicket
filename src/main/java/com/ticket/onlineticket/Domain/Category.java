package com.ticket.onlineticket.Domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


@Entity
public class Category implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private int servicePercentage;

    @JsonIgnore
    @OneToMany(mappedBy = "category",
            cascade = {CascadeType.DETACH,CascadeType.MERGE,
                    CascadeType.PERSIST,CascadeType.REFRESH})
    private List<Seat> seats;

    public Category(int id, String name, int servicePercentage, List<Seat> seats) {
        this.id = id;
        this.name = name;
        this.servicePercentage = servicePercentage;
        this.seats = seats;
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getServicePercentage() {
        return servicePercentage;
    }

    public void setServicePercentage(int servicePercentage) {
        this.servicePercentage = servicePercentage;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public void addSeat(Seat seat){
        if (seats==null)
            seats=new ArrayList<>();
        seats.add(seat);
        seat.setCategory(this);
    }
}