package com.examination.presentation.response;

import com.examination.domain.Employee;

import java.util.List;

public record AllEmployeeResponse(List<EmployeeResponse> employees) {
  public static AllEmployeeResponse createResponse(List<Employee> employeeList) {
    return new AllEmployeeResponse(employeeList.stream().map(EmployeeResponse::createResponse).toList());
  }
}
