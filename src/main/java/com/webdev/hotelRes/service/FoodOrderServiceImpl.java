package com.webdev.hotelRes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.FoodOrder;
import com.webdev.hotelRes.repository.FoodOrderRepository;

@Service
public class FoodOrderServiceImpl implements FoodOrderService {
    private FoodOrderRepository foodOrderRepository;

    @Autowired
    public FoodOrderServiceImpl(FoodOrderRepository foodOrderRepository){
        this.foodOrderRepository = foodOrderRepository;
    }

    @Override
    public List<FoodOrder> getAllFoodOrders(){
        return foodOrderRepository.findAll();
    }

    @Override
    public FoodOrder getFoodOrderById(Long id){
        return foodOrderRepository.findById(id).orElseThrow(() -> new NoSuchElementException("FoodOrder Not found" + id));
    }

    @Override
    public FoodOrder addFoodOrder(FoodOrder foodOrder){
        return foodOrderRepository.save(foodOrder);
    }

    @Override
    public FoodOrder editFoodOrder(Long id, FoodOrder foodOrder){
        FoodOrder existingFoodOrder = getFoodOrderById(id);

        existingFoodOrder.setTotal_amount(foodOrder.getTotal_amount());
        existingFoodOrder.setCreated_at(foodOrder.getCreated_at());
        existingFoodOrder.setStatus(foodOrder.getStatus());

        return foodOrderRepository.save(existingFoodOrder);
    }

    @Override
    public void deleteFoodOrder(Long id){
        foodOrderRepository.deleteById(id);
    }
    
}
