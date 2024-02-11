package com.examination.application;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCase {
  private final EmployeeRepository employeeRepository;

  public Employee createEmployee(InsertEmployeeData employee) {
    return employeeRepository.createEmployee(
      new Employee(
        employeeRepository.getNextId().toString(),
        employee.firstName(),
        employee.lastName()
      )
    );
  }

}
