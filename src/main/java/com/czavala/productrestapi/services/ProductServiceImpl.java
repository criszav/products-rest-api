package com.czavala.productrestapi.services;

import com.czavala.productrestapi.models.entities.Product;
import com.czavala.productrestapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product save(Product product) {
        Product productSave = Product.builder()
                .id(product.getId())
                .nombre(product.getNombre())
                .precio(product.getPrecio())
                .marca(product.getMarca())
                .tipoProducto(product.getTipoProducto())
                .build();
        return productRepository.save(productSave);
    }

    @Override
    public Product update(Long id, Product product) {
        Product productFound = productRepository.findById(id).orElse(null);

        if (productFound != null) {
            productFound.setNombre(product.getNombre());
            productFound.setPrecio(product.getPrecio());
            productFound.setMarca(product.getMarca());
            productFound.setTipoProducto(product.getTipoProducto());

            return productRepository.save(productFound);
        }
        return productFound;
    }

    @Override
    public void delete(Long id) {
            productRepository.deleteById(id);
        }

}
