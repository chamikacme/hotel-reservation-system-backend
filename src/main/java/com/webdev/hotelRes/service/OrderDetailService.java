package com.webdev.hotelRes.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.webdev.hotelRes.entity.OrderDetail;

@Service
public interface OrderDetailService {
    List<OrderDetail> getAllOrderDetails();
    OrderDetail getOrderDetailById(Long id);
    OrderDetail addOrderDetail(OrderDetail orderDetail);
    OrderDetail editOrderDetail(Long id, OrderDetail orderDetail);
    void deleteOrderDetail(Long id);
}
