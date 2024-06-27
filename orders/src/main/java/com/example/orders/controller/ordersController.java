package com.example.orders.controller;

import com.example.orders.model.entities.Orders;
import com.example.orders.service.OrdersService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping(path = "api/orders")
@RequiredArgsConstructor

public class ordersController {

    private final OrdersService ordersService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Orders> getOrders() {return ordersService.getOrders();}


    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> newOrders (@Valid @RequestBody Orders order, BindingResult bindingResult) {
        if (bindingResult.hasErrors()){
            List <String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
        return ordersService.newOrders(order);
    }


    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Object> updateOrders(@PathVariable("id") Long id, @RequestBody Orders updatedOrders) {
        return ordersService.updateOrders(id, updatedOrders);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Object> deleteOrders(@PathVariable("id") Long id) {
        return ordersService.deleteOrders(id);
    }

}
