package com.geekster.FoodDeliveryPlatform.repository;

import com.geekster.FoodDeliveryPlatform.model.AuthenticationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITokenRepository extends JpaRepository<AuthenticationToken,Long> {
    AuthenticationToken findFirstByToken(String token);
}
