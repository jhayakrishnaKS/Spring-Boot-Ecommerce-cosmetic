package com.restapi.service;

import com.restapi.dto.BeautyProductsDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.BeautyProducts;
import com.restapi.model.Category;
import com.restapi.repository.BeautyProductsRepository;
import com.restapi.repository.CategoryRepository;
import com.restapi.request.BeautyProductRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class BeautyProductsService {

    @Autowired
    private BeautyProductsDto beautyProductsDto;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private BeautyProductsRepository beautyProductsRepository;

    // Get all beauty products endpoint
    public List<BeautyProducts> findAll() {
        return beautyProductsRepository.findAll();
    }

    // Create beauty product endpoint
    @Transactional
    public List<BeautyProducts> createBeautyProduct(BeautyProductRequest beautyProductRequest) {
        BeautyProducts beautyProducts = beautyProductsDto.mapToBeautyProducts(beautyProductRequest);
        Category category = categoryRepository.findById(beautyProductRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", beautyProductRequest.getCategoryId()));
        beautyProducts.setCategory(category);
        beautyProductsRepository.save(beautyProducts);
        return findAll();
    }

    // Update beauty product endpoint
    @Transactional
    public List<BeautyProducts> updateBeautyProduct(BeautyProductRequest beautyProductRequest) {
        BeautyProducts beautyProducts = beautyProductsDto.mapToBeautyProducts(beautyProductRequest);
        Category category = categoryRepository.findById(beautyProductRequest.getCategoryId())
                .orElseThrow(() -> new ResourceNotFoundException("CategoryId",
                        "CategoryId", beautyProductRequest.getCategoryId()));
        beautyProducts.setCategory(category);
        beautyProductsRepository.save(beautyProducts);
        return findAll();
    }

    // Delete beauty product by ID endpoint
    public List<BeautyProducts> deleteById(Integer id) {
        beautyProductsRepository.deleteById(Long.valueOf(id));
        return findAll();
    }
}
