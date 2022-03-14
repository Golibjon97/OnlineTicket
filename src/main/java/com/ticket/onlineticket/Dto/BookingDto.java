package com.ticket.onlineticket.Dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/*@Getter
@Setter*/
public class BookingDto {

    private boolean paid;

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
