package com.ticket.onlineticket.Controller;

import com.ticket.onlineticket.Domain.User;
import com.ticket.onlineticket.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private ModelMapper modelMapper;

    private final UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    //responseEntity
    @PostMapping("/insertion")
    public ResponseEntity create(@RequestBody User user){
        if(!checkPasswordLength(user.getPassword())){
            return new ResponseEntity("password length is short", HttpStatus.BAD_REQUEST);
        }
        if(userService.checkUserName(user.getUserName())){
            return new ResponseEntity("Account exists",HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok(userService.create(user));
    }

    @GetMapping("/findAll")
    public ResponseEntity findAll(){
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity getOne(@PathVariable Long id){
        Optional<User> user = userService.findOne(id);
        return ResponseEntity.ok(user);
    }



    //Thymeleaf
    @PostMapping("/create")
    public String createAccount(@ModelAttribute("user") User user){
        if(!checkPasswordLength(user.getPassword())){
            return "password length is short";
        }
        if(userService.checkUserName(user.getUserName())){
            return "Account exists";
        }
        User user1 = userService.create(user);
        return "redirect:/";
    }

    @GetMapping("/createAccount")
    public String getAcc(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "createAccount";
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok("File deleted");
    }

    private Boolean checkPasswordLength(String password){
        return password.length() > 4;
    }
}
