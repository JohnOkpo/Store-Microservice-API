package com.iOtech.storeapi.messaging;

import com.iOtech.storeapi.dto.InventoryDto;
import com.iOtech.storeapi.external.Inventory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class InventoryMessageProducer
{
    private final RabbitTemplate rabbitTemplate;

    public InventoryMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(InventoryDto inventoryDto)
    {
        Inventory inventory = new Inventory();
        inventory.setProductId(inventoryDto.getProductId());
        inventory.setTotalNoAvailable(1);
        rabbitTemplate.convertAndSend("inventoryQueue",inventory);
    }
}
