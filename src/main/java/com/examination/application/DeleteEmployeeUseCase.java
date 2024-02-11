package com.examination.application;

import com.examination.application.exception.EmployeeNotFoundException;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 従業員を削除するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class DeleteEmployeeUseCase {
  private final EmployeeRepository employeeRepository;

  /**
   * 指定された従業員IDに対応する従業員を削除します.
   *
   * @param id 削除対象の従業員ID
   * @throws EmployeeNotFoundException 指定されたIDの従業員が見つからない場合にスローされる例外
   */
  public void deleteEmployee(String id) {
    if (employeeRepository.getEmployeeById(id).isEmpty()) {
      throw new EmployeeNotFoundException(id);
    }
    employeeRepository.deleteEmployee(id);
  }
}
