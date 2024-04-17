package com.ra.web.repository;

import com.ra.web.model.entity.accounts.AccEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccRepository extends JpaRepository<AccEntity,Integer> {
    Optional<AccEntity> findByUserNameAndPassword(String userName, String password);
    Optional<AccEntity> findByUserName(String userName);
}
