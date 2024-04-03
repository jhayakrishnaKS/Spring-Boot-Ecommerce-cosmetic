package com.restapi.controller;

import com.restapi.model.BeautyProducts;
import com.restapi.model.Role;
import com.restapi.response.common.APIResponse;
import com.restapi.service.BeautyProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/api/BeautyProduct")
//@RolesAllowed(Role.USER)
public class BeautyProductController {
    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private BeautyProductsService beautyProductsService;

    // Get all beauty products endpoint
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllBeautyProducts() {
        List<BeautyProducts> beautyProductsList = beautyProductsService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProductsList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
    // Get beauty products by category id endpoint
    @GetMapping("/category/{categoryId}")
    public ResponseEntity<APIResponse> getBeautyProductsByCategory(@PathVariable Long categoryId) {
        List<BeautyProducts> beautyProductsList = beautyProductsService.findByCategoryId(categoryId);
            apiResponse.setStatus(HttpStatus.OK.value());
            apiResponse.setData(beautyProductsList);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
