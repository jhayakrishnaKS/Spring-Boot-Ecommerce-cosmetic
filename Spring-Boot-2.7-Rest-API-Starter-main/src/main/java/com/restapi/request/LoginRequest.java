package com.restapi.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
public class LoginRequest {

    @NotEmpty(message = "field cannot be empty")
    @Size(min = 2, message = "Username should have at least 2 characters")
    private String username;

    @NotEmpty(message = "field cannot be empty")
    @Size(min = 2, message = "Password should have at least 2 characters")
    private String password;
}
