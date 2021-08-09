package com.karatay.openshop.model;

import org.hibernate.annotations.Cascade;

import javax.persistence.*;


@Entity
public class ProductPair {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne(cascade = CascadeType.ALL)
    private Product product;

    private Integer quantity;

    public ProductPair(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public ProductPair() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
