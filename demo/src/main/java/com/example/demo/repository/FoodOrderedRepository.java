package com.example.demo.repository;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodOrdered;
import com.example.demo.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodOrderedRepository extends JpaRepository<FoodOrdered, Long> {
    //FoodOrdered findById(long id);
    List<FoodList> findByOrder(Order order);
    //List<FoodOrdered> findFoodOrderedByOrder(Order order);
    //List<FoodOrdered> findFoodOrderedById_order(Long id_order);
}
