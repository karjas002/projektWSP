package com.example.demo.controller;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.FoodType;
import com.example.demo.model.entities.Order;
import com.example.demo.repository.FoodListRepository;
import com.example.demo.service.FoodOrderedService;
import com.example.demo.service.FoodService;
import com.example.demo.service.FoodTypeService;
import com.example.demo.service.OrderService;
import com.example.demo.util.FoodTemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Controller
public class OrderController {
    public List <FoodTemp> lista_zamowien = new ArrayList<>();


    @Autowired
    private OrderService orderService;
    @Autowired
    private FoodOrderedService foodOrderedService;
    @Autowired
    private FoodService foodService;
    @Autowired
    private FoodTypeService foodTypeService;

    @GetMapping("/")
    public String homePage(Model model) {
        return showFood(0, model);
    }

    @GetMapping("/{id}")
    public String showFood(@PathVariable(value = "id") long id, Model model) {

        List<FoodList> foodList = new ArrayList<>();
        if(id > 0) {
            FoodType foodType = foodTypeService.getType(id);
            foodList = foodService.getFoodByCategory(foodType);

        } else
            foodList = foodService.getAllFood();

        double fullPrice = 0;
        int countElements = 0;

        for (int i=0; i < lista_zamowien.size(); i++){
            fullPrice = fullPrice + ( lista_zamowien.get(i).getPrice() *lista_zamowien.get(i).getCounter() );
            countElements++;
        }

        //model.addAttribute("showOrdered", lista_zamowien);
        model.addAttribute("showPrice", fullPrice);
        System.out.println("Tu jest CENA: "+fullPrice);
        model.addAttribute("showCount", countElements);
        //List<FoodType> foodTypeList = foodService.getAllTypes();
        List<FoodType> foodTypeList = foodService.getAllTypes();
        model.addAttribute("foodList", foodList);
        model.addAttribute("foodTypeList", foodTypeList);
        return  "index";
    }

    @GetMapping("/kitchen")
    public String showOrders (Model model){
        //return orderService.getToDoOrders();
        List <Order> orders = new ArrayList<Order>();
        orders.addAll(orderService.findNotCollected());
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
        order.setDone(true);
        System.out.println("------------------------------------------");
        System.out.println(order);
        orderService.saveOrder(order);
        return "redirect:/kitchen";
    }



    @GetMapping("deleteOrder/{id}")
    public String deleteOrder(@PathVariable (value = "id") long id){
        Order order = orderService.findOrder(id);
        order.setCollected(true);
        orderService.saveOrder(order);
        System.out.println("COLLECTED 1 -----------------------------------------");
        return "redirect:/kitchen";
    }

    @GetMapping("summary")
//    public String getSummary(Model model, List<FoodList> dupa)
    public String getSummary(Model model){

        double fullPrice = 0;
        int countElements = 0;

        for (int i=0; i < lista_zamowien.size(); i++){
            fullPrice = fullPrice + ( lista_zamowien.get(i).getPrice() *lista_zamowien.get(i).getCounter() );
            countElements++;
        }

        model.addAttribute("showOrdered", lista_zamowien);
        model.addAttribute("showPrice", fullPrice);
        model.addAttribute("showCount", countElements);
        return "summary";
    }


    public List <FoodOrdered> lista_foodOrdered = new ArrayList<>();

    @GetMapping("/addingFoodtemp")
    public String addingFood(Model model){
        model.addAttribute("food", new FoodTemp());
        double fullPrice = 0;
        int countElements = 0;

        for (int i=0; i < lista_zamowien.size(); i++){
            fullPrice = fullPrice + ( lista_zamowien.get(i).getPrice() *lista_zamowien.get(i).getCounter() );
            countElements++;
        }
        model.addAttribute("showPrice", fullPrice);
        System.out.println("Tu jest CENA: "+fullPrice);
        model.addAttribute("showCount", countElements);

        return "addingFoodtemp";
    }

    //public List <FoodList> lista_zamowien = new ArrayList<>();

