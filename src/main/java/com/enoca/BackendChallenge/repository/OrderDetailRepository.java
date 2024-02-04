package com.enoca.BackendChallenge.repository;

import com.enoca.BackendChallenge.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail,Long> {


    List<OrderDetail> findByOrderId(Long orderId);
}
