package com.restapi.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class CartRequest {

    private Long userId;

    @NotNull(message = "beautyProductId cannot be null")
    private Long beautyProductId;

    @NotNull(message = "count cannot be null")
    private Integer count;

}
