package com.iOtech.order.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto
{
    private Long id;
    private Long productId;
    private Integer totalNoAvailable;
    private LocalDate modifiedAt;
}