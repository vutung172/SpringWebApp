package com.ra.web.service.Impl;

import com.ra.web.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    public String findNameById(String id){
        return productRepository.findProductsEntityByProductId(id).getProductName();
    }
}
