package com.geekster.FoodDeliveryPlatform.service;

import com.geekster.FoodDeliveryPlatform.model.AuthenticationToken;
import com.geekster.FoodDeliveryPlatform.model.Order;
import com.geekster.FoodDeliveryPlatform.model.OrderStatus;
import com.geekster.FoodDeliveryPlatform.repository.IOrderRepository;
import com.geekster.FoodDeliveryPlatform.repository.ITokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    IOrderRepository iOrderRepository;

    @Autowired
    ITokenRepository iTokenRepository;

    public void placeOrder(Order order, String token) {
        AuthenticationToken token1 = iTokenRepository.findFirstByToken(token);
        order.setStatus(OrderStatus.valueOf("CREATED"));
        order.setUsers(token1.getUser());
        iOrderRepository.save(order);
    }

    public List<Order> getOrders(String email, String token) {
        AuthenticationToken token1 = iTokenRepository.findFirstByToken(token);
        if(token1.getUser().getRole().getRoleId()==1){
            return iOrderRepository.findAll();
        }else{
            List<Order> orderList = new ArrayList<>();
            orderList = iOrderRepository.findByUsers(token1.getUser());
            return orderList;
        }
    }

    public void updateOrder(String email, String token , Order order) {
        AuthenticationToken token1 = iTokenRepository.findFirstByToken(token);
        if (order.getUsers() == token1.getUser() || token1.getUser().getRole().getRoleId() == 1) {
            if (order.getOrderId() != null) {
                Optional<Order> optionalOrder = iOrderRepository.findById(order.getOrderId());
                if (optionalOrder.isPresent()) {
                    Order oldOrder = optionalOrder.get();
                    if (order.getFood() != null) {
                        oldOrder.setFood(order.getFood());
                    }
                    if (order.getQuantity() != null) {
                        oldOrder.setQuantity(order.getQuantity());
                    }
                    if (order.getStatus() != null && token1.getUser().getRole().getRoleId() == 1) {
                        oldOrder.setStatus(order.getStatus());
                    }
                    iOrderRepository.save(oldOrder);
                } else {
                    throw new IllegalStateException("No such order exists");
                }
            } else {
                throw new IllegalStateException("Enter Order Id");
            }

        } else {
            throw new IllegalStateException("It's not your Order");
        }
    }
}
