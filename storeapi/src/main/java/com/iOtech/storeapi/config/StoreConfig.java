package com.iOtech.storeapi.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StoreConfig
{
    @Bean
    public ModelMapper modelMapper()
    {
        return new ModelMapper();
    }
}
