package com.ra.web.service.Impl;

import com.ra.web.model.entity.ProductEntity;
import com.ra.web.model.entity.accounts.AccEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Locale;
import java.util.TimeZone;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ProductServiceImplTest {
    @Autowired
    private ProductServiceImpl productService;
    private ProductEntity dataTest() {
        Date date = Date.valueOf("2024-03-11");

        return ProductEntity.builder()
                .productId("A0001")
                .productName("Laptop Dell 15inch")
                .manufacturer("Dell")
                .batch((short)95)
                .quantity(0)
                .created(date)
                .productStatus(true)
                .build();
    }

    @Test
    void findById() {
        ProductEntity product = productService.findById("A0001");

        /*assertEquals(dataTest(),acc);*/

        assertThat(product)
                .usingRecursiveComparison()
                .isEqualTo(dataTest());
    }
}