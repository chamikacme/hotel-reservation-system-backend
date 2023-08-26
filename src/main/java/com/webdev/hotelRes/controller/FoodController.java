package com.webdev.hotelRes.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.webdev.hotelRes.entity.Food;
import com.webdev.hotelRes.service.FoodService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping
public class FoodController {
    private FoodService foodService;

    @Autowired
    public FoodController(FoodService foodService){
        this.foodService=foodService;
    }

    @GetMapping("/foods")
    public ResponseEntity<List<Food>>getAllFoods(){
        return ResponseEntity.status(HttpStatus.OK).body(foodService.getAllFoods());
    }

    @GetMapping("/foods/{Id}")
    public ResponseEntity<Food> getFoodById(@PathVariable Long id){
        try{

            Food food= foodService.getFoodById(id);
            return ResponseEntity.status(HttpStatus.OK).body(food);

        }catch(NoSuchElementException e){

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

        }catch(Exception e){
            
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
            
    }

    @PostMapping("/foods")
    public ResponseEntity<Food> saveFood(@RequestBody Food food){
        try{

            Food newFood=foodService.addFood(food);
            return ResponseEntity.status(HttpStatus.OK).body(newFood);

        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/foods/{id}")
    public ResponseEntity<Food> updateFood(@PathVariable Long id,@RequestBody Food food){
            try{

                Food updatedFood=foodService.editFood(id,food);
                return ResponseEntity.status(HttpStatus.OK).body(updatedFood);

            }catch(NoSuchElementException e){

                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

            }catch(Exception e){
                
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        
    }
    @DeleteMapping("/foods/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id){
        try{
            foodService.deleteFood(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);

        }catch(Exception e){

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    
}

