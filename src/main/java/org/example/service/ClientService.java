package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.example.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class ClientService {
    private static KafkaTemplate<Long, Order> kafkaTemplate;
    private static List<Order> orders = new ArrayList<>();

    @Autowired
    public ClientService(KafkaTemplate<Long, Order> template,
                         ObjectMapper objectMapper) {
        kafkaTemplate = template;
    }

    public static List<Order> getOrders() {
        return orders;
    }

    public static void addOrder(Order order) {
        orders.add(order);
    }

    public static Order getOrder(long id) {
        Optional<Order> result = orders.stream().filter(x -> x.getId() == id).findFirst();
        return result.orElse(null);
    }

    public static void setOrderNumber(Order order) {
        if (orders.isEmpty()) {
            order.setId(1);
        } else {
            order.setId(orders.size() + 1);
        }
        orders.add(order);
    }

    public static Order create(Order order) {
        setOrderNumber(order);
        order.setStatus("CREATED");
        kafkaTemplate.send("created", order.getId(), order);
        return order;
    }

    public static String getOrderStatus(int id) {
        Order order = getOrder(id);
        return order != null ? order.getStatus() : String.format("Заказ с id=%d не найден", id);
    }
}
