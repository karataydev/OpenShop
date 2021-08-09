package com.karatay.openshop.service;


import com.karatay.openshop.model.*;
import com.karatay.openshop.repository.AddressRepository;
import com.karatay.openshop.repository.CartRepository;
import com.karatay.openshop.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserService userService;

    @Autowired
    AddressRepository addressRepository;

    @Autowired
    CartService cartService;

    @Autowired
    ProductService productService;


    public OrderService(OrderRepository orderRepository, UserService userService, AddressRepository addressRepository, CartService cartService, ProductService productService) {
        this.orderRepository = orderRepository;
        this.userService = userService;
        this.addressRepository = addressRepository;
        this.cartService = cartService;
        this.productService = productService;
    }

    public void order(Long addressId){
        User user = userService.getUser();
        Cart cart = user.getCart();
        Address address = addressRepository.getById(addressId);
        Cart cart2 = new Cart(cart.getProductPairs().stream().toList());
        Order order = new Order(cart2, address, user);
        user.setCart(new Cart());
        for(ProductPair productPair: cart.getProductPairs()){
            Product pr = productService.getProductById(productPair.getProduct().getId());
            pr.setStock(pr.getStock() - productPair.getQuantity());
            pr.setSold(pr.getSold() + productPair.getQuantity());
            productService.save(pr);
        }
        cartService.save(cart2);
        cartService.delete(cart);
        userService.save(user);
        orderRepository.save(order);
    }

    public List<Order> getOrders(){
        return orderRepository.findOrdersByUser(userService.getUser());
    }
}
