package com.ticket.onlineticket.Service;

import com.ticket.onlineticket.Domain.Bookings;
import com.ticket.onlineticket.Repository.BookingRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BookingService {

    private BookingRepository bookingRepository;

    public BookingService(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }


    public Bookings create(Bookings bookings){
        return bookingRepository.save(bookings);
    }

    public List<Bookings> getAll(){
        return bookingRepository.findAll();

    }

}
