package com.restapi.service;

import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.AppUser;
import com.restapi.model.BeautyProducts;
import com.restapi.model.Cart;
import com.restapi.repository.BeautyProductsRepository;
import com.restapi.repository.CartRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.CartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BeautyProductsRepository beautyProductsRepository;

    // Get user's cart endpoint
    public List<Cart> findUserCart(Long userId) {
        List<Cart> cart = cartRepository.findUserCart(userId)
                .orElseThrow(() -> new ResourceNotFoundException("cart", "userId", userId));
        return cart;
    }

    // Add to cart endpoint
    @Transactional
    public List<Cart> addToCart(CartRequest cartRequest) {
        AppUser appUser = userRepository.findById(cartRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        cartRequest.getUserId()));

        BeautyProducts beautyProducts = beautyProductsRepository.findById(cartRequest.getBeautyProductId())
                .orElseThrow(() -> new ResourceNotFoundException("beautyProductsId", "beautyProductsId",
                        cartRequest.getBeautyProductId()));

        Optional<List<Cart>> cartOptional = cartRepository.findUserCart(cartRequest.getUserId());

        if (cartOptional.isPresent()) {
            boolean isPresent = false;
            for (Cart cart : cartOptional.get()) {
                if (cart.getBeautyProducts().getId().equals(cartRequest.getBeautyProductId())) {
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);
                    isPresent = true;
                }
            }
            if (!isPresent) {
                Cart cart = new Cart();
                cart.setAppUser(appUser);
                cart.setBeautyProducts(beautyProducts);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }
        } else {
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setBeautyProducts(beautyProducts);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        }
        return findUserCart(cartRequest.getUserId());
    }

    // Delete beauty products from cart endpoint
    @Transactional
    public List<Cart> deleteBeautyProductsFromCart(Long userId, Long beautyProductsCartId) {
        // Check if the cart exists before attempting to delete
        Optional<Cart> cartOptional = cartRepository.findById(beautyProductsCartId);
        if (cartOptional.isPresent() && cartOptional.get().getAppUser().getId().equals(userId)) {
            // If the cart exists and belongs to the specified user, delete it
            cartRepository.deleteById(beautyProductsCartId);
        }
        return findUserCart(userId);
    }


    public List<Cart> updateCart(Long userId, CartRequest cartRequest) {
        AppUser appUser = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId", userId));

        BeautyProducts beautyProducts = beautyProductsRepository.findById(cartRequest.getBeautyProductId())
                .orElseThrow(() -> new ResourceNotFoundException("beautyProductsId", "beautyProductsId", cartRequest.getBeautyProductId()));

        Optional<List<Cart>> cartOptional = cartRepository.findUserCart(userId);

        // checking if cart is present
        if (cartOptional.isPresent()) {
            boolean isPresent = false;
            for (Cart cart : cartOptional.get()) {
                if (cart.getBeautyProducts().getId().equals(cartRequest.getBeautyProductId())) {
                    cart.setCount(cartRequest.getCount());
                    cartRepository.save(cart);
                    isPresent = true;
                }
            }

            // cart not present
            if (!isPresent) {
                Cart cart = new Cart();
                cart.setAppUser(appUser);
                cart.setBeautyProducts(beautyProducts);
                cart.setCount(cartRequest.getCount());
                cartRepository.save(cart);
            }
        } else {
            // cart not present
            Cart cart = new Cart();
            cart.setAppUser(appUser);
            cart.setBeautyProducts(beautyProducts);
            cart.setCount(cartRequest.getCount());
            cartRepository.save(cart);
        }
        return findUserCart(userId);
    }

}
