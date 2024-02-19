package com.iOtech.storeapi.service;

import com.iOtech.storeapi.dto.StoreModelDto;

import java.util.List;

public interface StoreService
{
    StoreModelDto addNewItem(StoreModelDto storeModelDto);
    List<StoreModelDto> getAllItems();
    StoreModelDto getItem(Long productId);
    void deleteItem(Long productId);
    void updateItem(Long productId, StoreModelDto storeModelDto);
}
