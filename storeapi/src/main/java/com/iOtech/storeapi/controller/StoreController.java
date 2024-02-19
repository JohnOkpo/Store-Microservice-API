package com.iOtech.storeapi.controller;

import com.iOtech.storeapi.dto.StoreModelDto;
import com.iOtech.storeapi.service.impl.StoreServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/store")
@RestController
public class StoreController
{
    private final StoreServiceImpl storeService;
    public StoreController(StoreServiceImpl storeService)
    {
        this.storeService = storeService;
    }

    @PostMapping
    public ResponseEntity<?> addNewProduct(@RequestBody StoreModelDto storeModelDto)
    {
        try
        {
            StoreModelDto createStore = storeService.addNewItem(storeModelDto);
            return ResponseEntity.status(HttpStatus.OK).body(createStore);
        }
        catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllProduct()
    {
        try
        {
           List<StoreModelDto> listOfProducts = storeService.getAllItems();
           return ResponseEntity.status(HttpStatus.OK).body(listOfProducts);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteProduct(@RequestParam Long id)
    {
        try
        {
            storeService.deleteItem(id);
            return ResponseEntity.status(HttpStatus.OK).body("Product deleted");
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(@RequestParam Long id, @RequestBody StoreModelDto storeModelDto)
    {
        try
        {
            storeService.updateItem(id, storeModelDto);
            return ResponseEntity.status(HttpStatus.OK).body("Record updated");
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }
}
