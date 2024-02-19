package com.iOtech.storeapi.service.impl;

import com.iOtech.storeapi.dto.InventoryDto;
import com.iOtech.storeapi.dto.StoreModelDto;
import com.iOtech.storeapi.entity.StoreModel;
import com.iOtech.storeapi.mapper.StoreMapper;
import com.iOtech.storeapi.messaging.InventoryMessageProducer;
import com.iOtech.storeapi.repository.StoreRepository;
import com.iOtech.storeapi.service.StoreService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StoreServiceImpl implements StoreService
{
    private final StoreMapper storeMapper;
    private final StoreRepository storeRepository;

    private final InventoryMessageProducer inventoryMessageProducer;

    public StoreServiceImpl(StoreMapper storeMapper, StoreRepository storeRepository, InventoryMessageProducer inventoryMessageProducer) {
        this.storeMapper = storeMapper;
        this.storeRepository = storeRepository;
        this.inventoryMessageProducer = inventoryMessageProducer;
    }

    @Override
    public StoreModelDto addNewItem(StoreModelDto storeModelDto)
    {
        try
        {
            StoreModel storeModel = storeMapper.convertToModel(storeModelDto);
            validateProduct(storeModelDto);
            StoreModel addedProduct = storeRepository.save(storeModel);
            //Sending to rabbitMQ
            InventoryDto inventoryDto = new InventoryDto(addedProduct.getProductId(),0, LocalDate.now());
            inventoryMessageProducer.sendMessage(inventoryDto);

            return storeMapper.convertToDto(addedProduct);
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public List<StoreModelDto> getAllItems()
    {
        try
        {
            List<StoreModel> storeModelDtoList = storeRepository.findAll();
            return storeMapper.storeModelDtoList(storeModelDtoList);
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public StoreModelDto getItem(Long productId)
    {
        try
        {
            Optional<StoreModel> storeModel = storeRepository.findById(productId);
            return storeMapper.convertFromOptional(storeModel);
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void deleteItem(Long productId)
    {
        try{
            Optional<StoreModel> storeModel = storeRepository.findById(productId);
            if(storeModel.isPresent())
            {
                storeRepository.deleteById(productId);
            }
        }catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    @Override
    public void updateItem(Long productId, StoreModelDto storeModelDto)
    {
        try
        {
            Optional<StoreModel> storeModel = storeRepository.findById(productId);
            if(storeModel.isPresent())
            {
                StoreModel storeFromDb = storeMapper.convertOptionalToStoreModel(storeModel);
                storeFromDb.setName(storeModelDto.getName());
                storeFromDb.setUnitPrice(storeModelDto.getUnitPrice());
                storeRepository.save(storeFromDb);
            }
        }
        catch (Exception ex)
        {
            throw new RuntimeException(ex.getMessage());
        }
    }

    private void validateProduct(StoreModelDto storeModelDto)
    {
        if(Objects.equals(storeModelDto.getName(), ""))
        {
            throw new RuntimeException("Add valid entry for name");
        }

        if (Double.isNaN(storeModelDto.getUnitPrice()))
        {
            throw new RuntimeException("Provide a valid entry for price");
        }

//        if(storeModelDto.getProductId() != null)
//        {
//            throw new RuntimeException("Provide vale for productId");
//        }

    }
}
