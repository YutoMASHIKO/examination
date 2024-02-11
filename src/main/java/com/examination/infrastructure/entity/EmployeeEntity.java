package com.examination.infrastructure.entity;

import com.examination.domain.Employee;

/**
 * 従業員を表すエンティティです.
 * このエンティティはデータベースからの従業員情報を保持します.
 */
public record EmployeeEntity(String id, String firstName, String lastName) {

  /**
   * 従業員エンティティを従業員モデルへと変化します.
   *
   * @return 変換されたEmployeeオブジェクト
   */
  public Employee convert() {
    return new Employee(id, firstName, lastName);
  }
}
