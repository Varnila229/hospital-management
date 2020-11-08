package com.enroll.hospitalmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.List;

import com.enroll.hospitalmanagement.entity.EnrollEntity;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class EnrollDto {
  private Long id;
  private String name;
  private boolean activeStatus;
  private Date dob;
  private String phoneNumber;
  List<DependentDto> dependentDtoList;

  public EnrollDto(EnrollEntity enrollEntity) {
    this.id = enrollEntity.getId();
    this.name = enrollEntity.getName();
    this.activeStatus = enrollEntity.isActiveStatus();
    this.dob = enrollEntity.getDob();
    this.phoneNumber = enrollEntity.getPhoneNumber();
  }

  public EnrollEntity toEnrollEntity() {
    EnrollEntity enrollDto = new EnrollEntity(this);
    return enrollDto;
  }
}
