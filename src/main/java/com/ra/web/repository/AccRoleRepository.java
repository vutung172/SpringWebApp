package com.ra.web.repository;

import com.ra.web.model.entity.AccRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccRoleRepository extends JpaRepository<AccRoleEntity,Integer> {
    List<AccRoleEntity> findAllByAccId(int accId);
}
