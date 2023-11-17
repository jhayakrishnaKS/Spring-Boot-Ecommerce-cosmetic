package com.restapi.response;

import com.restapi.model.BeautyProducts;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class BeautyProductsResponse {

    List<BeautyProducts> beautyProductsList;
}
