package com.geekster.FoodDeliveryPlatform.repository;

import com.geekster.FoodDeliveryPlatform.model.Order;
import com.geekster.FoodDeliveryPlatform.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IOrderRepository extends JpaRepository<Order,Long> {


    List<Order> findByUsers(Users user);
}
