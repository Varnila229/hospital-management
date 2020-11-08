package com.enroll.hospitalmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.enroll.hospitalmanagement.dao.EnrollDao;
import com.enroll.hospitalmanagement.dto.EnrollDto;
import com.enroll.hospitalmanagement.entity.DependentEntity;
import com.enroll.hospitalmanagement.entity.EnrollEntity;
import com.enroll.hospitalmanagement.error.EnrollNotFoundException;
import com.enroll.hospitalmanagement.error.NoDataFoundException;
import com.enroll.hospitalmanagement.service.EnrollService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EnrollServiceImpl implements EnrollService {

  @Autowired
  EnrollDao enrollDao;

  @Override
  public List<EnrollDto> getAll() {
    List<EnrollDto> enrollDtos = new ArrayList<>();
    List<EnrollEntity> enrollEntities = enrollDao.getAll();
    if (enrollEntities != null) {
      enrollDtos = enrollEntities.stream().map(e -> {EnrollDto enrollDto = e.toEnrollDto();
                              getDependentsByEnroleId(enrollDto);
                              return enrollDto;}).collect(Collectors.toList());
    } else {
      throw new NoDataFoundException("No enroll found");
    }
    return enrollDtos;
  }

  @Override
  public EnrollDto getById(int id) {
    EnrollDto enrollDto = null;
    try {
      Long id1 = Long.valueOf(id);
      Optional<EnrollEntity> optionalEnrolEntity = enrollDao.getById(id1);
      if (optionalEnrolEntity.isPresent()) {
        enrollDto = optionalEnrolEntity.get().toEnrollDto();
        getDependentsByEnroleId(enrollDto);
      } else {
        throw new EnrollNotFoundException(String.format("Enroll with Id %d not found", id));
      }
    } catch (Exception e) {
      e.printStackTrace();
      throw new EnrollNotFoundException(String.format("Enroll with Id %d not found", id));
    }
    return enrollDto;
  }

  @Override
  public EnrollDto create(EnrollDto enrollDto) {
    EnrollDto enrollDtoResult = null;
    try {
      EnrollEntity optionalEnrollEntity = enrollDao.create(enrollDto.toEnrollEntity());
      if (hasDependents(enrollDto)) {
        List<DependentEntity> dependentEntityList =
            enrollDto.getDependentDtoList().stream().map(d -> {
              DependentEntity de = d.toDependentEntity();
              de.setEnrollEntity(optionalEnrollEntity);
              return de;}).collect(Collectors.toList());
        List<DependentEntity> dependentEntityListResult = enrollDao.saveDependents(dependentEntityList);
        enrollDtoResult = optionalEnrollEntity.toEnrollDto();
        if(dependentEntityListResult!=null && !dependentEntityListResult.isEmpty()){
          enrollDtoResult.setDependentDtoList(dependentEntityListResult.stream().map(d -> d.toDependentDto()).collect(
              Collectors.toList()));
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return enrollDtoResult;
  }

  @Override
  public EnrollDto update(EnrollDto enrollDto) {
    EnrollDto enrollDtoResult = null;
    try {
      EnrollEntity optionalEnrollEntity = enrollDao.create(enrollDto.toEnrollEntity());
      if (hasDependents(enrollDto)) {
        List<DependentEntity> dependentEntityList =
            enrollDto.getDependentDtoList().stream().map(d -> {
              DependentEntity de = d.toDependentEntity();
              de.setEnrollEntity(optionalEnrollEntity);
              return de;}).collect(Collectors.toList());
        List<DependentEntity> dependentEntityListResult = enrollDao.saveDependents(dependentEntityList);
        enrollDtoResult = optionalEnrollEntity.toEnrollDto();
        getDependentsByEnroleId(enrollDtoResult);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return enrollDtoResult;
  }

  @Override
  public void delete(int id) {
    try {
      enrollDao.delete(Long.valueOf(id));
    } catch (Exception e) {
      throw new EnrollNotFoundException(String.format("Enroll with Id %d not found", id));
    }
  }

  @Override
  public void delete(int enrollId, int dependentId) {
    enrollDao.delete(enrollId, dependentId);
  }

  private boolean hasDependents(EnrollDto enrollDto) {
    if (enrollDto != null && enrollDto.getDependentDtoList() != null && enrollDto.getDependentDtoList().size() > 0) {
      return true;
    }
    return false;
  }

  public void getDependentsByEnroleId(EnrollDto enrollDto){
    List<DependentEntity> dependentEntityList = enrollDao.getDependents(enrollDto.toEnrollEntity());
    if(dependentEntityList!=null && !dependentEntityList.isEmpty()){
      enrollDto.setDependentDtoList(dependentEntityList.stream().map(d -> d.toDependentDto()).collect(Collectors.toList()));
    }
  }
}
