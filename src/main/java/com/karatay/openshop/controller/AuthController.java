package com.karatay.openshop.controller;

import com.karatay.openshop.dto.LoginForm;
import com.karatay.openshop.dto.RegisterForm;
import com.karatay.openshop.model.Cart;
import com.karatay.openshop.model.ERole;
import com.karatay.openshop.model.Role;
import com.karatay.openshop.model.User;
import com.karatay.openshop.repository.CartRepository;
import com.karatay.openshop.repository.RoleRepository;
import com.karatay.openshop.repository.UserRepository;
import com.karatay.openshop.service.UserDetailsImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.sql.RowSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller()
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder encoder, CartRepository cartRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.encoder = encoder;
        this.cartRepository = cartRepository;
    }

    @GetMapping("/login")
    public String loginPage(){return "/login";}

    @GetMapping("/register")
    public String registerPage(){return "/register";}


    @PostMapping(path = "/login-submit")
    public String submitLogin(@ModelAttribute LoginForm loginForm){

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginForm.getUsername(), loginForm.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImp userDetails = (UserDetailsImp) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return "redirect:/";
    }

    @PostMapping(path = "/register-submit")
    public String submitRegister(@ModelAttribute RegisterForm registerForm){

        //TO-DO


        User user = new User(registerForm.getUsername(),
                registerForm.getEmail(),
                encoder.encode(registerForm.getPassword()),
                registerForm.getFirstName(),
                registerForm.getLastName());
        Set<Role> rolesSet = new HashSet<>();
        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        rolesSet.add(userRole);
        user.setRoles(rolesSet);

        Cart cart = new Cart();
        cartRepository.save(cart);
        user.setCart(cart);
        userRepository.save(user);

        return "redirect:/login";


    }

    @PostMapping(path = "/update-user")
    public String updateUser(@ModelAttribute RegisterForm registerForm){
        User user = userRepository.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow();
        if(!user.getFirstName().equals(registerForm.getFirstName())){
            user.setFirstName(registerForm.getFirstName());
        }
        if(!user.getLastName().equals(registerForm.getLastName())){
            user.setLastName(registerForm.getLastName());
        }
        if(!user.getUsername().equals(registerForm.getUsername())){
            user.setUsername(registerForm.getUsername());
        }
        if(!user.getEmail().equals(registerForm.getEmail())){
            user.setEmail(registerForm.getEmail());
        }
        if(!user.getPassword().equals(registerForm.getPassword())){
            user.setPassword(encoder.encode(registerForm.getPassword()));
        }
        userRepository.save(user);
        return "redirect:/logout";

    }
}
