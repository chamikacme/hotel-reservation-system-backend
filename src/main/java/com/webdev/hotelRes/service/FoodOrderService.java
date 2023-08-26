package com.webdev.hotelRes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.FoodOrder;

@Service
public interface FoodOrderService {
    List<FoodOrder> getAllFoodOrders();
    FoodOrder getFoodOrderById(Long id);
    FoodOrder addFoodOrder(FoodOrder foodOrder);
    FoodOrder editFoodOrder(Long id, FoodOrder foodOrder);
    void deleteFoodOrder(Long id);

    
}
