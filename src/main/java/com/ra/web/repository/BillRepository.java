package com.ra.web.repository;

import com.ra.web.model.entity.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<BillEntity, Integer> {

}
