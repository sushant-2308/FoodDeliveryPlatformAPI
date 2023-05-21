package com.geekster.FoodDeliveryPlatform.dto;

import com.geekster.FoodDeliveryPlatform.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SignUpInput {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    private String userName;
    @NotBlank
    private String password;
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private Role role;
}
