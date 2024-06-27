package com.example.orders.service;

import com.example.orders.model.entities.Orders;
import com.example.orders.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor

public class OrdersService {
    private final RestTemplate restTemplate = new RestTemplate();
    private final OrdersRepository ordersRepository;




    public List<Orders> getOrders() {
        return ordersRepository.findAll();
    }


    public ResponseEntity<Object> newOrders(Orders orders) {
        Long productId = orders.getProductId();
        String url = "http://localhost:8085/api/products/" + productId;
        try {
            ResponseEntity<Object> response = restTemplate.getForEntity(url, Object.class);


            if (response.getStatusCode() == HttpStatus.OK) {

                ordersRepository.save(orders);
                return new ResponseEntity<>("Order created successfully", HttpStatus.CREATED);
            } else {

                return new ResponseEntity<>("Product not found, order not created", HttpStatus.NOT_FOUND);
            }
        } catch (HttpClientErrorException.NotFound ex) {
            return new ResponseEntity<>("Product not found, order not created", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> deleteOrders(Long id) {
        Optional<Orders> existingOrderOptional = ordersRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            ordersRepository.deleteById(id);
            return new ResponseEntity<>("Order deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<Object> updateOrders(Long id, Orders updatedOrders) {
        Optional<Orders> existingOrderOptional = ordersRepository.findById(id);
        if (existingOrderOptional.isPresent()) {
            Orders existingOrder = existingOrderOptional.get();
            existingOrder.setProductId(updatedOrders.getProductId());
            existingOrder.setUnitPrice(updatedOrders.getUnitPrice());
            existingOrder.setQuantity(updatedOrders.getQuantity());
            existingOrder.setTotal(updatedOrders.getTotal());
            existingOrder.setDate(updatedOrders.getDate());
            existingOrder.setNotes(updatedOrders.getNotes());

            ordersRepository.save(existingOrder);

            return new ResponseEntity<>("Order updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Order not found", HttpStatus.NOT_FOUND);
        }
    }


}
