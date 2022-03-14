package com.ticket.onlineticket.Service;

import com.ticket.onlineticket.Domain.Bookings;
import com.ticket.onlineticket.Domain.Concert;
import com.ticket.onlineticket.Domain.Seat;
import com.ticket.onlineticket.Dto.AvailableSeatDto;
import com.ticket.onlineticket.Repository.BookingRepository;
import com.ticket.onlineticket.Repository.ConcertRepository;
import com.ticket.onlineticket.Repository.SeatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
@Service
public class ConcertService {

    private final ConcertRepository concertRepository;
    private final SeatRepository seatRepository;
    private final BookingRepository bookingRepository;

    public ConcertService(ConcertRepository concertRepository, SeatRepository seatRepository, BookingRepository bookingRepository) {
        this.concertRepository = concertRepository;
        this.seatRepository = seatRepository;
        this.bookingRepository = bookingRepository;
    }

    //PostMapping
    public Concert create(Concert concert){
        return concertRepository.save(concert);
    }

    //GetMapping(All)
    public List<Concert> getAll(){
        return concertRepository.findAll();
    }

    //GetMapping(One) by optional
    public Optional<Concert> findOne(Long id){
        return concertRepository.findById(id);
    }

    //PutMapping
    public Concert modify(Concert concert){
        return concertRepository.save(concert);
    }

    public void delete(Long id){
        Concert concert = concertRepository.getOne(id);
        concertRepository.delete(concert);
    }

    public AvailableSeatDto getAvailableSeats(Long concertId){
        AvailableSeatDto availableSeatDto=new AvailableSeatDto();
        Concert concert = concertRepository.findById(concertId).get();

        List<Bookings> seatReservedList = bookingRepository.findByConcertId(concertId);
        List<Seat> all = seatRepository.findAll();

        for (Bookings seatReserved : seatReservedList) {
            int index=0;
            while (index<all.size()){
                if(all.get(index).getNumber()
                        ==seatReserved.getSeat().getNumber())
                    all.remove(index--);
                index++;
            }
        }

        DecimalFormat decimalFormat=new DecimalFormat("###,###,###,###.##");
        for (Seat seat : all) {
            AvailableSeatDto.Seat seat1 = new AvailableSeatDto.Seat();
            seat1.setSeatNumber(seat.getNumber());
            seat1.setClassName(seat.getCategory().getName());
            seat1.setPrice(seat.getCategory().getServicePercentage()*concert.getPrice());
            availableSeatDto.add(seat1);
        }

        return availableSeatDto;
    }

}