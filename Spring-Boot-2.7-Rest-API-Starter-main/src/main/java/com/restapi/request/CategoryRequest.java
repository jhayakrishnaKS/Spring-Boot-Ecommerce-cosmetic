package com.restapi.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryRequest {

    private Long id;

    @NotEmpty(message = "field cannot be empty")
    @Size(min = 3,message = "title should have at least 3 characters")
    private String title;

    public CategoryRequest(String title) {
        this.title = title;
    }
}
