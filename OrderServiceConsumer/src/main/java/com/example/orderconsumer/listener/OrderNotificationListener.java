package com.example.orderconsumer.listener;

import com.example.orderconsumer.entity.Order;
import com.example.orderconsumer.entity.User;
import com.example.orderconsumer.repo.OrderCrud;
import com.example.orderconsumer.repo.UserCrud;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderNotificationListener {

  @Value("${order.topic.name}")
  private String topicName;

  @Autowired
  OrderCrud orderCRUD;
  @Autowired
  UserCrud userCRUD;

  @KafkaListener(topics = "order-topic", groupId = "foo")
  public void listenGroupFoo(String message) {
    System.out.println("Received Message in group foo: " + message);
    ObjectMapper object = new ObjectMapper();
    Order order = null;
    try {
      order = object.readValue(message, Order.class);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
    User user = userCRUD.findById(order.getUserId()).get();
    if (user.getBalance() > order.getOrderAmount()) {
      user.setBalance(user.getBalance() - order.getOrderAmount());
      order.setStatus("SUCCESS");
      userCRUD.save(user);
      orderCRUD.save(order);
    } else {
      order.setStatus("FAILED");
      orderCRUD.save(order);
    }

  }
}
