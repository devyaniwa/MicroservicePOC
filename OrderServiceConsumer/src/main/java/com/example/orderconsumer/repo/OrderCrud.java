package com.example.orderconsumer.repo;


import com.example.orderconsumer.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderCrud extends CrudRepository<Order,Integer> {

}
