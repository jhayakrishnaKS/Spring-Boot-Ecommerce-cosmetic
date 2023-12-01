package com.restapi.dto;

import com.restapi.model.AppUser;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import org.springframework.stereotype.Component;

@Component
public class AuthDto {

    // Map RegisterRequest DTO to AppUser entity
    public AppUser mapToAppUser(RegisterRequest user) {
        AppUser appUser = new AppUser();
        appUser.setUsername(user.getUsername());
        appUser.setName(user.getName());
        appUser.setPassword(user.getPassword());

        return appUser;
    }

    // Map AppUser entity to AuthResponse DTO
    public AuthResponse mapToAuthResponse(AppUser appUser) {
        AuthResponse authResponse = new AuthResponse();
        authResponse.setId(appUser.getId());
        authResponse.setName(appUser.getName());
        authResponse.setUsername(appUser.getUsername());
        authResponse.setRole(appUser.getRoles().getName());
        return authResponse;
    }
}
