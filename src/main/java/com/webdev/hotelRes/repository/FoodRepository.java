package com.webdev.hotelRes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webdev.hotelRes.entity.Food;

@Repository
public interface FoodRepository extends JpaRepository<Food,Long>{
    
}
