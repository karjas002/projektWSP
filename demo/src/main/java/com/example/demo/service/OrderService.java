package com.example.demo.service;

import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;

import java.util.List;

public interface OrderService {

        List<Order> getAllOrders();
        List<Order> getToDoOrders();
        List<FoodOrdered> getOrderedFood(Order order);
        Order findOrder(long id);
        Order saveOrder(Order order);
        void deleteOrder(Order order);
}
