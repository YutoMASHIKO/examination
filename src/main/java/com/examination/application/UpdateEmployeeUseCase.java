package com.examination.application;

import com.examination.application.data.UpdateEmployeeData;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCase {
  private final EmployeeRepository employeeRepository;

  public void update(UpdateEmployeeData updateEmployeeData) {
    employeeRepository.updateEmployee(
        updateEmployeeData.convert(
          employeeRepository.getEmployeeById(updateEmployeeData.id())
            .orElseThrow(() -> new EmployeeNotFoundException(updateEmployeeData.id()))
      )
    );
  }
}
