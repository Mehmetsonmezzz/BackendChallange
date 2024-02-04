package com.enoca.BackendChallenge.converter;

import com.enoca.BackendChallenge.dto.ProductResponse;
import com.enoca.BackendChallenge.entity.Product;

import java.util.List;
import java.util.stream.Collectors;

public class ProductConverter {
    public static ProductResponse convertToResponse(Product product) {
        return new ProductResponse(product.getId(), product.getName(), product.getCategory(),product.getPrice(),product.getStockQuantity(),product.getCreatedDate());
    }

    public static List<ProductResponse> convertListToResponse(List<Product> productList) {
        return productList.stream()
                .map(ProductConverter::convertToResponse)
                .collect(Collectors.toList());
    }


}
