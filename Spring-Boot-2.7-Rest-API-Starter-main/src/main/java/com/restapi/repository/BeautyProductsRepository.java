package com.restapi.repository;

import com.restapi.model.Address;
import com.restapi.model.BeautyProducts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyProductsRepository  extends JpaRepository<BeautyProducts, Long> {
}
