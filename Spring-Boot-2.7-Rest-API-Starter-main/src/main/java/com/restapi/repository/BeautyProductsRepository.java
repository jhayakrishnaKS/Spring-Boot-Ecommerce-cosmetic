package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.BeautyProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeautyProductsRepository  extends JpaRepository<BeautyProducts, Long> {
    List<BeautyProducts> findByCategoryId(Long categoryId);
}
