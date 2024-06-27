package com.ucc.crudservice.controller;

import com.ucc.crudservice.model.entities.Product;
import com.ucc.crudservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/products")
@RequiredArgsConstructor

public class productController {

    private final ProductService productService;

    // get all
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Product> getAllProducts(){
        return this.productService.getProducts();
    }
    // post
    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public void newProduct(@RequestBody Product product){
        this.productService.addProduct(product);
    }

    // delete
    @DeleteMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteProduct(@PathVariable("id") Long id) {
        System.out.println(id);
        this.productService.deleteProduct(id);
    }
    // update
    @PutMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void updateProduct(@PathVariable("id") Long id, @RequestBody Product updatedProduct) {
        this.productService.updateProduct(id, updatedProduct);
    }
    @GetMapping(path = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Product getProductById(@PathVariable("id") Long id){
        return this.productService.getProductById(id).orElseThrow(() -> new RuntimeException("Product not found"));
    }
}