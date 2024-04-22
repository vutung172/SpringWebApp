package com.ra.web.service.Impl;

import com.ra.web.model.entity.accounts.AccEntity;
import com.ra.web.model.entity.accounts.AccRoleEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AccServiceImplTest {

    @Autowired
    private AccServiceImpl accService;
    private AccEntity dataTest(){
        AccRoleEntity accRole = new AccRoleEntity();
        accRole.setAccId(1);
        accRole.setRoleId(1);
        List<AccRoleEntity> list = new ArrayList<>();
        list.add(accRole);
        return AccEntity.builder()
                .accId(1)
                .userName("tungvu")
                .password("12345")
                .permission(true)
                .accStatus(true)
                .email(null)
                .employeeDetail(null)
                .userRoleEntities(list)
                .build();
    }
    @Test
    void findAll() {
        List<AccEntity> accEntityList = accService.findAll();
        int result = accEntityList.size();
        int expect = 7;

        assertEquals(expect, result);

    }

    @Test
    void findByUserName() {
        AccEntity acc = accService.findByUserName("tungvu");
        AccEntity entityExpect = dataTest();

        /*assertEquals(dataTest(),acc);*/

        assertThat(acc)
                .usingRecursiveComparison()
                .isEqualTo(entityExpect);
    }

}