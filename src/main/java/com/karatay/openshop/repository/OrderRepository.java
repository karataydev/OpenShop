package com.karatay.openshop.repository;

import com.karatay.openshop.model.Order;
import com.karatay.openshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findOrdersByUser(User user);
}
