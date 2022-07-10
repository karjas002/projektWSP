package com.example.demo.repository;

import com.example.demo.model.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

    List<Order> findBymakingTrue();
    Order findById(long id);
    List<Order> findBydoneTrue();
    List<Order> findBycollectedTrue();
    List<Order> findBycollectedFalse();
    @Query(value = "SELECT * FROM `order` WHERE done = 1 AND collected = 0", nativeQuery = true)
    List<Order> findBydoneTrueAndcollectedFalse();
}
