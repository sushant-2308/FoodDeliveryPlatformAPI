package com.geekster.FoodDeliveryPlatform.controller;

import com.geekster.FoodDeliveryPlatform.model.Food;
import com.geekster.FoodDeliveryPlatform.service.FoodService;
import com.geekster.FoodDeliveryPlatform.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodController {
    @Autowired
    FoodService foodService;

    @Autowired
    TokenService authService;

    @PostMapping("/{email}/{token}")
    public ResponseEntity<String> addFood(@PathVariable String email , @PathVariable String token , @RequestBody Food food){
        HttpStatus status;
        String msg = "";
        if(authService.authenticate(email,token))
        {
            foodService.addFood(food , token);
            msg = "Food added successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @DeleteMapping("/{email}/{token}/{id}")
    public ResponseEntity<String> deletedFood(@PathVariable Long id , @PathVariable String email , @PathVariable String token){
        HttpStatus status;
        String msg = "";
        if(authService.authenticate(email,token))
        {
            foodService.deleteFood(id , token);
            msg = "Food deleted successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @GetMapping("/{email}/{token}/{foodName}")
    public List<Food> getAllFood(@Nullable @PathVariable String foodName){
        return foodService.getFood(foodName);
    }
}

