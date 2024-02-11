package com.examination.application.data;

import com.examination.domain.Employee;

public record UpdateEmployeeData(String id, String firstName, String lastName) {
  public Employee convert(Employee employee) {
    return new Employee(id, firstName, lastName);
  }
}
