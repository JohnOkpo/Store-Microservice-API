package com.iOtech.storeapi.dto;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreModelDto
{
    private Long productId;
    private String name;
    private Double unitPrice;


}
