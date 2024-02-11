package com.examination.application.data;

import com.examination.domain.Employee;

/**
 * 従業員を新規登録するためのデータを保持します.
 *
 * @param firstName 追加したい従業員の名前
 * @param lastName  追加したい従業員の名字
 */
public record InsertEmployeeData(String firstName, String lastName) {

  /**
   * データを指定のIDを用いてEmployeeオブジェクトに変換します.
   *
   * @param id 新規登録する従業員のID
   * @return Employeeオブジェクト
   */
  public Employee convert(Long id) {
    return new Employee(String.valueOf(id), firstName, lastName);
  }
}
