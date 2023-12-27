package com.restapi.response;

import com.restapi.model.Address;
import com.restapi.model.AppUser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse {

    private AppUser appUser;
    private List<Address> addressList;

}
