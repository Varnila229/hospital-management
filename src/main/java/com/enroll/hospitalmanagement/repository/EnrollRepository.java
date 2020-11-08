package com.enroll.hospitalmanagement.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.enroll.hospitalmanagement.entity.EnrollEntity;

@Repository
public interface EnrollRepository extends CrudRepository<EnrollEntity, Long> {
}
