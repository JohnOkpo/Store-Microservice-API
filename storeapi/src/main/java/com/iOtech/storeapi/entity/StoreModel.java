package com.iOtech.storeapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StoreModel
{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long productId;
    private String name;
    private Double unitPrice;
}
