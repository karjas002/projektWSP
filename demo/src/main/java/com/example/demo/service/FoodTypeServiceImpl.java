package com.example.demo.service;

import com.example.demo.model.entities.FoodType;
import com.example.demo.repository.FoodTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodTypeServiceImpl implements FoodTypeService {

    @Autowired
    private FoodTypeRepository foodTypeRepository;
    @Override
    public FoodType getType(long id) {
        Optional<FoodType> optional = foodTypeRepository.findById(id);
        FoodType foodType = null;
        if (optional.isPresent()) {
            foodType = optional.get();
        } else {
            throw new RuntimeException(" Employee not found for id :: " + id);
        }
        return foodType;
    }
}
