package com.ticket.onlineticket.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class AvailableSeatDto {

    private List<Seat> seatList;

    @NoArgsConstructor
    @AllArgsConstructor
    public static class Seat {
        private Integer seatNumber;
        private String className;
        private int price;

        public Integer getSeatNumber() {
            return seatNumber;
        }

        public void setSeatNumber(Integer seatNumber) {
            this.seatNumber = seatNumber;
        }

        public String getClassName() {
            return className;
        }

        public void setClassName(String className) {
            this.className = className;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }

    public void add(Seat seat){
        if (seatList==null)
            seatList=new ArrayList<>();
        seatList.add(seat);
    }
}