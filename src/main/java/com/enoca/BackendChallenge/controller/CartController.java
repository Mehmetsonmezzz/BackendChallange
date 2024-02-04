package com.enoca.BackendChallenge.controller;

import com.enoca.BackendChallenge.dto.CartResponse;
import com.enoca.BackendChallenge.service.cart.CartService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/cart")
public class CartController {

 private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }


    @PostMapping("/addProduct")
    public ResponseEntity<CartResponse> addProductToCart(@RequestParam Long customerId, @RequestParam Long productId) {

        CartResponse cartResponse = cartService.addProductToCart(customerId, productId);
        return new ResponseEntity<>(cartResponse, HttpStatus.OK);

    }

    @PostMapping("/removeProduct")
    public ResponseEntity<CartResponse> removeProductFromCart(@RequestParam Long customerId, @RequestParam Long productId) {
            CartResponse cartResponse = cartService.removeProductFromCart(customerId, productId);
            return new ResponseEntity<>(cartResponse, HttpStatus.OK);
    }



    @GetMapping("/{customerId}")
    public ResponseEntity<CartResponse> GetCard(@PathVariable long customerId){
        CartResponse cartResponse = cartService.getCartByCustomerId(customerId);

       return new ResponseEntity<>(cartResponse,HttpStatus.OK);
    }

    @PostMapping("/emptyCart")
    public ResponseEntity<CartResponse> emptyCart(@RequestParam Long customerId) {

            CartResponse cartResponse = cartService.emptyCart(customerId);
            return new ResponseEntity<>(cartResponse,HttpStatus.OK);
    }

    @PostMapping("/updateCard/{customerId}")
    public ResponseEntity<CartResponse> updateCart(@PathVariable Long customerId, @RequestBody List<Long> productIds) {

            CartResponse cartResponse = cartService.updateCart(customerId, productIds);
            return  new ResponseEntity<>(cartResponse,HttpStatus.OK);
    }




}
