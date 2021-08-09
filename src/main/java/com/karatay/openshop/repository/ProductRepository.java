package com.karatay.openshop.repository;

import com.karatay.openshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> getProductsByCategoryName(String categoryName);

    Product getProductById(Long id);

    List<Product> findProductsByNameContains(String keyword);
}
