package com.ticket.onlineticket.Controller;

import com.ticket.onlineticket.Domain.*;
import com.ticket.onlineticket.Dto.BookingDto;
import com.ticket.onlineticket.Service.BookingService;
import com.ticket.onlineticket.Service.ConcertService;
import com.ticket.onlineticket.Service.SeatService;
import com.ticket.onlineticket.Service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/booking")
public class BookingController {

//    @Autowired
    private final ModelMapper modelMapper;

    private final BookingService bookingService;
    private final ConcertService concertService;
    private final UserService userService;
    private final SeatService seatService;

    public BookingController(ModelMapper modelMapper, BookingService bookingService, ConcertService concertService, UserService userService, SeatService seatService) {
        this.modelMapper = modelMapper;
        this.bookingService = bookingService;
        this.concertService = concertService;
        this.userService = userService;
        this.seatService = seatService;
    }

    //DTO goes here
        @PostMapping("/{user_id}/{concert_id}/{seat_id}/save")
        public ResponseEntity<?> save(@PathVariable Long user_id,
                                      @PathVariable Long concert_id,
                                      @PathVariable Long seat_id,
                                      @RequestBody BookingDto bookingDto
        ) {
            Bookings bookings=new Bookings();

            bookings.setPaid(bookingDto.isPaid());

            //concert added to booking but concert do not add booking yet
            Optional<Concert> concert = concertService.findOne(concert_id);
            bookings.setConcert(concert.get());
            concert.get().addBooking(bookings);

            Optional<User> user = userService.findOne(user_id);
            bookings.setUser(user.get());
            user.get().addBooking(bookings);

            Optional<Seat> seat = seatService.findOne(seat_id);
            bookings.setSeat(seat.get());
            seat.get().addBookings(bookings);

            //finally booking save
            bookingService.create(bookings);

            return ResponseEntity.ok(bookings);
        }

        @GetMapping("/getBookings")
        public ResponseEntity getBookings(){
            return ResponseEntity.ok(bookingService.getAll());
        }

    //thymleaf goes down
        @PostMapping  ("/create")
        public String create(@ModelAttribute("bookings")/* @RequestBody */ BookingDto bookDto) {
            Bookings bookings = modelMapper.map(bookDto, Bookings.class);
            bookingService.create(bookings);
            BookingDto bookDto1 = modelMapper.map(bookings, BookingDto.class);
            /*return new ResponseEntity<BookDto>(bookDto1, HttpStatus.CREATED);*/
            return "redirect:/";
        }
    
}
