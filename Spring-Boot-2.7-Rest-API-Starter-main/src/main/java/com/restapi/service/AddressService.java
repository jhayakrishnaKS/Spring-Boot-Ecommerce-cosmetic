package com.restapi.service;

import com.restapi.dto.AddressDto;
import com.restapi.exception.common.ResourceNotFoundException;
import com.restapi.model.Address;
import com.restapi.model.AppUser;
import com.restapi.repository.AddressRepository;
import com.restapi.repository.UserRepository;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AddressDto addressDto;

    // Get all addresses endpoint
    public AddressResponse findAll() {
        List<Address> addressList = addressRepository.findAll();
        return addressDto.mapToAddressResponse(addressList);
    }

    // Create address endpoint
    @Transactional
    public void create(AddressRequest addressRequest) {
        Address address = addressDto.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);

    }

    // Update address endpoint
    public AddressResponse update(AddressRequest addressRequest) {
        System.out.println(addressRequest);
        Address address = addressDto.mapToAddress(addressRequest);
        AppUser appUser = userRepository.findById(addressRequest.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("userId", "userId",
                        addressRequest.getUserId()));
        address.setAppUser(appUser);
        addressRepository.save(address);

        return findAll();
    }

    // Delete address by ID endpoint
    public AddressResponse deleteById(Long id) {
        addressRepository.deleteById(id);
        return findAll();
    }
}