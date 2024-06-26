package com.ra.web.service.Impl;

import com.ra.web.model.entity.ProductEntity;
import com.ra.web.repository.ProductRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class ProductServiceImpl {
    @Autowired
    private ProductRepository productRepository;
    public String findNameById(String id){
        return productRepository.findProductsEntityByProductId(id).getProductName();
    }

    public ProductEntity findById(String id){
        return productRepository.findById(id).orElse(null);
    }
}
