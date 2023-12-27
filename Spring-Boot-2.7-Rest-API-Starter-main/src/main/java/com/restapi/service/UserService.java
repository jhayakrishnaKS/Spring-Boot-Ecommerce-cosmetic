package com.restapi.service;

import com.restapi.dto.AuthDto;
import com.restapi.exception.common.InvalidUserException;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.model.Role;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.RoleRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.LoginRequest;
import com.restapi.request.RegisterRequest;
import com.restapi.response.AuthResponse;
import com.restapi.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private AuthDto authDto;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AddressRepository addressRepository;

    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByUsername(registerRequest.getUsername())) {
            throw new InvalidUserException("Username already exists");
        }

        if (registerRequest.getPassword().length() < 4) {
            throw new InvalidUserException("Password should have at least 4 characters");
        }

        AppUser appUser = authDto.mapToAppUser(registerRequest);
        appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
        appUser.setRoles(roleRepository.findByName(Role.USER));
        appUser = userRepository.save(appUser);

        return authDto.mapToAuthResponse(appUser);
    }

    public AuthResponse login(LoginRequest loginRequest) {
        AppUser appUser = userRepository
                .findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidUserException("Invalid user credentials"));

        if (!bCryptPasswordEncoder.matches(loginRequest.getPassword(), appUser.getPassword())) {
            throw new InvalidUserException("Invalid password");
        }

        return authDto.mapToAuthResponse(appUser);
    }

    public List<AppUser> findAll() {
        return userRepository.findAll();
    }

    public AppUser findUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
    }


    public List<AuthResponse> findAllUsers() {
        List<AppUser> users= userRepository.findAllUsers();
        List<AuthResponse> userResponse=new ArrayList<>();
        for(AppUser user:users) {
            userResponse.add(authDto.mapToAuthResponse(user));
        }
        return userResponse;
    }
}
