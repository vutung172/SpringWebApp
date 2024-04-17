package com.ra.web.repository;

import com.ra.web.model.entity.BillDetailsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BillDetailRepository extends JpaRepository<BillDetailsEntity, Integer> {

}
