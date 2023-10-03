package com.czavala.productrestapi.services;

import com.czavala.productrestapi.models.entities.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Product findById(Long id);

    Product save(Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}
