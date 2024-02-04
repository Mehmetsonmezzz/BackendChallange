package com.enoca.BackendChallenge.service.cart;
import com.enoca.BackendChallenge.converter.CartConverter;
import com.enoca.BackendChallenge.converter.ProductConverter;
import com.enoca.BackendChallenge.dto.CartResponse;
import com.enoca.BackendChallenge.entity.Cart;
import com.enoca.BackendChallenge.entity.Customer;
import com.enoca.BackendChallenge.entity.Product;
import com.enoca.BackendChallenge.exception.GlobalException;
import com.enoca.BackendChallenge.repository.CartRepository;
import com.enoca.BackendChallenge.repository.CustomerRepository;
import com.enoca.BackendChallenge.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;
    private final ProductRepository productRepository;

    private final CustomerRepository customerRepository;
    public CartServiceImpl(CartRepository cartRepository, ProductRepository productRepository,CustomerRepository customerRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
        this.customerRepository=customerRepository;
    }

    @Override
    public CartResponse addProductToCart(long customerId, long productId) {
        Cart cart = getOrCreateCart(customerId);

        Product product = getProduct(productId);

        cart.getProducts().add(product);
        cart.updateTotalAmount();
       Cart cart1= cartRepository.save(cart);
        return CartConverter.convertToResponse(cart1);
    }

    @Override
    public CartResponse findById(long id) {
        Optional<Cart> cartOptional = cartRepository.findById(id);
        if (cartOptional.isPresent()) {
            Cart cart = cartOptional.get();
            return new CartResponse(cart.getId(), cart.getTotalPrice(),
                    ProductConverter.convertListToResponse(cart.getProducts()));
        } else {
            throw new GlobalException("Cart not found with id: " + id, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public CartResponse getCartByCustomerId(long customerId) {
        Cart cart = cartRepository.getCartByCustomer(customerId)
                .orElseThrow(() -> new GlobalException("Cart not found for customer with id: " + customerId,HttpStatus.NOT_FOUND));

        return new CartResponse(cart.getId(),cart.getTotalPrice(),ProductConverter.convertListToResponse(cart.getProducts()));
    }

    @Override
    public CartResponse removeProductFromCart(Long customerId, Long productId) {
            Cart cart = getOrCreateCart(customerId);
            Product product = getProduct(productId);
            cart.getProducts().remove(product);
            cart.updateTotalAmount();
            cartRepository.save(cart);
            return new CartResponse(cart.getId(),cart.getTotalPrice(),ProductConverter.convertListToResponse(cart.getProducts()));

    }


    @Override
    public CartResponse emptyCart(Long customerId) {
        Cart cart = getOrCreateCart(customerId);

        cart.getProducts().clear();
        cart.updateTotalAmount();
        cartRepository.save(cart);

        return new CartResponse(cart.getId(), cart.getTotalPrice(),new ArrayList<>());
    }

    @Override
    public CartResponse updateCart(Long customerId, List<Long> productIds) {

            Cart cart = getOrCreateCart(customerId);
            cart.getProducts().clear();
            for (Long productId : productIds) {
                Product product = getProduct(productId);
                cart.getProducts().add(product);
            }
            cart.updateTotalAmount();
            cartRepository.save(cart);
            return new CartResponse(cart.getId(),cart.getTotalPrice(),ProductConverter.convertListToResponse(cart.getProducts()));
    }

    private Cart getOrCreateCart(long customerId) {
        return cartRepository.findById(customerId)
                .orElseGet(() -> {
                    Customer customer = customerRepository.findById(customerId)
                            .orElseThrow(() -> new GlobalException("Customer not found with id: " + customerId,HttpStatus.NOT_FOUND));

                    Cart newCart = new Cart();
                    newCart.setCustomer(customer);
                    return newCart;
                });
    }

    private Product getProduct(long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() -> new GlobalException("Product not found with id: " + productId,HttpStatus.NOT_FOUND));
    }


}
