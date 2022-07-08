package com.example.demo.service;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodType;

import java.util.List;

public interface FoodService {
    List<FoodList> getAllFood();
    List<FoodList> getFoodByCategory(FoodType foodType);
    List<FoodType> getAllTypes();

}
