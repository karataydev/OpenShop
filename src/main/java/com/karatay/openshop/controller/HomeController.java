package com.karatay.openshop.controller;

import com.karatay.openshop.dto.ProductCardDto;
import com.karatay.openshop.dto.RegisterForm;
import com.karatay.openshop.model.*;
import com.karatay.openshop.repository.*;
import com.karatay.openshop.service.*;
import org.aspectj.weaver.ast.Or;
import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class HomeController {

    @Autowired
    CategoryService categoryService;

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;

    @Autowired
    CartService cartService;

    @Autowired
    OrderService orderService;


    public HomeController(CategoryService categoryService, ProductService productService, UserService userService, CartService cartService, OrderService orderService) {
        this.categoryService = categoryService;
        this.productService = productService;
        this.userService = userService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @GetMapping
    public String getHomePage(Model model){
        model.addAttribute("category", categoryService.getCategories());
        return "/home";
    }

    @GetMapping(path = "/search")
    public String searchProducts(@RequestParam("keyword") String keyword, Model model){
        model.addAttribute("products", productService.searchWithKeyword(keyword));
        return "/products";
    }

    @GetMapping(path = "/profile")
    public String profilePage(Model model){
        model.addAttribute("user", userService.getUser());
        return "/profile";
    }

    @GetMapping(path="/products/category/{category}")
    public String getProductsByCategory(@PathVariable("category") String category, Model model){
        model.addAttribute("products", productService.getProductsByCategory(category));
        return "/products";
    }
    @GetMapping(path="/product/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model){
        model.addAttribute("product", productService.getProductById(id));
        return "/productDetails";
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping(path="/cart")
    public String getCart(Model model){
        model.addAttribute("cart", userService.getUser().getCart());
        model.addAttribute("user", userService.getUser());
        return "/cart";
    }

    @PostMapping(path = "/add-to-cart")
    public String addProductToCart(@RequestParam("id") Long id, @RequestParam("quantity") Integer quantity){
        cartService.addToCart(id, quantity);
        return "redirect:/";
    }
    @PostMapping(path = "/delete-from-cart")
    public String removeFromCart(@RequestParam("id") Long id){
        cartService.removeFromCart(id);
        return "redirect:/cart";
    }


    @PostMapping(path = "/update-cart")
    public String updateCart(@RequestParam("id") Long id, Integer quantity){
        cartService.updateCart(id, quantity);
        return "redirect:/cart";
    }

    @PostMapping(path = "/delete-address")
    public String deleteAddress(@RequestParam("id") Long id){
        userService.deleteAddress(id);
        return "redirect:/profile";
    }

    @PostMapping(path = "/add-address")
    public String addAddress(@RequestParam("name") String name, @RequestParam("address") String address){
        userService.addAddress(name, address);
        return "redirect:/profile";
    }

    @PostMapping(path = "/order")
    public String order(@RequestParam("addressId") Long addressId){
        orderService.order(addressId);
        return "redirect:/";

    }

    @GetMapping(path = "/my-orders")
    public String orderPage(Model model){
        model.addAttribute("orders", orderService.getOrders());
        return "/orders";
    }






}
