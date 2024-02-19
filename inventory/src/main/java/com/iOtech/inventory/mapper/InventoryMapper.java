package com.iOtech.inventory.mapper;

import com.iOtech.inventory.dto.InventoryDto;
import com.iOtech.inventory.entity.Inventory;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class InventoryMapper
{
    private final ModelMapper modelMapper;

    public InventoryMapper(ModelMapper modelMapper)
    {
        this.modelMapper = modelMapper;
    }

    public Inventory convertToModel(InventoryDto inventoryDto)
    {
        return modelMapper.map(inventoryDto, Inventory.class);
    }

    public InventoryDto convertToDto(Inventory inventory)
    {
        return modelMapper.map(inventory, InventoryDto.class);
    }

    public InventoryDto convertFomOptionalToDto(Optional<Inventory> inventoryOptional)
    {
        return inventoryOptional.map(this::convertToDto).orElseThrow(() ->new RuntimeException("Invalid inventory"));
    }

    public Inventory convertFromOptionalToModel(Optional<Inventory> optionalInventory)
    {
        return optionalInventory.orElseThrow(()->new RuntimeException("Invalid inventory"));
    }

    public List<InventoryDto> inventoryListDto(List<Inventory> inventoryList)
    {
        return inventoryList.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }


}
