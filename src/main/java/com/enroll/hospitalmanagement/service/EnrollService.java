package com.enroll.hospitalmanagement.service;


import org.springframework.stereotype.Service;

import com.enroll.hospitalmanagement.dto.EnrollDto;

import java.util.List;

@Service
public interface EnrollService {
  List<EnrollDto> getAll();

  EnrollDto getById(int id);

  EnrollDto create(EnrollDto enrollDto);

  EnrollDto update(EnrollDto enrollDto);

  void delete(int id);

  void delete(int enrollId, int dependentId);
}
