package com.restapi.dto;

import com.restapi.model.BeautyProducts;
import com.restapi.request.BeautyProductRequest;
import com.restapi.response.BeautyProductsResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BeautyProductsDto {

    // Map list of BeautyProducts entities to BeautyProductsResponse DTO
    public BeautyProductsResponse mapToBeautyProductsResponse(List<BeautyProducts> beautyProductsList) {
        return new BeautyProductsResponse(beautyProductsList);
    }

    // Map BeautyProductRequest DTO to BeautyProducts entity
    public BeautyProducts mapToBeautyProducts(BeautyProductRequest beautyProductRequest) {
        BeautyProducts beautyProducts = new BeautyProducts();
        if (beautyProductRequest.getId() != null) {
            beautyProducts.setId(beautyProductRequest.getId());
        }
        beautyProducts.setBrand(beautyProductRequest.getBrand());
        beautyProducts.setPrice(beautyProductRequest.getPrice());
        beautyProducts.setDescription(beautyProductRequest.getDescription());
        beautyProducts.setTitle(beautyProductRequest.getTitle());
        beautyProducts.setPhoto(beautyProductRequest.getPhoto());
        return beautyProducts;
    }
}
