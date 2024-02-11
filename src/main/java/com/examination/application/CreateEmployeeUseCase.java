package com.examination.application;

import com.examination.application.data.InsertEmployeeData;
import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 従業員の新規登録をするユースケースを表現するサービスクラスです.
 */
@Service
@RequiredArgsConstructor
public class CreateEmployeeUseCase {
  private final EmployeeRepository employeeRepository;

  /**
   * 従業員データを基に従業員を新規登録します.
   *
   * @param insertEmployeeData 新規登録する従業員データ
   * @return 作成された新規従業員情報
   */
  public Employee createEmployee(InsertEmployeeData insertEmployeeData) {
    return employeeRepository.createEmployee(
      insertEmployeeData.convert(employeeRepository.getNextId())
    );
  }
}
