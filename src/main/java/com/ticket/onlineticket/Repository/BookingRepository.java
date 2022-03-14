package com.ticket.onlineticket.Repository;

import com.ticket.onlineticket.Domain.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {

    List<Bookings> findByConcertId(Long concertId);
}
