package com.restapi.controller;

import com.restapi.model.Role;
import com.restapi.request.CartRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;

@RestController
@RequestMapping("/api/cart")
@RolesAllowed(Role.USER)
public class CartController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private CartService cartService;

    // Get user's cart endpoint
    @GetMapping("/{userId}")
    public ResponseEntity<APIResponse> getUsersCart(@PathVariable Long userId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.findUserCart(userId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Add to cart endpoint
    @PostMapping
    public ResponseEntity<APIResponse> addToCart(@Valid @RequestBody CartRequest cartRequest) {
        apiResponse.setStatus(HttpStatus.OK.value());
        cartService.addToCart(cartRequest);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Delete beauty products from cart endpoint
    @DeleteMapping("/{userId}/{beautyProductsCartId}")
    public ResponseEntity<APIResponse> deleteBeautyProductsFromCart(@PathVariable Long userId,
                                                                    @PathVariable Long beautyProductsCartId) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.deleteBeautyProductsFromCart(userId, beautyProductsCartId));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Update cart endpoint (PUT)
    @PutMapping("/{userId}")
    public ResponseEntity<APIResponse> updateCart(@PathVariable Long userId,
                                                  @Valid @RequestBody CartRequest cartRequest) {
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(cartService.updateCart(userId, cartRequest));
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }
}
