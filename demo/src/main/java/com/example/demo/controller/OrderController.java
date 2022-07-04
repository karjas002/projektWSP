package com.example.demo.controller;

import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/kitchen")
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
        System.out.println(foodOrderedList);

        List <String> testList = new ArrayList<>();
        String zamowienie = "";
        for (int i = 0; i < foodOrderedList.size(); i++){
            List<String> tempList = new ArrayList<>();
            for (int l = 0; l < foodOrderedList.get(i).size(); l++){
                zamowienie = String.join(" " , zamowienie, foodOrderedList.get(i).get(l).getFood().getFood_name());
                zamowienie = String.join("", zamowienie, " (x", foodOrderedList.get(i).get(l).getCounter().toString(),") ");
                }
                System.out.println(zamowienie);
                testList.add(zamowienie);
                System.out.println(tempList);
                zamowienie = "";
        }
        System.out.println(testList);
        model.addAttribute("showOrders", orders);
        model.addAttribute("showFood", foodOrderedList);
        model.addAttribute("showProducts", testList);

        return "kitchen";
    }

    @GetMapping("/updateOrder/{id}")
    public String updateOrder(@PathVariable (value = "id") long id) {
        Order order = orderService.findOrder(id);
        order.setMaking(false);
        System.out.println("------------------------------------------");
        System.out.println(order);
        orderService.saveOrder(order);
        return "redirect:/kitchen";
    }

    @GetMapping("deleteOrder/{id}")
    public String deleteOrder(@PathVariable (value = "id") long id){
        Order order = orderService.findOrder(id);
        orderService.deleteOrder(order);
        return "redirect:/kitchen";
    }
}
