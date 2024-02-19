package com.iOtech.storeapi.mapper;

import com.iOtech.storeapi.dto.StoreModelDto;
import com.iOtech.storeapi.entity.StoreModel;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class StoreMapper
{
    private final ModelMapper modelMapper;

    public StoreMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public StoreModelDto convertToDto(StoreModel storeModel)
    {
        return modelMapper.map(storeModel, StoreModelDto.class);
    }

    public StoreModel convertToModel(StoreModelDto storeModelDto)
    {
        return modelMapper.map(storeModelDto, StoreModel.class);
    }

    public List<StoreModelDto> storeModelDtoList(List<StoreModel> storeModels)
    {
        return storeModels.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public StoreModelDto convertFromOptional(Optional<StoreModel> optionalStoreModel)
    {
        return optionalStoreModel.map(this::convertToDto)
                .orElseThrow(()-> new RuntimeException("Invalid values"));
    }

    public StoreModel convertOptionalToStoreModel(Optional<StoreModel> optionalStoreModel)
    {
        return optionalStoreModel.orElseThrow(()-> new RuntimeException("No values to parse"));
    }


}
