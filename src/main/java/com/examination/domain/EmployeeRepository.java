package com.examination.domain;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository {
  List<Employee> getAllEmployees();

  Optional<Employee> getEmployeeById(String id);

  Long getNextId();

  Employee createEmployee(Employee employee);

  void updateEmployee(Employee employee);

  void deleteEmployee(String id);
}
