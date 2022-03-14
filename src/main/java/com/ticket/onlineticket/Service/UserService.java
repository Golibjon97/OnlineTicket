package com.ticket.onlineticket.Service;

import com.ticket.onlineticket.Domain.User;
import com.ticket.onlineticket.Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User create(User user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Optional<User> findOne(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public Boolean checkUserName(String userName){
        return userRepository.existsByUserName(userName);
    }

    public void delete(Long id){
        User user = userRepository.getOne(id);
        userRepository.delete(user);
    }

}
