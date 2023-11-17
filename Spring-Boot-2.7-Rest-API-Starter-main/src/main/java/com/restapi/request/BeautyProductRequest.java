package com.restapi.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BeautyProductRequest {

    private Long id;

    private Long categoryId;

    @NotEmpty(message = "Field cannot be Empty")
    @Size(min = 3,message = "title should have at least 3 characters")
    private String title;

    @NotEmpty(message = "Field cannot be Empty")
    @Size(min = 3,message = "description should have at least 3 characters")
    private String description;

    @NotEmpty(message = "Field cannot be Empty")
    @Size(min = 3,message = "brand name should have at least 3 characters")
    private String Brand;


    private Double price;

    private byte[] photo;
}
