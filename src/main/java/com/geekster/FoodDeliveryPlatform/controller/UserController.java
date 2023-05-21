package com.geekster.FoodDeliveryPlatform.controller;

import com.geekster.FoodDeliveryPlatform.dto.SignInInput;
import com.geekster.FoodDeliveryPlatform.dto.SignInOutput;
import com.geekster.FoodDeliveryPlatform.dto.SignUpInput;
import com.geekster.FoodDeliveryPlatform.dto.SignUpOutput;
import com.geekster.FoodDeliveryPlatform.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/signup")
    public SignUpOutput signUp(@RequestBody SignUpInput signUpDto){
        return userService.signUp(signUpDto);
    }

    @PostMapping("/signin")
    public SignInOutput signIn(@RequestBody SignInInput signInDto){
        return userService.signIn(signInDto);
    }

}
