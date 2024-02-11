package com.examination.common;

import static java.util.Collections.emptyList;

import com.examination.application.EmployeeNotFoundException;
import com.examination.presentation.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * グローバルな例外ハンドリングを行うクラスです.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
  /**
   * idによる検索に失敗した場合の例外ハンドリングメソッドです.
   *
   * @param e {@link EmployeeNotFoundException} インスタンス
   * @return エラーレスポンス
   */
  @ExceptionHandler(EmployeeNotFoundException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleEmployeeNotFoundException(EmployeeNotFoundException e) {
    return new ErrorResponse(
      "0003",
      String.format("specified employee [id = %s] is not found.", e.getId()),
      emptyList()
      );
  }
}
