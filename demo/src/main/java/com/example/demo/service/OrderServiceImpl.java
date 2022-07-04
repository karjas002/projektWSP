package com.example.demo.service;

import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;
import com.example.demo.repository.FoodOrderedRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private FoodOrderedRepository foodOrderedRepository;

    @Override
    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
    @Override
    public List<Order> getToDoOrders(){
        return orderRepository.findBymakingTrue();
    }
    @Override
    public List<FoodOrdered> getOrderedFood(Order order){
        return foodOrderedRepository.findFoodOrderedByOrder(order);
    }
    @Override
    public Order findOrder(long id) { return orderRepository.findById(id);}
    @Override
    public Order saveOrder(Order order) {
        return orderRepository.save(order);
    }
    @Override
    public void deleteOrder(Order order){
        orderRepository.delete(order);
        System.out.println("Usunięto");
    }
}
