package com.examination.application;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllEmployeesUseCase {
  private final EmployeeRepository employeeRepository;

  public List<Employee> findAllEmployee() {
    return employeeRepository.getAllEmployees();
  }
}
