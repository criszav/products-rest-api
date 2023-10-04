package com.czavala.productrestapi.controller;

import com.czavala.productrestapi.models.entities.Product;
import com.czavala.productrestapi.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Product> products = productService.findAll();

        if (products.isEmpty()) {
            return new ResponseEntity<>("No hay productos", HttpStatus.OK);
        }

        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        Product product = productService.findById(id);

        if (product == null) {
            return new ResponseEntity<>("Producto no existe", HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Product product) {

        try {
            var productSave = productService.save(product);
            return new ResponseEntity<>(productSave, HttpStatus.CREATED);
        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody Product product) {
        Product productUpdate = productService.update(id, product);

        try {
            if (productUpdate == null) {
                return new ResponseEntity<>("Producto no existe", HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(productUpdate, HttpStatus.OK);

        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        Product product = productService.findById(id);

        try {
            if (product == null) {
                return new ResponseEntity<>("Producto no existe", HttpStatus.NOT_FOUND);
            }
            productService.delete(product.getId());
            return new ResponseEntity<>("Producto eliminado", HttpStatus.NO_CONTENT);

        } catch (DataAccessException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
