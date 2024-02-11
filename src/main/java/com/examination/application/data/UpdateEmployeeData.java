package com.examination.application.data;

import static java.util.Objects.nonNull;

import com.examination.domain.Employee;

/**
 * 更新したい従業員の情報を保持します.
 *
 * @param id        更新したい従業員のID
 * @param firstName 更新したい従業員の名前
 * @param lastName  更新したい従業員の名字
 */
public record UpdateEmployeeData(String id, String firstName, String lastName) {

  /**
   * 入力された新規情報を用いて、従業員情報を更新します.
   * 更新箇所以外は元の情報を保持します.
   *
   * @param employee 更新したい従業員オブジェクト
   * @return 更新された従業員オブジェクト
   */
  public Employee convert(Employee employee) {
    String updatedLastName = employee.lastName();
    if (nonNull(lastName)) {
      updatedLastName = lastName;
    }

    String updatedFirstName = employee.firstName();
    if (nonNull(firstName)) {
      updatedFirstName = firstName;
    }

    return new Employee(id, updatedFirstName, updatedLastName);
  }
}
