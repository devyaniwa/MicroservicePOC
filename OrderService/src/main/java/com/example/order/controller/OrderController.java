package com.example.order.controller;

import com.example.order.entity.Order;
import com.example.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {


  @Autowired
  private OrderService orderService;

  @PostMapping(value = "/create")
  public Order createOrder(@RequestBody Order order) {

    order=orderService.createOrder(order);
    return order;
  }

  @GetMapping("/all")
  public List<Order> getOrders() {

    return orderService.getAllOrders();
  }
}
