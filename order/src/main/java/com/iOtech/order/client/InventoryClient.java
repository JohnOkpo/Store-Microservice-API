package com.iOtech.order.client;

import com.iOtech.order.external.InventoryDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="INVENTORY-SERVICE")
public interface InventoryClient
{
    @PostMapping("/inventory/trackProductQuantity")
    Boolean updateProductQuantity(@RequestBody InventoryDto inventoryDto);
}
