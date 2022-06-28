package com.example.demo.service;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;

import java.util.List;

public interface OrderService {

        List<Order> getAllOrders();
        List<Order> getToDoOrders();
        //List<FoodOrdered> getOrderedFood(Long id_order);
}
