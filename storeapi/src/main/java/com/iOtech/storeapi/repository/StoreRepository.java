package com.iOtech.storeapi.repository;

import com.iOtech.storeapi.entity.StoreModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository  extends JpaRepository<StoreModel, Long>
{
}
