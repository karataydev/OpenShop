package com.karatay.openshop.service;


import com.karatay.openshop.model.Cart;
import com.karatay.openshop.model.ProductPair;
import com.karatay.openshop.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CartService {

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    public CartService(CartRepository cartRepository, UserService userService, ProductService productService) {
        this.cartRepository = cartRepository;
        this.userService = userService;
        this.productService = productService;
    }

    public void addToCart(Long id, Integer quantity){
        Cart cart = userService.getUser().getCart();
        cart.addToCart(new ProductPair(productService.getProductById(id), quantity) );
        cartRepository.save(cart);
    }

    public void removeFromCart(Long id){
        Cart cart = userService.getUser().getCart();
        cart.removeFromCart(productService.getProductById(id));
        cartRepository.save(cart);
    }

    public void updateCart(Long id, Integer quantity){
        Cart cart = userService.getUser().getCart();
        cart.updateQuantity(productService.getProductById(id), quantity);
        cartRepository.save(cart);
    }

    public void save(Cart cart){
        cartRepository.save(cart);
    }

    public void delete(Cart cart){
        cartRepository.delete(cart);
    }


}
