package com.geekster.FoodDeliveryPlatform.service;

import com.geekster.FoodDeliveryPlatform.model.Role;
import com.geekster.FoodDeliveryPlatform.repository.IRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RoleService {

    @Autowired
    IRoleRepository iRoleRepository;

    public boolean validateUserRole(String email, Role role) {
        if((role.getRoleId()==1)){
            Pattern p = Pattern.compile("^.*@admin\\.com$");
            Matcher m = p.matcher(email);
            if( (m.find() && m.group().equals(email))){

                return true;

            }else{
                return false;
            }
        }else{
            if(role.getRoleId()==2){
                return true;
            }else{
                return false;
            }
        }

    }

    public String addRole(String email, Role role) {
        if(AdminService.isValidEmail(email)){
            iRoleRepository.save(role);
            return "Role added successfully";
        }else{
            return "You don't have access to add roles";
        }
    }
}
