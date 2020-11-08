package com.enroll.hospitalmanagement.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.enroll.hospitalmanagement.dao.EnrollDao;
import com.enroll.hospitalmanagement.entity.DependentEntity;
import com.enroll.hospitalmanagement.entity.EnrollEntity;
import com.enroll.hospitalmanagement.repository.DependentRepository;
import com.enroll.hospitalmanagement.repository.EnrollRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Component
public class EnrollDaoImpl implements EnrollDao {

  @Autowired
  EnrollRepository enrollRepository;

  @Autowired
  DependentRepository dependentRepository;

  @PersistenceContext
  private EntityManager manager;

  @Override
  public List<EnrollEntity> getAll() {
    return (List<EnrollEntity>) enrollRepository.findAll();
  }

  @Override
  public Optional<EnrollEntity> getById(Long id) {
    return enrollRepository.findById(id);
  }

  @Override
  public EnrollEntity create(EnrollEntity enrollEntity) {
    return enrollRepository.save(enrollEntity);
  }

  @Override
  public EnrollEntity update(EnrollEntity enrollEntity) {
    return enrollRepository.save(enrollEntity);
  }

  @Override
  @Transactional
  public void delete(Long id) {
    Query query= manager.createNativeQuery("delete from dependent a where a.enroll= ?");
    query.setParameter(1, id);
    query.executeUpdate();
    enrollRepository.deleteById(id);
  }

  @Override
  public List<DependentEntity> saveDependents(List<DependentEntity> dependentEntityList) {
    return (List<DependentEntity>) dependentRepository.saveAll(dependentEntityList);
  }

  @Override
  public List<DependentEntity> getDependents(EnrollEntity enrollEntity) {
    List<DependentEntity> dependentEntityList = new ArrayList<>();
    Query query= manager.createNativeQuery("select * from dependent a where a.enroll= ?", DependentEntity.class);
    query.setParameter(1, enrollEntity.getId());
    List<DependentEntity> result = query.getResultList();
    dependentEntityList = result;
    return dependentEntityList;
  }

  @Override
  @Transactional
  public void delete(int enrollId, int dependentId) {
    Query query= manager.createNativeQuery("delete from dependent a where a.enroll= ? and id=? ");
    query.setParameter(1, enrollId);
    query.setParameter(2, enrollId);
    query.executeUpdate();
  }
}
