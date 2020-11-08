package com.enroll.hospitalmanagement.error;

public class EnrollNotFoundException extends RuntimeException {

  public EnrollNotFoundException(Long id) {
    super(String.format("Enroll with Id %d not found", id));
  }

  public EnrollNotFoundException(String id) {
    super(id);
  }
}