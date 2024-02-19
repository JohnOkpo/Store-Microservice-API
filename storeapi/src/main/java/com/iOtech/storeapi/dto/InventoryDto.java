package com.iOtech.storeapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InventoryDto
{
    private Long productId;
    private Integer totalNoAvailable;
    private LocalDate modifiedAt;
}
