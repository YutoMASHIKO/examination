package com.examination.presentation.response;

import com.examination.domain.Employee;

/**
 * 従業員を表すレスポンス用のオブジェクト.
 *
 * @param id        従業員のID
 * @param firstName 従業員の名前
 * @param lastName  従業員の名字
 */
public record EmployeeResponse(String id, String firstName, String lastName) {
  /**
   * EmployeeResponseを作成します.
   *
   * @param employee 従業員オブジェクト
   * @return 作成されたEmployeeResponse
   */
  public static EmployeeResponse createResponse(Employee employee) {
    return new EmployeeResponse(employee.id(), employee.firstName(), employee.lastName());
  }
}
