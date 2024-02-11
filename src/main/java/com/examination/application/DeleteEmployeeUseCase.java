package com.examination.application;

import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteEmployeeUseCase {
  private final EmployeeRepository employeeRepository;
}
