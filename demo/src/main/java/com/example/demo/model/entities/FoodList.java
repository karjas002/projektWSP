package com.example.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_food;
    private String food_name;
    // @ManyToOne
    // @JoinColumn(name = "id_order")
    // private Order order;
    @ManyToOne
    @JoinColumn(name = "id_food_type")
    private FoodType foodType;
    private double price;
}
