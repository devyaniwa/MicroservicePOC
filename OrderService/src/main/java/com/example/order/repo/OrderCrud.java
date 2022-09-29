package com.example.order.repo;

import com.example.order.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderCrud extends CrudRepository<Order,Integer> {

}
