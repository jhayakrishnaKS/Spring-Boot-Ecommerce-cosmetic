package com.restapi.dto;

import com.restapi.model.Order;
import com.restapi.response.OrderResponse;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDto {

    // Map list of Order entities to list of OrderResponse DTOs
    public List<OrderResponse> mapToOrderResponse(List<Order> orderList) {
        List<OrderResponse> orderResponseList = new ArrayList<>();

        // Iterate through each Order entity and map it to OrderResponse DTO
        for (Order order : orderList) {
            OrderResponse orderResponse = new OrderResponse();
            orderResponse.setId(order.getId());
            orderResponse.setUserId(order.getAppUser().getId());
            orderResponse.setName(order.getAppUser().getName());
            orderResponse.setUsername(order.getAppUser().getUsername());
            orderResponse.setOrderStatus(order.getOrderStatus().getStatus());
            orderResponse.setAddress(order.getAddress());
            orderResponse.setOrderedBeautyProductList(order.getOrderedBeautyProductList());
            orderResponseList.add(orderResponse);
        }

        return orderResponseList;
    }
}
