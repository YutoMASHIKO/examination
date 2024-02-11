package com.examination.application;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * すべての従業員を取得するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class GetAllEmployeesUseCase {
  private final EmployeeRepository employeeRepository;

  /**
   * すべての従業員を取得します.
   *
   * @return 従業員のリスト
   */
  public List<Employee> findAllEmployee() {
    return employeeRepository.getAllEmployees();
  }
}
