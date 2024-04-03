package com.restapi.controller.admin;

import com.restapi.model.BeautyProducts;
import com.restapi.model.Role;
import com.restapi.request.BeautyProductRequest;
import com.restapi.response.common.APIResponse;
import com.restapi.service.BeautyProductsService;
import com.restapi.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.security.RolesAllowed;
import javax.validation.Valid;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/admin/BeautyProduct")
@RolesAllowed(Role.ADMIN)
public class AdminBeautyProductController {

    @Autowired
    private APIResponse apiResponse;

    @Autowired
    private StorageService storageService;
    @Autowired
    private BeautyProductsService beautyProductsService;

    // Get all beauty products
    @GetMapping("/all")
    public ResponseEntity<APIResponse> getAllBeautyProducts() {
        List<BeautyProducts> beautyProducts = beautyProductsService.findAll();
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Create a new beauty product
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
//    public ResponseEntity<APIResponse> createBeautyProduct(@RequestParam("photo") MultipartFile photo,
////                                                           @RequestParam("id") Long id,
//                                                           @RequestParam("categoryId")Long categoryId,
//                                                           @RequestParam("title") String title,
//                                                           @RequestParam("description") String description,
//                                                           @RequestParam("brand") String brand,
//                                                           @RequestParam("price") Double price) throws IOException {
//
//        String file = storageService.storeFile(photo);
//        BeautyProductRequest beautyProductRequest = new BeautyProductRequest();
////        beautyProductRequest.setId(id);
//        beautyProductRequest.setCategoryId(categoryId);
//        beautyProductRequest.setTitle(title);
//        beautyProductRequest.setDescription(description);
//        beautyProductRequest.setBrand(brand);
//        beautyProductRequest.setPrice(price);
//        beautyProductRequest.setPhoto(file);
//
//        List<BeautyProducts> beautyProducts = beautyProductsService.createBeautyProduct(beautyProductRequest);
//        apiResponse.setStatus(HttpStatus.OK.value());
//        apiResponse.setData(beautyProducts);
//        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
//    }
    @PostMapping(consumes ="multipart/form-data",produces = "application/json")
    public ResponseEntity<APIResponse> createBeautyProduct(@ModelAttribute BeautyProductRequest beautyProductRequest) throws IOException {
        String file = storageService.storeFile(beautyProductRequest.getImage());
        beautyProductRequest.setPhoto(file);
        List<BeautyProducts> beautyProducts = beautyProductsService.createBeautyProduct(beautyProductRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }


    // Update an existing beauty product
    @PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<APIResponse> updateBeautyProduct(
                                                           @RequestParam("id") Long id,
                                                           @RequestParam("title") String title,
                                                           @RequestParam("description") String description,
                                                           @RequestParam("brand") String brand,
                                                           @RequestParam("price") Double price) throws IOException {

        BeautyProductRequest beautyProductRequest = new BeautyProductRequest();
        beautyProductRequest.setId(id);

        beautyProductRequest.setTitle(title);
        beautyProductRequest.setDescription(description);
        beautyProductRequest.setBrand(brand);
        beautyProductRequest.setPrice(price);
        List<BeautyProducts> beautyProducts = beautyProductsService.updateBeautyProduct(beautyProductRequest);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    // Delete a beauty product by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteBeautyProduct(@PathVariable Integer id) {
        List<BeautyProducts> beautyProducts = beautyProductsService.deleteById(id);
        apiResponse.setStatus(HttpStatus.OK.value());
        apiResponse.setData(beautyProducts);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

//    @GetMapping("/downloadFile/{id}")
//    public ResponseEntity<Resource> downloadFile(@PathVariable Long id) throws IOException {
//
//        File file = beautyProductsService.getFile(id);
//
//        InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
//        headers.add("Pragma", "no-cache");
//        headers.add("Expires", "0");
//
//        return ResponseEntity.ok()
//                .headers(headers)
//                .contentLength(file.length())
//                .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                .body(resource);
//    }

}
