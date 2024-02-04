package com.enoca.BackendChallenge.service.product;

import com.enoca.BackendChallenge.converter.ProductConverter;
import com.enoca.BackendChallenge.dto.ProductResponse;
import com.enoca.BackendChallenge.entity.Product;
import com.enoca.BackendChallenge.exception.GlobalException;
import com.enoca.BackendChallenge.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductResponse findById(long id)  {
        Optional<Product> productOptional= productRepository.findById(id);
        if(productOptional.isPresent()){
            Product product = productOptional.get();
            return ProductConverter.convertToResponse(product);
        }
        else {
            throw new GlobalException("Cart not found with id: " + id, HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public ProductResponse save(Product product) {
       Product createProduct= productRepository.save(product);

        return ProductConverter.convertToResponse(createProduct);
    }

    @Override
    public List<ProductResponse> getAll() {
       List<Product> allProducts= productRepository.findAll();
        return ProductConverter.convertListToResponse(allProducts) ;
    }

    @Override
    public void delete(long id) {
        ProductResponse productResponse = findById(id);

        if (productResponse.id() != null) {
            productRepository.deleteById(productResponse.id());
        } else {
            throw new GlobalException("Product not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ProductResponse update(Product updatedProduct) {
        ProductResponse productResponse = findById(updatedProduct.getId());
        if (productResponse != null) {
            updatedProduct.setCreatedDate(productResponse.createdDate());
            Product updatedEntity = productRepository.save(updatedProduct);
            return ProductConverter.convertToResponse(updatedEntity);
        }
            throw new GlobalException("Product not found with id: " + updatedProduct.getId(), HttpStatus.NOT_FOUND);
    }
}
