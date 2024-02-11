package com.examination.application;

import com.examination.application.exception.EmployeeNotFoundException;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteEmployeeUseCase {
  private final EmployeeRepository employeeRepository;

  public void deleteEmployee(String id) {
    if (employeeRepository.getEmployeeById(id).isEmpty()) {
      throw new EmployeeNotFoundException(id);
    }
    employeeRepository.deleteEmployee(id);
  }
}
