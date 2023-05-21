package com.geekster.FoodDeliveryPlatform.service;

import com.geekster.FoodDeliveryPlatform.model.AuthenticationToken;
import com.geekster.FoodDeliveryPlatform.model.Food;
import com.geekster.FoodDeliveryPlatform.repository.IFoodRepository;
import com.geekster.FoodDeliveryPlatform.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodService {

    @Autowired
    IFoodRepository iFoodRepository;

    @Autowired
    ITokenRepository iTokenRepository;

    public void addFood(Food food, String token) {
        AuthenticationToken token1 = iTokenRepository.findFirstByToken(token);
        if(token1.getUser().getRole().getRoleId()==1){
            iFoodRepository.save(food);
        }
    }

    public List<Food> getFood(String foodName) {
        if(foodName==null){
            return iFoodRepository.findAll();
        }else{

            return iFoodRepository.findByFoodName(foodName);
        }
    }

    public void deleteFood(Long id, String token) {
        AuthenticationToken token1 = iTokenRepository.findFirstByToken(token);
        if(token1.getUser().getRole().getRoleId()==1){
            iFoodRepository.deleteById(id);
        }
    }
}
