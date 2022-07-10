package com.example.demo.util;

import com.example.demo.model.entities.FoodType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
public class FoodTemp {
    private Long id_food;
    private String food_name;

    private FoodType foodType;
    private double price;

    private String img;
    private int counter;
}