    @PostMapping("/addingFoodtemp")
    public String addingFood(@ModelAttribute("FoodTemp") FoodTemp foodTemp, Model model){



        lista_zamowien.add(foodTemp);
        lista_zamowien.stream().forEach(z -> {
            System.out.println(z.getId_food() + " " + z.getPrice() + " " + z.getCounter() + " " + z.getFood_name());
        });
       // FoodOrdered foodOrdered = new FoodOrdered();
        //System.out.println("TU JEST FOODLIISTA -----------------------------"+foodList);
        return "redirect:/";
    }

    @GetMapping("/index")
    public String getPriceandCount(Model model){


        return "index";
    }

    @GetMapping("/deleteOrdered")
    public String deleteOrdered(){
        lista_zamowien.clear();
        return "redirect:/";
    }

    public long id_zamowienia = 0;

    @GetMapping("/payment")
    public String makeOrder(){
        Order new_order = new Order(0l, false, true, false, false);
        Order new_order2 = orderService.saveOrder(new_order); // tak sie robi zeby ściągnąc ID bo inaczej jest chujnia i sciąga id_order=0

        System.out.println("ID ORDER - "+new_order2.getId_order());

        for(int i = 0; i < lista_zamowien.size(); i++){


            FoodOrdered foodOrdered = new FoodOrdered(0l, foodService.findById(lista_zamowien.get(i).getId_food()), new_order2, lista_zamowien.get(i).getCounter());
            foodOrderedService.saveOrderedFood(foodOrdered);
            //wrzucanko do foodordered narazie bez countera bo ni chuja nie wiem jak to zrobić
            //FoodOrdered foodOrdered = new FoodOrdered(0l, lista_zamowien.get(i), new_order2, 1);
            //foodOrderedService.saveOrderedFood(foodOrdered);
        }
        id_zamowienia = new_order2.getId_order();
        lista_zamowien.clear();
        return "payment";
    }

    @GetMapping("/restaurant")
    public String showDoneFood(Model model) {
        List<Order> ordersMaking = new ArrayList<>();
        ordersMaking.addAll(orderService.getToDoOrders());
        List<Order> ordersDone = new ArrayList<>();
        ordersDone.addAll(orderService.findKitchen());
        System.out.println(ordersDone);
        model.addAttribute("showMaking", ordersMaking);
        model.addAttribute("showDone", ordersDone);

        return "restaurant";
    }

    @GetMapping("/editOrdertemp")
    public String editOrder(Model model){
        model.addAttribute("FoodTemp", new FoodTemp());


        return "editOrdertemp";
    }

    //public List <FoodList> lista_zamowien = new ArrayList<>();

    @PostMapping("/editOrdertemp")
    public String editOrder(@ModelAttribute("FoodTemp") FoodTemp foodTemp, Model model){

        System.out.println("Foodtemp: " + foodTemp.getId_food() + " " + foodTemp.getFood_name());
        int id = lista_zamowien.indexOf(foodTemp.getId_food());
        System.out.println("id: " + id);
        for (int i=0; i < lista_zamowien.size(); i++){
            if(lista_zamowien.get(i).getId_food() == foodTemp.getId_food()){
                lista_zamowien.get(i).setCounter(foodTemp.getCounter());
            }
        }

        //lista_zamowien.get(id).setCounter(foodTemp.getCounter());
        //lista_zamowien.get(Math.toIntExact(foodTemp.getId_food())).setCounter(foodTemp.getCounter());
        //lista_zamowien.add(foodTemp);
        //System.out.println("dddduuuuuuuuuuupppppppaaaaaaaaaaaaaa   "+lista_zamowien);
        lista_zamowien.stream().forEach(z -> {
            System.out.println(z.getId_food() + " " + z.getPrice() + " " + z.getCounter() + " " + z.getFood_name());
        });
        // FoodOrdered foodOrdered = new FoodOrdered();
        //System.out.println("TU JEST FOODLIISTA -----------------------------"+foodList);
        return "redirect:/summary";
    }

    @GetMapping("success")
    public String showIdOrder(Model model){
        model.addAttribute("id_zamowienia", id_zamowienia);
        return "success";
    }

}
