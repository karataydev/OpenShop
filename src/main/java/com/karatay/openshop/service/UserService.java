package com.karatay.openshop.service;

import com.karatay.openshop.model.Address;
import com.karatay.openshop.model.User;
import com.karatay.openshop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(){
       return userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
    }

    public void addAddress(String name, String address){
        User user = getUser();
        user.addAddress(new Address(name,address));
        userRepository.save(user);
    }

    public void deleteAddress(Long id){
        User user = getUser();
        user.deleteAddress(id);
        userRepository.save(user);
    }

    public void save(User user){
        userRepository.save(user);
    }
}
