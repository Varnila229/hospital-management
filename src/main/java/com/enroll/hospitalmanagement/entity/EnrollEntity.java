package com.enroll.hospitalmanagement.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.enroll.hospitalmanagement.dto.EnrollDto;

@Entity(name = "enrollment")
@Data
@ToString
@NoArgsConstructor
public class EnrollEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private boolean activeStatus;
  private Date dob;
  private String phoneNumber;

  public EnrollDto toEnrollDto() {
    EnrollDto enrollDto = new EnrollDto(this);
    return enrollDto;
  }

  public EnrollEntity(EnrollDto enrollDto){
    this.id = enrollDto.getId();
    this.name = enrollDto.getName();
    this.activeStatus = enrollDto.isActiveStatus();
    this.dob = enrollDto.getDob();
    this.phoneNumber = enrollDto.getPhoneNumber();
  }
}
