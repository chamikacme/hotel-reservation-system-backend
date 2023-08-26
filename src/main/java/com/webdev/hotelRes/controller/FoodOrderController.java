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

import com.webdev.hotelRes.entity.FoodOrder;
import com.webdev.hotelRes.service.FoodOrderService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/foodorders")
public class FoodOrderController {
    private FoodOrderService foodOrderService;

    @Autowired
    public FoodOrderController(FoodOrderService foodOrderService){
        this.foodOrderService=foodOrderService;
    }

    @GetMapping
    public ResponseEntity<List<FoodOrder>> getAllFoodOrders() {
        return ResponseEntity.status(HttpStatus.OK).body(foodOrderService.getAllFoodOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getFoodOrderById(@PathVariable Long id) {
        try {
            FoodOrder foodOrder = foodOrderService.getFoodOrderById(id);
            return ResponseEntity.status(HttpStatus.OK).body(foodOrder);
        } catch (NoSuchElementException e)

        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e)

        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    @PostMapping("/foodorders")
        public ResponseEntity<FoodOrder> saveFoodOrder(@RequestBody FoodOrder foodOrder){
            try{
                FoodOrder newFoodOrder= foodOrderService.addFoodOrder(foodOrder);
                return ResponseEntity.status(HttpStatus.OK).body(newFoodOrder);
            }catch(Exception e){
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }

        @PutMapping("/foodorders/{id}")
        public ResponseEntity<FoodOrder> updateOrderDetail(@PathVariable Long id, @RequestBody FoodOrder foodOrder){
            try{
                FoodOrder updatedFoodOrder=foodOrderService.editFoodOrder(id, foodOrder);
                return ResponseEntity.status(HttpStatus.OK).body(updatedFoodOrder);
                }catch(NoSuchElementException e)
                {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

                }catch(Exception e)
                {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
                }
        }

        @DeleteMapping("/foodorders/{id}")
        public ResponseEntity<Void> deleteOrderDetail(@PathVariable Long id){
            try{
                foodOrderService.deleteFoodOrder(id);
                return ResponseEntity.status(HttpStatus.OK).body(null);
            }catch(NoSuchElementException e)
            {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }catch(Exception e)
            {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        }
    

}
