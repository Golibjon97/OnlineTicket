package com.ticket.onlineticket.Controller;

import com.ticket.onlineticket.Domain.Concert;
import com.ticket.onlineticket.Dto.AvailableSeatDto;
import com.ticket.onlineticket.Dto.ConcertDto;
import com.ticket.onlineticket.Service.ConcertService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/concert")
public class ConcertController {

    @Autowired
    private ModelMapper modelMapper;

    private ConcertService concertService;

    public ConcertController(ConcertService concertService) {
        this.concertService = concertService;
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<Concert> concert = concertService.findOne(id);
        return ResponseEntity.ok(concert);
    }

    @GetMapping("/getAll")
    public ResponseEntity getAll(){
        return ResponseEntity.ok(concertService.getAll());
    }

    @PostMapping("/addConcerts")
    public ResponseEntity<ConcertDto> createDto(@RequestBody ConcertDto concertDto){
        Concert concert = modelMapper.map(concertDto, Concert.class);
        Concert concert1 = concertService.create(concert);
        ConcertDto concertDto1 = modelMapper.map(concert1, ConcertDto.class);
        return new ResponseEntity<ConcertDto>(concertDto1, HttpStatus.CREATED);
    }

    @PutMapping("/updateConcert")
    public ResponseEntity modify(@RequestBody Concert concert){
        Concert concert1 = concertService.modify(concert);
        return ResponseEntity.ok(concert1);
    }
    
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        concertService.delete(id);
        return ResponseEntity.ok("file o'chirildi");
    }

    //DTO get Available seats Down
    @GetMapping("/DTOAvailableSeat/{id}")
    public ResponseEntity DTORead(@PathVariable("id") Long concertId){
        AvailableSeatDto availableSeatDto = concertService.getAvailableSeats(concertId);
        return ResponseEntity.ok(availableSeatDto);
    }

    //THYMLEAF TEST IS WRITTEN DOWN
    @GetMapping("/addConcert")
    public String home(Model model){
        Concert concert = new Concert();
        model.addAttribute("concert", concert);
        return "addConcert";
    }

    @PostMapping("/addNewConcert")
    public String addConcert(@ModelAttribute("concert") Concert concert){
        concertService.create(concert);
        return "redirect:/";
    }

    //GetAll allConcerts admin user
    @GetMapping("/")
    public String getAll(Model model){
        model.addAttribute("listConcert", concertService.getAll());
        return "index";
    }

    //admin user
    @GetMapping("/logout")
    public String logout(){
        return "redirect:/login";
    }


}