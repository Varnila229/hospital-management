package com.enroll.hospitalmanagement.dao;


import java.util.List;
import java.util.Optional;

import com.enroll.hospitalmanagement.entity.DependentEntity;
import com.enroll.hospitalmanagement.entity.EnrollEntity;

public interface EnrollDao {
  List<EnrollEntity> getAll();

  Optional<EnrollEntity> getById(Long id);

  EnrollEntity create(EnrollEntity enrollDto);

  EnrollEntity update(EnrollEntity enrollDto);

  void delete(Long id);

  List<DependentEntity> saveDependents(List<DependentEntity> dependentEntityList);

  List<DependentEntity> getDependents(EnrollEntity enrollEntity);

  void delete(int enrollId, int dependentId);
}
