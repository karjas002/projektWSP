package com.example.demo.controller;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @GetMapping("summary")
//    public String getSummary(Model model, List<FoodList> dupa)
    public String getSummary(Model model){

        // do wyjebania jak będziemy ściągać liste z porpzedniego widoku

        List<FoodList> zamowione_jedzenie = new ArrayList<>();

        zamowione_jedzenie.add(orderService.findById(1));
        zamowione_jedzenie.add(orderService.findById(1));
        zamowione_jedzenie.add(orderService.findById(3));
        zamowione_jedzenie.add(orderService.findById(2));

        System.out.println(zamowione_jedzenie);

        // do wyjebania jak będziemy ściągać liste z porpzedniego widoku

        double fullPrice = 0;
        int countElements = 0;

        for (int i=0; i < zamowione_jedzenie.size(); i++){
            fullPrice = fullPrice + zamowione_jedzenie.get(i).getPrice();
            countElements++;
        }

        model.addAttribute("showOrdered", zamowione_jedzenie);
        model.addAttribute("showPrice", fullPrice);
        model.addAttribute("showCount", countElements);
        return "summary";
    }



}
