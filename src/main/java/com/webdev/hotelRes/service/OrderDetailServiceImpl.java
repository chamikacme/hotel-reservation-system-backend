package com.webdev.hotelRes.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.OrderDetail;
import com.webdev.hotelRes.repository.OrderDetailRepository;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository){
        this.orderDetailRepository=orderDetailRepository;
    }

    @Override
    public  List<OrderDetail> getAllOrderDetails(){
        return orderDetailRepository.findAll();
    }

    @Override
    public OrderDetail getOrderDetailById(Long id){
        return orderDetailRepository.findById(id).orElseThrow(() -> new NoSuchElementException("OrderDetails Not found for" + id));
    }

    @Override
    public  OrderDetail addOrderDetail(OrderDetail orderDetail){
        return orderDetailRepository.save(orderDetail);
    }

    @Override
    public OrderDetail editOrderDetail(Long id, OrderDetail orderDetail){
        OrderDetail existingOrderDetail = getOrderDetailById(id);

        existingOrderDetail.setQuantity(orderDetail.getQuantity());
        existingOrderDetail.setSub_total(orderDetail.getSub_total());

        return orderDetailRepository.save(existingOrderDetail);
    }

    @Override
    public void deleteOrderDetail(Long id){
        orderDetailRepository.deleteById(id);
    }


}
