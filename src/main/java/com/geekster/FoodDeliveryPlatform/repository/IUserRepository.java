package com.geekster.FoodDeliveryPlatform.repository;

import com.geekster.FoodDeliveryPlatform.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUserRepository extends JpaRepository<Users,Long> {
    Users findFirstByEmail(String email);
}
