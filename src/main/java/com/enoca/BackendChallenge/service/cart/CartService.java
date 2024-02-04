package com.enoca.BackendChallenge.service.cart;

import com.enoca.BackendChallenge.dto.CartResponse;
import com.enoca.BackendChallenge.dto.ProductResponse;

import java.util.List;

public interface CartService {
    CartResponse addProductToCart(long customerId, long productId);

    CartResponse findById(long id);

     CartResponse getCartByCustomerId(long customerId);

     CartResponse removeProductFromCart(Long customerId, Long productId);


     CartResponse emptyCart(Long customerId);

   CartResponse updateCart(Long customerId, List<Long> productIds);



}
