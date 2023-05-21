package com.geekster.FoodDeliveryPlatform.repository;

import com.geekster.FoodDeliveryPlatform.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRoleRepository extends JpaRepository<Role,Long> {
}
