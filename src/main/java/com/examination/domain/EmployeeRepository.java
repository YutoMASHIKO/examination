package com.examination.domain;

import java.util.List;
import java.util.Optional;

/**
 * 従業員を管理するためのリポジトリインターフェースです.
 */
public interface EmployeeRepository {

  /**
   * すべての従業員を取得します.
   *
   * @return すべての従業員のリスト
   */
  List<Employee> getAllEmployees();

  /**
   * 従業員IDにより指定の従業員を取得します.
   *
   * @param id 従業員のID。
   * @return 取得したOptionalのEmployee。見つからない場合は空のOptionalが返ります。
   */
  Optional<Employee> getEmployeeById(String id);

  /**
   * 次の利用可能な従業員IDを取得します.
   *
   * @return 次の従業員ID。
   */
  Long getNextId();

  /**
   * 従業員の新規登録をします.
   *
   * @param employee 作成する従業員オブジェクト。
   * @return 作成された従業員。
   */
  Employee createEmployee(Employee employee);

  /**
   * 既存の従業員を更新します.
   *
   * @param employee 更新する従業員
   */
  void updateEmployee(Employee employee);

  /**
   * 指定したIDの従業員を削除します.
   *
   * @param id 削除する従業員のID。
   */
  void deleteEmployee(String id);
}
