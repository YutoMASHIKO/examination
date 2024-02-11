package com.examination.domain;

import java.util.List;

public interface EmployeeRepository {
  List<Employee> getAllEmployees();

  Employee getEmployeeById(String id);

  Long getNextId();

  Employee createEmployee(Employee employee);
}
