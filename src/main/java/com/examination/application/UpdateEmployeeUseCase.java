package com.examination.application;

import com.examination.application.data.UpdateEmployeeData;
import com.examination.application.exception.EmployeeNotFoundException;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 従業員情報を更新するユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class UpdateEmployeeUseCase {

  private final EmployeeRepository employeeRepository;

  /**
   * 従業員情報を更新します.
   *
   * @param updateEmployeeData 更新対象の従業員情報
   * @throws EmployeeNotFoundException 指定されたIDの従業員が見つからない場合に発生します。
   */
  @Transactional
  public void update(UpdateEmployeeData updateEmployeeData) {
    employeeRepository.updateEmployee(
        updateEmployeeData.convert(
            employeeRepository.getEmployeeById(updateEmployeeData.id())
                .orElseThrow(() -> new EmployeeNotFoundException(updateEmployeeData.id()))
        )
    );
  }
}
