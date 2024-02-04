package com.enoca.BackendChallenge.service.product;

import com.enoca.BackendChallenge.dto.ProductResponse;
import com.enoca.BackendChallenge.entity.Product;

import java.util.List;

public interface ProductService {
    ProductResponse findById(long id);
    ProductResponse save(Product product);

    List<ProductResponse> getAll();

    void delete(long id);
     ProductResponse update(Product updatedProduct);
}
