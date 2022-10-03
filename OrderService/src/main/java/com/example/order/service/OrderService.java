package com.example.order.service;

import com.example.order.entity.Order;
import com.example.order.repo.OrderCrud;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderCrud orderCRUD;

    @Value("${order.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    ObjectMapper om=new ObjectMapper();

    public Order createOrder(Order order) {
        order =orderCRUD.save(order);
        order.setStatus("CREATED");
        
        String message= null;
        try {
            message = om.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        kafkaTemplate.send(topicName,message);
        return order;
    }

    public List<Order> getAllOrders() {
        List<Order> orders= (List<Order>) orderCRUD.findAll();
        return orders;
    }
}
