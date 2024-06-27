package com.example.orders.repositories;

import com.example.orders.model.entities.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository  extends JpaRepository<Orders,Long> {
}
