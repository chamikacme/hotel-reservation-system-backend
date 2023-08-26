package com.webdev.hotelRes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdev.hotelRes.entity.FoodOrder;

@Repository
public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long> {
    
}
