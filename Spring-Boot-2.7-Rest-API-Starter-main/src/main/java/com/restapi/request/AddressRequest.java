package com.restapi.request;

import lombok.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AddressRequest {

    private Long id;

    @NotNull(message = "User ID cannot be null")
    private Long userId;

    @NotEmpty(message = "Address cannot be empty")
    @Size(min = 4, message = "Address should have at least 4 characters")
    private String address;

    @NotEmpty(message = "City cannot be empty")
    @Size(min = 3, message = "Enter the correct city name")
    private String city;

    @NotEmpty(message = "state cannot be empty")
    @Size(min = 3, message = "Enter the correct state name")
    private String state;

    @NotNull(message = "Zipcode cannot be null")
    @Digits(integer = 6, fraction = 0, message = "Zipcode must be a numeric value with up to 6 digits")
    private Integer zipcode;

    @NotNull(message = "phoneNumber cannot be null")
    @Digits(integer = 10, fraction = 0, message = "phoneNumber must be a numeric value with up to 10 digits")
    private Long phoneNumber;
}
