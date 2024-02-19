package com.iOtech.order.repository;

import com.iOtech.order.entity.OrderModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderModel, Long>
{

}
