package com.example.demo.service;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;
import com.example.demo.repository.FoodListRepository;
import com.example.demo.repository.FoodOrderedRepository;
import com.example.demo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private FoodListRepository foodListRepository;

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
    @Override
    public FoodList findById (long Id){
        return foodListRepository.findById(Id);
    }
    @Override
    public List<FoodList> findAll(){
        return foodListRepository.findAll();
    }
}
