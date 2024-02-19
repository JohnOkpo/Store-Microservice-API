package com.iOtech.inventory.controller;

import com.iOtech.inventory.dto.InventoryDto;
import com.iOtech.inventory.service.impl.InventoryServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inventory")
public class InventoryController
{
    private final InventoryServiceImpl inventoryService;

    public InventoryController(InventoryServiceImpl inventoryService) {
        this.inventoryService = inventoryService;
    }

    @PostMapping
    public ResponseEntity<?> createInventory(@RequestBody InventoryDto inventoryDto)
    {
        try
        {
            InventoryDto createInventoryResult = inventoryService.createInventory(inventoryDto);
            return ResponseEntity.status(HttpStatus.OK).body(createInventoryResult);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PostMapping("/trackProductQuantity")
    public Boolean updateProductQuantity(@RequestBody InventoryDto inventoryDto)
    {
        try
        {
            Boolean updateResult = inventoryService.reduceInventoryByProductId(inventoryDto.getProductId());
            if(updateResult)
            {
                return true;
            }
            return false;
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllInventory()
    {
        try {
            List<InventoryDto> inventoryList = inventoryService.getAllInventory();
            return ResponseEntity.status(HttpStatus.OK).body(inventoryList);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getInventory(@RequestParam Long id)
    {
        try
        {
            InventoryDto inventoryDto = inventoryService.getInventory(id);
            return ResponseEntity.status(HttpStatus.OK).body(inventoryDto);
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInventory(@RequestParam Long id)
    {
        try
        {
            inventoryService.deleteInventory(id);
            return ResponseEntity.status(HttpStatus.OK).body("Inventory deleted successfully");
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateInventory(@RequestParam Long id, @RequestBody InventoryDto inventoryDto)
    {
        try {
            inventoryService.updateInventory(id, inventoryDto);
            return ResponseEntity.status(HttpStatus.OK).body("Inventory updated successfully");
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }


}
