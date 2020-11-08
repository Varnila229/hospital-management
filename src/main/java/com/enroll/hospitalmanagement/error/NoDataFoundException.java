package com.enroll.hospitalmanagement.error;

public class NoDataFoundException extends RuntimeException {

  public NoDataFoundException() {
    super("No data found");
  }

  public NoDataFoundException(String err) {
    super(err);
  }
}