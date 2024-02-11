package com.examination.application;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetEmployeeUseCase {
  private final EmployeeRepository employeeRepository;

  public Employee getEmployeeById(String id) {
    return employeeRepository.getEmployeeById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }
}
