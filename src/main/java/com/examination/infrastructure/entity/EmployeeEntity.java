package com.examination.infrastructure.entity;

import com.examination.domain.Employee;

public record EmployeeEntity(String id, String firstName, String lastName) {
  public Employee convert() {
    return new Employee(id, firstName, lastName);
  }
}
