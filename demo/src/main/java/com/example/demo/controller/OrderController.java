package com.example.demo.controller;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;
import com.example.demo.service.FoodOrderedService;
import com.example.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private FoodOrderedService foodOrderedService;

    @GetMapping("/kitchen")
    public String showOrders (Model model){
        //return orderService.getToDoOrders();
        List <Order> orders = new ArrayList<Order>();
        orders.addAll(orderService.getToDoOrders());
        System.out.println("ORDERSY: "+orders);
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

        for (int i=0; i < lista_zamowien.size(); i++){
            fullPrice = fullPrice + lista_zamowien.get(i).getPrice();
            countElements++;
        }

        model.addAttribute("showOrdered", lista_zamowien);
        model.addAttribute("showPrice", fullPrice);
        model.addAttribute("showCount", countElements);
        return "summary";
    }
    public List <FoodList> lista_zamowien = new ArrayList<>();

    @GetMapping("/addingFoodtemp")
    public String addingFood(Model model){
        model.addAttribute("food", new FoodList());



        return "addingFoodtemp";
    }

    @PostMapping("/addingFoodtemp")
    public String addingFood(@ModelAttribute("food") FoodList foodList, Model model){

        lista_zamowien.add(foodList);
        System.out.println(lista_zamowien);
        //System.out.println("TU JEST FOODLIISTA -----------------------------"+foodList);
        return "redirect:/";
    }

    @GetMapping("index")
    public String getPriceandCount(Model model){

        double fullPrice = 0;
        int countElements = 0;

        for (int i=0; i < lista_zamowien.size(); i++){
            fullPrice = fullPrice + lista_zamowien.get(i).getPrice();
            countElements++;
        }

        //model.addAttribute("showOrdered", lista_zamowien);
        model.addAttribute("showPrice", fullPrice);
        System.out.println("Tu jest CENA: "+fullPrice);
        model.addAttribute("showCount", countElements);
        return "index";
    }

    @GetMapping("/deleteOrdered")
    public String deleteOrdered(){
        lista_zamowien.clear();
        return "redirect:/";
    }

    @GetMapping("/payment")
    public String makeOrder(){
        Order new_order = new Order(0l, false, false);
        Order new_order2 = orderService.saveOrder(new_order); // tak sie robi zeby ściągnąc ID bo inaczej jest chujnia i sciąga id_order=0

        System.out.println("ID ORDER - "+new_order2.getId_order());
        for(int i = 0; i < lista_zamowien.size(); i++){
            //wrzucanko do foodordered narazie bez countera bo ni chuja nie wiem jak to zrobić
            FoodOrdered foodOrdered = new FoodOrdered(0l, lista_zamowien.get(i), new_order2, 1);
            foodOrderedService.saveOrderedFood(foodOrdered);
        }

        lista_zamowien.clear();
        return "payment";
    }

}
