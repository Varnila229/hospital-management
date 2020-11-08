package com.enroll.hospitalmanagement.repository;

import org.springframework.stereotype.Repository;

import com.enroll.hospitalmanagement.entity.DependentEntity;

import org.springframework.data.repository.CrudRepository;

@Repository
public interface DependentRepository extends CrudRepository<DependentEntity, Long> {

}
