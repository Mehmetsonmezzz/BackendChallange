package com.enoca.BackendChallenge.service.order;

import com.enoca.BackendChallenge.converter.OrderConverter;
import com.enoca.BackendChallenge.converter.ProductConverter;
import com.enoca.BackendChallenge.dto.OrderDetailResponse;
import com.enoca.BackendChallenge.dto.OrderResponse;
import com.enoca.BackendChallenge.dto.OrderWithOrderDetailResponse;
import com.enoca.BackendChallenge.entity.*;
import com.enoca.BackendChallenge.exception.GlobalException;
import com.enoca.BackendChallenge.repository.*;
import com.enoca.BackendChallenge.service.cart.CartService;
import com.enoca.BackendChallenge.service.product.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final ProductRepository productRepository;
    private final CartService cartService;
    private final OrderDetailRepository orderDetailRepository;

    private final CustomerRepository customerRepository;
    private final CartRepository cartRepository;
    public OrderServiceImpl(OrderRepository orderRepository, ProductService productService, ProductRepository productRepository, CartService cartService, OrderDetailRepository orderDetailRepository, CustomerRepository customerRepository, CartRepository cartRepository) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.productRepository = productRepository;
        this.cartService = cartService;
        this.orderDetailRepository = orderDetailRepository;
        this.customerRepository = customerRepository;
        this.cartRepository = cartRepository;
    }


    @Override
    public OrderResponse placeOrder(long customerId) {
        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new GlobalException("Müşteri bulunamadı.", HttpStatus.NOT_FOUND));

        Cart cart = customer.getCart();
        if (cart == null || cart.getProducts() == null || cart.getProducts().isEmpty()) {
            throw new GlobalException("Your cart is empty. You cannot order.",HttpStatus.BAD_REQUEST);
        }

        double totalPrice = cart.calculateTotalAmount();
        String orderCode = UUID.randomUUID().toString();
        Date orderDate = new Date();

        Order order = new Order();
        order.setTotalAmount(totalPrice);
        order.setOrderDate(orderDate);
        order.setOrderCode(orderCode);
        order.setCustomer(customer);
        orderRepository.save(order);

        for (Product product : cart.getProducts()) {
            int quantityInStock = product.getStockQuantity();
            double price = product.getPrice();
            int quantityForOrder = 1;

            if (quantityForOrder > quantityInStock) {
                throw new GlobalException("Product stock is insufficient. You cannot order.",HttpStatus.BAD_REQUEST);
            }

            product.setStockQuantity(quantityInStock - quantityForOrder);
            productRepository.save(product);

            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setOrder(order);
            orderDetail.setProduct(product);
            orderDetail.setProductName(product.getName());
            orderDetail.setQuantity(quantityForOrder);
            orderDetail.setPrice(price);
            orderDetailRepository.save(orderDetail);

        }
        List<OrderDetail> orderDetails = orderDetailRepository.findByOrderId(order.getId());
        order.setOrderDetails(orderDetails);
        cart.setProducts(new ArrayList<>());
        cartRepository.save(cart);

        return new OrderResponse(order.getTotalAmount(), order.getOrderCode(),order.getOrderDate() );
    }

    @Override
    public OrderWithOrderDetailResponse findByOrderCode(String orderCode) {
        Order order = orderRepository.findByOrderCode(orderCode);

        List<OrderDetail> orderDetails = order.getOrderDetails();


        List<OrderDetailResponse> orderDetailResponses = new ArrayList<>();

        for (OrderDetail orderDetail : orderDetails) {

            String productName = orderDetail.getProduct().getName();
            double productPrice = orderDetail.getPrice();
            int productQuantity = orderDetail.getQuantity();

            OrderDetailResponse orderDetailResponse = new OrderDetailResponse(productName, productPrice, productQuantity);


            orderDetailResponses.add(orderDetailResponse);
        }


        double totalAmount = order.getTotalAmount();
        String orderCode1 = order.getOrderCode();
        Date orderDate = order.getOrderDate();


        return new OrderWithOrderDetailResponse(totalAmount, orderCode1, orderDate, orderDetailResponses);
    }

    @Override
    public List<OrderResponse> getAllOrdersForCustomer(Long customerId) {
        List<Order> orders = orderRepository.findByCustomerId(customerId);
        if (orders == null) {
            orders = new ArrayList<>();
        }


        return new ArrayList<>(OrderConverter.convertListToResponse(orders));
    }
}