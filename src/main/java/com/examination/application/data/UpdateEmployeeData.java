package com.examination.application.data;

import com.examination.domain.Employee;

import static java.util.Objects.nonNull;

public record UpdateEmployeeData(String id, String firstName, String lastName) {
  public Employee convert(Employee employee) {
    String updatedLastName = employee.lastName();
    if (nonNull(lastName)) {
      updatedLastName = lastName;
    }

    String updatedFirstName = employee.firstName();

    return new Employee(id, updatedFirstName, updatedLastName);
  }
}
