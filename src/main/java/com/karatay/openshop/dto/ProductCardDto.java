package com.karatay.openshop.dto;

import com.karatay.openshop.model.Category;

public class ProductCardDto {
    public Long id;
    public String name;
    public String pictureUrl;
    public Double price;
    public Category category;

    public ProductCardDto(Long id, String name, String pictureUrl, Double price, Category category) {
        this.id = id;
        this.name = name;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.category = category;
    }
}
