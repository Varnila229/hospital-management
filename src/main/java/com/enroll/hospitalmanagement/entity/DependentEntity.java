package com.enroll.hospitalmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.enroll.hospitalmanagement.dto.DependentDto;

@Data
@NoArgsConstructor
@Entity(name = "dependent")
@AllArgsConstructor
public class DependentEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String name;
  private Date dob;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "enroll")
  private EnrollEntity enrollEntity;

  public DependentDto toDependentDto() {
    DependentDto dependentDto = new DependentDto(this);
    return dependentDto;
  }

  public DependentEntity(DependentDto dependentDto){
    this.id = dependentDto.getId();
    this.name = dependentDto.getName();
    this.dob = dependentDto.getDob();
  }
}
