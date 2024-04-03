package com.restapi.dto;

import com.restapi.model.Address;
import com.restapi.request.AddressRequest;
import com.restapi.response.AddressResponse;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class AddressDto {

    // Map Address entities to AddressResponse DTO
    public AddressResponse mapToAddressResponse(List<Address> addressList) {
        return new AddressResponse(addressList);
    }

    // Map AddressRequest DTO to Address entity
    public Address mapToAddress(AddressRequest addressRequest) {
        Address address = new Address();
        if (addressRequest.getId() != null) {
            address.setId(addressRequest.getId());
        }
        address.setAddress(addressRequest.getAddress());
        address.setCity(addressRequest.getCity());
        address.setZipcode(addressRequest.getZipcode());
        address.setState(addressRequest.getState());
        address.setPhoneNumber(addressRequest.getPhoneNumber());
        return address;
    }


}
