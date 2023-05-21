package com.geekster.FoodDeliveryPlatform.service;

import com.geekster.FoodDeliveryPlatform.dto.SignInInput;
import com.geekster.FoodDeliveryPlatform.dto.SignInOutput;
import com.geekster.FoodDeliveryPlatform.dto.SignUpInput;
import com.geekster.FoodDeliveryPlatform.dto.SignUpOutput;
import com.geekster.FoodDeliveryPlatform.model.AuthenticationToken;
import com.geekster.FoodDeliveryPlatform.model.Users;
import com.geekster.FoodDeliveryPlatform.repository.IRoleRepository;
import com.geekster.FoodDeliveryPlatform.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

@Service
public class UserService {

    @Autowired
    IUserRepository iUserRepository;

    @Autowired
    TokenService tokenService;

    @Autowired
    RoleService roleService;


    public SignUpOutput signUp(SignUpInput signUpDto) {
        Users user = iUserRepository.findFirstByEmail(signUpDto.getEmail());

        if(user!=null){
            throw new IllegalStateException("User already exists!!!!...sign in instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signUpDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        if(!(roleService.validateUserRole(signUpDto.getEmail() , signUpDto.getRole()))){
            throw new IllegalStateException("Enter valid Details");
        }

        user = new Users(signUpDto.getUserName(), encryptedPassword , signUpDto.getEmail(),
                signUpDto.getRole());

        iUserRepository.save(user);

        return new SignUpOutput("App User registered","App user account created successfully");

    }

    private String encryptPassword(String userPassword) throws NoSuchAlgorithmException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");

        md5.update(userPassword.getBytes());
        byte[] digested = md5.digest();

        String hash = DatatypeConverter.printHexBinary(digested);

        return hash;
    }

    public SignInOutput signIn(SignInInput signInDto) {
        Users user = iUserRepository.findFirstByEmail(signInDto.getEmail());

        if(user==null){
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        String encryptedPassword = null;

        try {
            encryptedPassword = encryptPassword(signInDto.getPassword());
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();

        }

        //match it with database encrypted password

        boolean isPasswordValid = encryptedPassword.equals(user.getPassword());

        if(!isPasswordValid)
        {
            throw new IllegalStateException("User invalid!!!!...sign up instead");
        }

        AuthenticationToken token = new AuthenticationToken(user);

        tokenService.saveToken(token);

        //set up output response
        return new SignInOutput("Authentication Successful !!!", token.getToken());
    }
}
