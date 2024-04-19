package com.webdev.hotelRes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Food;

@Service
public interface FoodService {
    List<Food> getAllFoods();
    Food getFoodById(Long id);
    Food addFood(Food food);
    Food editFood(Long id, Food food);
    void deleteFood(Long id);
    
}
