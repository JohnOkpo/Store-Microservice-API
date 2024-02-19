package com.iOtech.inventory.messaging;

import com.iOtech.inventory.dto.InventoryDto;
import com.iOtech.inventory.entity.Inventory;
import com.iOtech.inventory.mapper.InventoryMapper;
import com.iOtech.inventory.service.impl.InventoryServiceImpl;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Service;

@Service
public class RabbitMessageConsumer
{
    private final InventoryServiceImpl inventoryService;
    private final InventoryMapper inventoryMapper;
    public RabbitMessageConsumer(InventoryServiceImpl inventoryService, InventoryMapper inventoryMapper)
    {
        this.inventoryService = inventoryService;
        this.inventoryMapper = inventoryMapper;
    }

    @RabbitListener(queues = "inventoryQueue")
    public void consumeMessage(Inventory inventory)
    {
        InventoryDto inventoryDto = inventoryMapper.convertToDto(inventory);
        inventoryService.createInventory(inventoryDto);
    }
}
