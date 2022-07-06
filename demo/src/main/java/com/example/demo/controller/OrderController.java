package com.example.demo.controller;

import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

    @Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/showOrders")
    public String showOrders (Model model){
        //return orderService.getToDoOrders();
        List <Order> orders = new ArrayList<Order>();
        orders.addAll(orderService.getToDoOrders());
        System.out.println(orders);
        List<List<FoodOrdered>> foodOrderedList = new ArrayList<>();
        for (Order o : orders){
            //System.out.println(orderService.getOrderedFood(o));
            foodOrderedList.add(orderService.getOrderedFood(o));
        }
        System.out.println("LISTA ZAMÓWIEN");
        System.out.println(foodOrderedList);
        //System.out.println(foodOrderedList);
        //System.out.println(orderService.getOrderedFood(orders.));
        //System.out.println(orders);

        //FoodOrdered dupa = (FoodOrdered) orderService.getOrderedFood(orders.get(0));
        //foodOrderedList.add(orderService.getOrderedFood(orders.get(0)));
        //System.out.println(foodOrderedList);
        model.addAttribute("showOrders", orders);

        return "showOrders";
    }
}
