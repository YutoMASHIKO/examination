package com.examination.domain;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isNumeric;

/**
 * 従業員を表すオブジェクト.
 *
 * @param id        従業員を識別する個別のID
 * @param firstName 従業員の名前
 * @param lastName  従業員の名字
 */
public record Employee(String id, String firstName, String lastName) {

  /**
   * Employeeを初期化します.
   * @param id        従業員ID。1から9999999999の数字であり、nullであってはなりません。
   * @param firstName 従業員の名前。
   * @param lastName  従業員の名字。
   */
  public Employee {
    if (!isNumeric(id)) {
      throw new IllegalArgumentException("従業員IDが数字ではありません");
    }
    if (id.length() > 10 || Integer.parseInt(id) < 1) {
      throw new IllegalArgumentException("従業員IDが1から9999999999の数字ではありません");
    }

    if (isNull(firstName)) {
      throw new IllegalArgumentException("従業員の名前がnullです");
    }
    if (firstName.length() > 100) {
      throw new IllegalArgumentException("従業員の名前が100文字を超えています");
    }

    if (isNull(lastName)) {
      throw new IllegalArgumentException("従業員の名字がnullです");
    }
    if (lastName.length() > 100) {
      throw new IllegalArgumentException("従業員の名字が100文字を超えています");
    }

  }
}
