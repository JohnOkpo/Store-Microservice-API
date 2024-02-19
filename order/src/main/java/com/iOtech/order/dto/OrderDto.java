package com.iOtech.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderDto
{
    private Long id;
    private Long productId;
    private Integer quantity;
    private Double amount;
    private LocalTime timeOfOrder;
    private LocalDate dateOfOrder;
}
