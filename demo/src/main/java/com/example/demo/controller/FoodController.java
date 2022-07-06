package com.example.demo.controller;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodType;
import com.example.demo.service.FoodService;
import com.example.demo.service.FoodTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class FoodController {

    @Autowired
    private FoodService foodService;

    @Autowired
    private FoodTypeService foodTypeService;

    @GetMapping("/")
    public String homePage(Model model) {
        return showFood(0, model);
    }

    @GetMapping("/{id}")
    public String showFood(@PathVariable (value = "id") long id, Model model) {

        List<FoodList> foodList = new ArrayList<>();
        if(id > 0) {
            FoodType foodType = foodTypeService.getType(id);
            foodList = foodService.getFoodByCategory(foodType);

        } else
            foodList = foodService.getAllFood();

        //List<FoodType> foodTypeList = foodService.getAllTypes();
        List<FoodType> foodTypeList = foodService.getAllTypes();
        model.addAttribute("foodList", foodList);
        model.addAttribute("foodTypeList", foodTypeList);
        return  "index";
    }
}
