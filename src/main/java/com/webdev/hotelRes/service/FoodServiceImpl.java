package com.webdev.hotelRes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.Food;
import com.webdev.hotelRes.repository.FoodRepository;

@Service
public class FoodServiceImpl implements FoodService {
    private FoodRepository foodRepository;

    @Autowired
    public FoodServiceImpl(FoodRepository foodRepository){
        this.foodRepository=foodRepository;
    }

    @Override
    public List<Food> getAllFoods(){
        return foodRepository.findAll();
    }

    @Override
    public Food getFoodById(Long id){
        return foodRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Food Not Found"+id));
    }

    @Override
    public Food addFood(Food food){
        return foodRepository.save(food);
    }

    @Override
    public Food editFood(Long id, Food food){
        Food existingFood=getFoodById(id);

        existingFood.setName(food.getImage());
        existingFood.setPrice(food.getPrice());
        existingFood.setImage(food.getImage());

        return foodRepository.save(existingFood);
    }

    @Override

    public void deleteFood(Long id){
        foodRepository.deleteById(id);
    }
    
}
