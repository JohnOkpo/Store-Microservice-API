package com.iOtech.inventory.service.impl;

import com.iOtech.inventory.dto.InventoryDto;
import com.iOtech.inventory.entity.Inventory;
import com.iOtech.inventory.mapper.InventoryMapper;
import com.iOtech.inventory.messaging.RabbitMessageConsumer;
import com.iOtech.inventory.repository.InventoryRepository;
import com.iOtech.inventory.service.InventoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImpl implements InventoryService
{

    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
//    private final RabbitMessageConsumer rabbitMessageConsumer;
    public InventoryServiceImpl(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
    }

    @Override
    public InventoryDto createInventory(InventoryDto inventoryDto)
    {
        try
        {
            validateInventory(inventoryDto);
            Optional<Inventory> findByIdResult = inventoryRepository.findByProductId(inventoryDto.getProductId());
            if(findByIdResult.isPresent())
            {
                Inventory inventory = inventoryMapper.convertFromOptionalToModel(findByIdResult);
                increaseInventory(inventory, inventoryDto);
                return inventoryDto;
            }
            else
            {
                validateInventory(inventoryDto);
                Inventory inventory = inventoryMapper.convertToModel(inventoryDto);
                inventory.setModifiedAt(LocalDate.now());
                Inventory savedInventory = inventoryRepository.save(inventory);
                return inventoryMapper.convertToDto(savedInventory);
            }

        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public Boolean increaseInventory(Inventory inventoryModel, InventoryDto inventoryDto)
    {
        try
        {
//            Optional<Inventory> findByIdResult = inventoryRepository.findByProductId(inventoryDto.getProductId());
//            Inventory inventoryModel = inventoryMapper.convertFromOptionalToModel(findByIdResult);
            inventoryModel.setTotalNoAvailable(inventoryModel.getTotalNoAvailable()+1);
            inventoryModel.setModifiedAt(LocalDate.now());
            inventoryRepository.save(inventoryModel);
            return true;
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public InventoryDto getInventory(Long inventoryId)
    {
        try
        {
            Optional<Inventory> findByIdResult = inventoryRepository.findById(inventoryId);
            if(findByIdResult.isPresent())
                return inventoryMapper.convertFomOptionalToDto(findByIdResult);
            return null;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }

    @Override
    public Boolean reduceInventoryByProductId(Long productId)
    {
        try
        {
            Optional<Inventory> findByIdResult = inventoryRepository.findByProductId(productId);
            Integer productQty = findByIdResult.get().getTotalNoAvailable();
            Integer newProductQty = productQty - 1;
            Inventory inventory = inventoryMapper.convertFromOptionalToModel(findByIdResult);
            inventory.setTotalNoAvailable(newProductQty);
            inventoryRepository.save(inventory);
            return true;
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }

    }



    @Override
    public List<InventoryDto> getAllInventory()
    {
        try
        {
            List<Inventory> inventoryList = inventoryRepository.findAll();
            return inventoryMapper.inventoryListDto(inventoryList);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void deleteInventory(Long id)
    {
        try {
            Optional<Inventory> findInventoryById = inventoryRepository.findById(id);
            if(findInventoryById.isPresent())
            {
                inventoryRepository.deleteById(id);
            }
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void updateInventory(Long id, InventoryDto inventoryDto)
    {
        try
        {
            Optional<Inventory> findByIdResult = inventoryRepository.findById(id);
            if (findByIdResult.isPresent())
            {
                Inventory inventoryModel = inventoryMapper.convertFromOptionalToModel(findByIdResult);
                inventoryModel.setProductId(inventoryDto.getProductId());
                inventoryModel.setTotalNoAvailable(inventoryDto.getTotalNoAvailable());
                inventoryModel.setModifiedAt(LocalDate.now());
                inventoryRepository.save(inventoryModel);
            }

        }catch (Exception ex)
        {
            throw  new RuntimeException(ex.getMessage());
        }
    }

    private void validateInventory(InventoryDto inventoryDto)
    {
        if(inventoryDto.getProductId() == null)
        {
            throw new RuntimeException("provide a valid figure");
        }

        if(inventoryDto.getTotalNoAvailable() == null)
        {
            throw new RuntimeException("provide a total available amount");
        }
    }
}
