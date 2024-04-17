package com.ra.web.repository;

import com.ra.web.model.entity.EmployeeDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDetailEntity,Integer> {
    EmployeeDetailEntity findEmployeeDetailEntityByEmployeeId(String id);
    Optional<EmployeeDetailEntity> findAllByEmail(String email);
}
