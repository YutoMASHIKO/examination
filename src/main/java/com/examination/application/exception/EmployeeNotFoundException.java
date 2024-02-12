package com.examination.application.exception;

import lombok.Getter;

/**
 * 特定のIDの従業員が見つからなかった際にスローされる例外です.
 */
@Getter
public class EmployeeNotFoundException extends RuntimeException {

  /**
   * 見つからなかった従業員のID.
   */
  private final String id;

  /**
   * 見つからなかった従業員IDを用いてEmployeeNotFoundExceptionを初期化します.
   *
   * @param id 見つからなかった従業員ID
   */
  public EmployeeNotFoundException(String id) {
    super(id);
    this.id = id;
  }
}
