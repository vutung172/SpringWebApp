package com.ra.web.repository;

import com.ra.web.model.entity.AccDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccDetailsRepository extends JpaRepository<AccDetailEntity,String> {
    Optional<AccDetailEntity> findAllByEmail(String email);
}
