package com.enoca.BackendChallenge.controller;


import com.enoca.BackendChallenge.converter.ProductConverter;
import com.enoca.BackendChallenge.dto.ProductResponse;
import com.enoca.BackendChallenge.entity.Product;
import com.enoca.BackendChallenge.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;


    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> GetProduct(@PathVariable long id){
      ProductResponse product=  productService.findById(id);

        return new ResponseEntity<>(product, HttpStatus.OK);


    }

    @PostMapping("/save")
    public ResponseEntity<ProductResponse> CreateProduct(@RequestBody Product product){
           ProductResponse productResponse= productService.save(product);

        return new ResponseEntity<>(productResponse,HttpStatus.CREATED) ;
    }

    @DeleteMapping("/{id}")
    public String DeleteProduct(@PathVariable long id){
       productService.delete(id);

       return "The product with ID "+ id+" has been deleted.";
    }

    @GetMapping("/getAll")
    public ResponseEntity<List<ProductResponse>> GetAllProducts(){
        List<ProductResponse> product= productService.getAll();
        return new ResponseEntity<>(product, HttpStatus.OK);
    }

    @PutMapping("/")
    public ResponseEntity<ProductResponse> updateProduct( @RequestBody Product product) {
        ProductResponse updatedProduct=productService.update(product);
        return new ResponseEntity<>(updatedProduct, HttpStatus.OK);
    }

}
