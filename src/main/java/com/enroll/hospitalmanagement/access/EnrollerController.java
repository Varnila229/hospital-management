package com.enroll.hospitalmanagement.access;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.enroll.hospitalmanagement.dto.EnrollDependent;
import com.enroll.hospitalmanagement.dto.EnrollDto;
import com.enroll.hospitalmanagement.service.EnrollService;

import java.util.List;

@RestController
@RequestMapping("/enroll")
public class EnrollerController {

  @Autowired
  EnrollService enrollService;

  @PostMapping
  public EnrollDto createEnrol(@RequestBody EnrollDto enrollDto) {
    return enrollService.create(enrollDto);
  }

  @GetMapping("/{id}")
  public EnrollDto getByIdEnroll(@PathVariable("id") int id) {
    return enrollService.getById(id);
  }

  @GetMapping
  public List<EnrollDto> getAllEnroll() {
    return enrollService.getAll();
  }

  @DeleteMapping("/{id}")
  public void deleteEnroll(@PathVariable("id") int id) {
	  enrollService.delete(id);
  }

  @PutMapping
  public EnrollDto updateEnroll(@RequestBody EnrollDto enrollDto) {
    return enrollService.update(enrollDto);
  }

  @DeleteMapping
  public void deleteDependent(@RequestBody EnrollDependent enrollDependent) {
	  enrollService.delete(enrollDependent.getEnrollId(), enrollDependent.getDependentId());
  }


}
