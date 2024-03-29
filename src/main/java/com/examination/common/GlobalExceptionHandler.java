package com.examination.common;

import static java.util.Collections.emptyList;

import com.examination.application.exception.EmployeeNotFoundException;
import com.examination.presentation.response.ErrorResponse;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
   * @param e idによる検索に失敗した場合の例外
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

  /**
   * リクエストの入力に問題があった場合の例外ハンドリングメソッドです.
   *
   * @param e リクエストの入力に問題があった場合に発生する例外
   * @return バリデーションエラーに関する詳細情報を含むエラーレスポンス
   */
  @ExceptionHandler
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
    List<String> details = new ArrayList<>();

    for (FieldError error : e.getFieldErrors()) {
      details.add(String.format("%s %s", error.getField(), error.getDefaultMessage()));
    }
    return new ErrorResponse(
        "0002",
        "request validation error is occurred.",
        details
    );
  }
}
