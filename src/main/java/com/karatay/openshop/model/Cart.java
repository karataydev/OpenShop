package com.karatay.openshop.model;

import javax.persistence.*;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL)
    private List<ProductPair> productPairs;

    private Double price;

    public Cart() {
        this.price = 0.0;
    }

    public Cart(List<ProductPair> productPairs) {
        this.productPairs = productPairs;
        Double cost = 0.0;
        for(ProductPair pr: productPairs){
            cost += pr.getProduct().getPrice()*pr.getQuantity();
        }
        this.price = cost;
    }

    public Long getId() {
        return id;
    }

    public void addToCart(ProductPair productPair){
        this.productPairs.add(productPair);
        Double cost = 0.0;
        for(ProductPair pr: productPairs){
            cost += pr.getProduct().getPrice()*pr.getQuantity();
        }
        this.price = cost;

    }
    public void removeFromCart(Product product){
        this.productPairs.removeIf(productPair -> (productPair.getProduct().equals(product)));
        Double cost = 0.0;
        for(ProductPair pr: productPairs){
            cost += pr.getProduct().getPrice()*pr.getQuantity();
        }
        this.price = cost;

    }
    public void updateQuantity(Product product, Integer quantity){
        this.productPairs.stream().filter(productPair -> (productPair.getProduct().equals(product))).collect(Collectors.toList()).get(0).setQuantity(quantity);

        Double cost = 0.0;
        for(ProductPair pr: productPairs){
            cost += pr.getProduct().getPrice()*pr.getQuantity();
        }
        this.price = cost;

    }


    public void setId(Long id) {
        this.id = id;
    }

    public List<ProductPair> getProductPairs() {
        return productPairs;
    }

    public void setProductPairs(List<ProductPair> productPairs) {
        this.productPairs = productPairs;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
