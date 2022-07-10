package com.example.demo.service;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodType;
import com.example.demo.repository.FoodListRepository;
import com.example.demo.repository.FoodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodServiceImpl implements FoodService{
    @Autowired
    private FoodListRepository foodListRepository;
    @Autowired
    private FoodTypeRepository foodTypeRepository;

    @Override
    public List<FoodList> getAllFood() {
        return foodListRepository.findAll();
    }
    @Override
    public List<FoodList> getFoodByCategory(FoodType foodType) {
        return foodListRepository.findByFoodType(foodType);
    }
    @Override
    public List<FoodType> getAllTypes() {
        return foodTypeRepository.findAll();
    }
    @Override
    public FoodList findById(long id) {
        return foodListRepository.findById(id);
    }
}
