package com.ticket.onlineticket.Controller;

import com.ticket.onlineticket.Domain.Category;
import com.ticket.onlineticket.Domain.Seat;
import com.ticket.onlineticket.Dto.SeatDto;
import com.ticket.onlineticket.Service.CategoryService;
import com.ticket.onlineticket.Service.SeatService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/seat")
public class SeatController {
    private SeatService seatService;
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    public SeatController(SeatService seatService, CategoryService categoryService) {
        this.seatService = seatService;
        this.categoryService = categoryService;
    }

    @GetMapping("/allSeats")
    public ResponseEntity findAll(){
        List<Seat> seat = seatService.getAll();
        return ResponseEntity.ok(seat);
    }

    @GetMapping("/getOneSeat/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Seat seat = seatService.getOne(id);
        return ResponseEntity.ok(seat);
    }

    @PostMapping("/{id}/insert")
    public ResponseEntity<?> insert(@PathVariable int id,
                                    @RequestBody SeatDto seatDto){
        Seat seat = modelMapper.map(seatDto, Seat.class);

        Optional<Category> category = categoryService.findOne(id);
        seat.setCategory(category.get());
        category.get().addSeat(seat);

        Seat seat1 = seatService.create(seat);
        SeatDto seatDto1 = modelMapper.map(seat1, SeatDto.class);

        return ResponseEntity.ok(seat);
    }

    //Thymeleaf goes down
    @GetMapping("/allSeat")
    public String getAll(Model model){
        model.addAttribute("allSeat",seatService.getAll());
        return "seat";
    }

}