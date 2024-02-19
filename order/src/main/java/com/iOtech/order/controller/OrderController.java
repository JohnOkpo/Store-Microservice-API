package com.iOtech.order.controller;

import com.iOtech.order.dto.OrderDto;
import com.iOtech.order.service.impl.OrderServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RequestMapping("/order")
@RestController
public class OrderController
{
    private final OrderServiceImpl orderService;

    public OrderController(OrderServiceImpl orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto)
    {
        try
        {
            OrderDto createdOrder = orderService.makeAnOrder(orderDto);
            return ResponseEntity.status(HttpStatus.OK).body(createdOrder);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllOrder()
    {
        try
        {
            List<OrderDto> orderDtoList = orderService.getOrders();
            return ResponseEntity.status(HttpStatus.OK).body(orderDtoList);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOrder(@PathVariable Long id)
    {
        try
        {
            OrderDto orderDto = orderService.getOrder(id);
            return ResponseEntity.status(HttpStatus.OK).body(orderDto);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateOrder(@PathVariable Long id, @RequestBody OrderDto orderDto)
    {
        try
        {
            orderService.updateOrder(id,orderDto);
            return ResponseEntity.status(HttpStatus.OK).body("Updated successfully");
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrder(@RequestParam Long id)
    {
        try{
            orderService.deleteOrder(id);
            return ResponseEntity.status(HttpStatus.OK).body("Order deleted");
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
