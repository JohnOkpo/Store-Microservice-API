package com.iOtech.inventory.service;

import com.iOtech.inventory.dto.InventoryDto;
import com.iOtech.inventory.entity.Inventory;

import java.util.List;

public interface InventoryService
{
    InventoryDto createInventory(InventoryDto inventoryDto);
    InventoryDto getInventory(Long inventoryId);
    Boolean increaseInventory(Inventory inventory, InventoryDto inventoryDto);
    Boolean reduceInventoryByProductId(Long productId);
    List<InventoryDto> getAllInventory();
    void deleteInventory(Long id);
    void updateInventory(Long id, InventoryDto inventoryDto);
}
