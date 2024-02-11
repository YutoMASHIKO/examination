package com.examination.presentation.response;

import com.examination.domain.Employee;
import java.util.List;

/**
 * 全ての従業員情報を含むレスポンスを表すレコードクラスです.
 *
 * @param employees 従業員のレスポンス用オブジェクトであるEmployeeResponseのリスト
 */
public record AllEmployeeResponse(List<EmployeeResponse> employees) {

  /**
   * 従業員オブジェクトのリストからAllEmployeeResponseを生成します.
   *
   * @param employeeList 従業員オブジェクトのリスト
   * @return 生成されたAllEmployeeResponse
   */
  public static AllEmployeeResponse createResponse(List<Employee> employeeList) {
    return new AllEmployeeResponse(
      employeeList.stream().map(EmployeeResponse::createResponse).toList()
    );
  }
}
