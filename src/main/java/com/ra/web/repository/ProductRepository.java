package com.ra.web.repository;

import com.ra.web.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity,String> {
    ProductEntity findProductsEntityByProductId(String id);
}
