package com.iOtech.order.service.impl;

import com.iOtech.order.client.InventoryClient;
import com.iOtech.order.dto.OrderDto;
import com.iOtech.order.entity.OrderModel;
import com.iOtech.order.external.InventoryDto;
import com.iOtech.order.mapper.OrderMapper;
import com.iOtech.order.repository.OrderRepository;
import com.iOtech.order.service.OrderService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService
{
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final InventoryClient inventoryClient;

    public OrderServiceImpl(OrderRepository orderRepository, OrderMapper orderMapper, InventoryClient inventoryClient)
    {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
        this.inventoryClient = inventoryClient;
    }

    @Override
    public OrderDto makeAnOrder(OrderDto orderDto)
    {
        try
        {
            orderDto.setDateOfOrder(LocalDate.now());
            orderDto.setTimeOfOrder(LocalTime.now());
            OrderModel order = orderMapper.convertToModel(orderDto);
            OrderModel savedOrder = orderRepository.save(order);

            InventoryDto inventoryDto = new InventoryDto(
                    null,
                    order.getProductId(),
                    0,
                    LocalDate.now());

            inventoryClient.updateProductQuantity(inventoryDto);
            return orderMapper.convertToDto(savedOrder);

        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public OrderDto getOrder(Long orderId) {
        try {
            Optional<OrderModel> orderModelOptional  = orderRepository.findById(orderId);
            if(orderModelOptional.isPresent())
                return orderMapper.convertFromOptionalModelToOrderDto(orderModelOptional);
            return null;
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<OrderDto> getOrders() {
        try
        {
            List<OrderModel> orderModelList = orderRepository.findAll();
            return orderMapper.orderDtoList(orderModelList);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void deleteOrder(Long id)
    {
        try {
            Optional<OrderModel> findOrderById = orderRepository.findById(id);
            if(findOrderById.isPresent())
                orderRepository.deleteById(id);
        }catch (Exception ex)
        {
            throw new RuntimeException("Invalid operation");
        }
    }

    @Override
    public void updateOrder(Long id, OrderDto orderDto)
    {
        try{
            Optional<OrderModel> orderModelOptional = orderRepository.findById(id);
            if(orderModelOptional.isPresent()) {
                OrderModel orderModel = orderMapper.convertFromOptionalToModel(orderModelOptional);
                orderModel.setAmount(orderDto.getAmount());
                orderModel.setQuantity(orderDto.getQuantity());
                orderModel.setProductId(orderDto.getProductId());
                orderRepository.save(orderModel);
            }
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
