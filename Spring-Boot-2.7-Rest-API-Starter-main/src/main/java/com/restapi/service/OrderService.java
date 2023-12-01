package com.restapi.service;

import com.restapi.dto.OrderDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.*;
import com.restapi.repository.*;
import com.restapi.response.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderDto orderDto;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private OrderedBeautyProductRepository orderedBeautyProductRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private OrderStatusRepository orderStatusRepository;

    @Transactional
    public List<OrderResponse> placeOrder(Long userId, Long addressId) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));

        Address address = addressRepository.findById(addressId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("addressId", "addressId", addressId));

        OrderStatus orderStatus = orderStatusRepository.findById(1L)
                .orElseThrow(() ->
                        new ResourceNotFoundException("orderStatusId", "orderStatusId", 1));

        List<Cart> cartList = cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));

        Order order = new Order();
        order.setAddress(address);
        order.setOrderStatus(orderStatus);
        order.setAppUser(appUser);

        order = orderRepository.save(order);

        for (Cart cart : cartList) {
            OrderedBeautyProduct orderedBeautyProduct = new OrderedBeautyProduct();
            orderedBeautyProduct.setOrder(order);
            orderedBeautyProduct.setTitle(cart.getBeautyProducts().getTitle());
            orderedBeautyProduct.setBrand(cart.getBeautyProducts().getBrand());
            orderedBeautyProduct.setDescription(cart.getBeautyProducts().getDescription());
            orderedBeautyProduct.setPrice(cart.getBeautyProducts().getPrice());
            orderedBeautyProduct.setCount(cart.getCount());
            orderedBeautyProductRepository.save(orderedBeautyProduct);
            cartRepository.delete(cart);
        }

        return getUserOrders(userId);
    }

    public List<OrderResponse> getUserOrders(Long userId) {
        List<Order> orderList = orderRepository.findUserOrder(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderResponse> getAllOrders() {
        List<Order> orderList = orderRepository.findAll();
        return orderDto.mapToOrderResponse(orderList);
    }

    public List<OrderStatus> getAllOrderStatus() {
        return orderStatusRepository.findAll();
    }

    @Transactional
    public List<OrderResponse> updateOrderStatus(Long orderId, Long statusId) {
        System.out.println(orderId);
        System.out.println(statusId);
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new ResourceNotFoundException("orderId", "orderId", orderId));

        OrderStatus orderStatus = orderStatusRepository.findById(statusId)
                .orElseThrow(() -> new ResourceNotFoundException("statusId", "statusId", statusId));

        order.setOrderStatus(orderStatus);

        orderRepository.save(order);
        return getAllOrders();

    }
}
