package com.karatay.openshop.service;


import com.karatay.openshop.dto.ProductCardDto;
import com.karatay.openshop.model.Product;
import com.karatay.openshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductCardDto> getProductsByCategory(String categoryName){
        return productRepository.getProductsByCategoryName(categoryName).stream().map(item -> {return new ProductCardDto(item.getId(),item.getName(),item.getPictureUrl().get(0),item.getPrice(),item.getCategory());}).collect(Collectors.toList());
    }

    public Product getProductById(Long id){
        return productRepository.getProductById(id);
    }

    public List<ProductCardDto> searchWithKeyword(String keyword){
        return productRepository.findProductsByNameContains(keyword).stream().map(item -> {return new ProductCardDto(item.getId(),item.getName(),item.getPictureUrl().get(0),item.getPrice(),item.getCategory());}).collect(Collectors.toList());
    }

    public void save(Product product){
        productRepository.save(product);
    }


}
