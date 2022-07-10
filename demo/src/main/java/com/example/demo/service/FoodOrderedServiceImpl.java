package com.example.demo.service;

import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.repository.FoodOrderedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodOrderedServiceImpl implements FoodOrderedService{

    @Autowired
    private FoodOrderedRepository foodOrderedRepository;

    public FoodOrdered saveOrderedFood (FoodOrdered foodOrdered){
        return foodOrderedRepository.save(foodOrdered);
    }
}
