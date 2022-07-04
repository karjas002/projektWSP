package com.example.demo.model.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodOrdered {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_FoodOrdered;
    @ManyToOne
    @JoinColumn(name = "id_food")
    private FoodList food;
    @ManyToOne
    @JoinColumn(name = "id_order")
    private Order order;

    private Integer counter;
}
