package com.iOtech.order.mapper;

import com.iOtech.order.dto.OrderDto;
import com.iOtech.order.entity.OrderModel;
import org.hibernate.query.Order;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class OrderMapper
{
    private final ModelMapper modelMapper;
    public OrderMapper(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public OrderModel convertToModel(OrderDto orderDto)
    {
        return modelMapper.map(orderDto, OrderModel.class);
    }

    public OrderDto convertToDto(OrderModel order)
    {
        return modelMapper.map(order, OrderDto.class);
    }

    public List<OrderDto> orderDtoList(List<OrderModel> orderList)
    {
        return orderList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public Optional<OrderDto> optionalOrderDto (Optional<OrderModel> orderOptional)
    {
        return orderOptional.map(this::convertToDto);
    }

    public OrderDto convertFromOptionalToOrderDto(Optional<OrderDto> optionalOrderDto)
    {
        return optionalOrderDto.orElseThrow(()->new RuntimeException("Invalid operation"));
    }

    public OrderDto convertFromOptionalModelToOrderDto(Optional<OrderModel> optionalOrderDto)
    {
        return optionalOrderDto.map(this::convertToDto)
                .orElseThrow(()->new RuntimeException("Invalid operation"));
    }


    public OrderModel convertFromOptionalToModel(Optional<OrderModel> orderModelOptional)
    {
        return orderModelOptional.orElseThrow(()->new RuntimeException("Invalid Operation"));
    }

}
