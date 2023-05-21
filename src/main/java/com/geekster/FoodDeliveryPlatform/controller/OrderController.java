package com.geekster.FoodDeliveryPlatform.controller;

import com.geekster.FoodDeliveryPlatform.model.Order;
import com.geekster.FoodDeliveryPlatform.service.OrderService;
import com.geekster.FoodDeliveryPlatform.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    TokenService authService;

    @PostMapping("/placeOrder/{email}/{token}")
    public ResponseEntity<String> placeOrder(@PathVariable String email , @PathVariable String token , @RequestBody Order order){
        HttpStatus status;
        String msg = "";
        if(authService.authenticate(email,token))
        {
            orderService.placeOrder(order,token);
            msg = "Food ordered successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

    @GetMapping("/getOrder/{email}/{token}")
    public ResponseEntity<List<Order>> getOrders(@PathVariable String email , @PathVariable String token ){
        HttpStatus status;
        String msg = "";
        List<Order> orderList = null ;
        if(authService.authenticate(email,token))
        {
            orderList = orderService.getOrders(email , token);
            status = HttpStatus.OK;
        }
        else
        {
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<List<Order>>(orderList , status);
    }

    @PutMapping("/updateOrder/{email}/{token}")
    public ResponseEntity<String> updateOrder(@PathVariable String email , @PathVariable String token , @RequestBody Order order){
        HttpStatus status;
        String msg = "";

        if(authService.authenticate(email,token))
        {
            orderService.updateOrder(email , token , order);
            msg = "Food order updated successfully";
            status = HttpStatus.OK;
        }
        else
        {
            msg = "Invalid user";
            status = HttpStatus.FORBIDDEN;
        }

        return new ResponseEntity<String>(msg , status);
    }

}
