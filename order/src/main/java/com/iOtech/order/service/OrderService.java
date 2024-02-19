package com.iOtech.order.service;

import com.iOtech.order.dto.OrderDto;

import java.util.List;

public interface OrderService
{
    OrderDto makeAnOrder(OrderDto orderDto);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getOrders();
    void deleteOrder(Long id);
    void updateOrder(Long id, OrderDto orderDto);
}
