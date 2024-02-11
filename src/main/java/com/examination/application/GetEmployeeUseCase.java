package com.examination.application;

import com.examination.application.exception.EmployeeNotFoundException;
import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 従業員IDを指定して従業員情報を取得するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class GetEmployeeUseCase {
  private final EmployeeRepository employeeRepository;

  /**
   * 指定された従業員IDに対応する従業員情報を取得します.
   *
   * @param id 従業員ID
   * @return 従業員情報
   * @throws EmployeeNotFoundException 指定されたIDの従業員が見つからない場合に発生します。
   */
  public Employee getEmployeeById(String id) {
    return employeeRepository.getEmployeeById(id)
      .orElseThrow(() -> new EmployeeNotFoundException(id));
  }
}
