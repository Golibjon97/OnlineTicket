package com.ticket.onlineticket.Service;

import com.ticket.onlineticket.Domain.Seat;
import com.ticket.onlineticket.Repository.SeatRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SeatService {

    private SeatRepository seatRepository;

    public SeatService(SeatRepository seatRepository) {
        this.seatRepository = seatRepository;
    }

    public Seat create(Seat seat){
        return seatRepository.save(seat);
    }

    public Optional<Seat> findOne(Long id){
        return seatRepository.findById(id);
    }

    public List<Seat> getAll(){
        return seatRepository.findAll();
    }

    public Seat getOne(Long id){
        Seat seat = seatRepository.findById(id).get();
        return seat;
    }

}
