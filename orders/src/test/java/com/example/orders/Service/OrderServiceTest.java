package com.example.orders.Service;

import com.example.orders.model.entities.Orders;
import com.example.orders.repositories.OrdersRepository;
import com.example.orders.service.OrdersService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class OrderServiceTest {

    @MockBean
    private OrdersRepository ordersRepository;

    @Autowired
    private OrdersService ordersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /*****************TEST PARA OBTENER ORDENES*****************/
    @Test
    public void testGetOrders() {
        Orders order = new Orders(1L, 12L, 10.0, 5L, 1000.0, LocalDate.now(), "true");
        List<Orders> orders = Collections.singletonList(order);

        when(ordersRepository.findAll()).thenReturn(orders);

        List<Orders> result = ordersService.getOrders();

        assertEquals(1, result.size());
        assertEquals(12L, result.get(0).getProductId());
    }

    /*****************TEST PARA ELIMINAR ORDEN*****************/
    @Test
    public void testDeleteOrder() {
        Long orderId = 1L;
        Orders order = new Orders();
        order.setId(orderId);

        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(order));

        ResponseEntity<Object> response = ordersService.deleteOrders(orderId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order deleted successfully", response.getBody());

        verify(ordersRepository, times(1)).deleteById(orderId);
    }

    /*****************TEST PARA CREAR ORDEN*****************/
    @Test
    public void testNewOrder() {
        Orders order = new Orders(1L, 1L, 10.0, 5L, 1000.0, LocalDate.now(), "true");

        when(ordersRepository.save(order)).thenReturn(order);

        ResponseEntity<Object> response = ordersService.newOrders(order);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Order created successfully", response.getBody());

        verify(ordersRepository, times(1)).save(order);
    }

    /*****************TEST PARA ACTUALIZAR ORDEN*****************/
    @Test
    public void testUpdateOrder() {
        Long orderId = 1L;

        Orders existingOrder = new Orders(1L, 12L, 10.0, 5L, 1000.0, LocalDate.now(), "true");
        Orders updatedOrder = new Orders(1L, 12L, 20.0, 10L, 2000.0, LocalDate.now(), "false");

        when(ordersRepository.findById(orderId)).thenReturn(Optional.of(existingOrder));
        when(ordersRepository.save(existingOrder)).thenReturn(updatedOrder);

        ResponseEntity<Object> response = ordersService.updateOrders(orderId, updatedOrder);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Order updated successfully", response.getBody());

        assertEquals(updatedOrder.getUnitPrice(), existingOrder.getUnitPrice());
        assertEquals(updatedOrder.getQuantity(), existingOrder.getQuantity());
        assertEquals(updatedOrder.getTotal(), existingOrder.getTotal());
        assertEquals(updatedOrder.getDate(), existingOrder.getDate());
        assertEquals(updatedOrder.getNotes(), existingOrder.getNotes());
    }
}
