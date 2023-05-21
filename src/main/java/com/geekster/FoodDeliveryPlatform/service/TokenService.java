package com.geekster.FoodDeliveryPlatform.service;

import com.geekster.FoodDeliveryPlatform.model.AuthenticationToken;
import com.geekster.FoodDeliveryPlatform.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    @Autowired
    ITokenRepository iTokenRepository;
    public void saveToken(AuthenticationToken token) {
        iTokenRepository.save(token);
    }

    public boolean authenticate(String email, String token) {

        if(token==null && email==null){
            return false;
        }

        AuthenticationToken authToken = iTokenRepository.findFirstByToken(token);

        if(authToken==null){
            return false;
        }

        String expectedEmail = authToken.getUser().getEmail();


        return expectedEmail.equals(email);
    }


    public void deleteToken(String token) {
        AuthenticationToken token1 = iTokenRepository.findFirstByToken(token);

        iTokenRepository.deleteById(token1.getTokenId());
    }
}
