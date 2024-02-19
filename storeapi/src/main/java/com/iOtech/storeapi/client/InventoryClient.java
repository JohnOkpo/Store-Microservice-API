package com.iOtech.storeapi.client;

import com.iOtech.storeapi.external.Inventory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryClient
{
    @PostMapping("/trackProductQuantity")
    Boolean reduceInventoryByProductId(@RequestBody Inventory inventoryDto);
}
