package com.restapi.controller.admin;

import com.restapi.model.BeautyProducts;
import com.restapi.model.Role;
import com.restapi.request.BeautyProductRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.BeautyProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/BeautyProduct")
//@PreAuthorize("hasRole('ROLE_ADMIN')")
@RolesAllowed(Role.ADMIN)
public class AdminBeautyProductController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private BeautyProductsService beautyProductsService;

    // Get all beauty products
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllBeautyProducts() {
        List<BeautyProducts> beautyProducts = beautyProductsService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Create a new beauty product
    @PostMapping
    public ResponseEntity<APIResponse> createBeautyProduct(@Valid @RequestBody BeautyProductRequest beautyProductRequest) {
        List<BeautyProducts> beautyProducts = beautyProductsService.createBeautyProduct(beautyProductRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Update an existing beauty product
    @PutMapping
    public ResponseEntity<APIResponse> updateBeautyProduct(@Valid @RequestBody BeautyProductRequest beautyProductRequest) {
        List<BeautyProducts> beautyProducts = beautyProductsService.updateBeautyProduct(beautyProductRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Delete a beauty product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteBeautyProduct(@PathVariable Integer id) {
        List<BeautyProducts> beautyProducts = beautyProductsService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
