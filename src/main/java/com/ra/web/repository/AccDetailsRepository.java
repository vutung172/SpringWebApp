package com.ra.web.repository;

import com.ra.web.model.entity.UserDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccDetailsRepository extends JpaRepository<UserDetailEntity,String> {
    Optional<UserDetailEntity> findAllByEmail(String email);
}
