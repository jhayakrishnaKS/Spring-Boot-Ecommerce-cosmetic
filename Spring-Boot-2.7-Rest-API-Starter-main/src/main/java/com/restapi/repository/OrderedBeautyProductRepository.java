package com.restapi.repository;

import com.restapi.model.OrderedBeautyProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderedBeautyProductRepository extends JpaRepository<OrderedBeautyProduct, Long> {
}
