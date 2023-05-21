package com.geekster.FoodDeliveryPlatform.repository;

import com.geekster.FoodDeliveryPlatform.model.Food;
import com.geekster.FoodDeliveryPlatform.service.FoodService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IFoodRepository extends JpaRepository<Food,Long> {
    List<Food> findByFoodName(String foodName);
}
