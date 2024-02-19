package com.iOtech.storeapi.external;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inventory
{
    private Long id;
    private Long productId;
    private Integer totalNoAvailable;
    private LocalDate modifiedAt;
}
