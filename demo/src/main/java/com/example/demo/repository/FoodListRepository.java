package com.example.demo.repository;

import com.example.demo.model.entities.FoodList;
import com.example.demo.model.entities.FoodType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodListRepository extends JpaRepository<FoodList, Long> {
    //List<FoodList> findByFoodType(FoodType foodType);

}
