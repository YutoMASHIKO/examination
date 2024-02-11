package com.examination.infrastructure.exception;

/**
 * この例外は、SQLの実行に失敗した場合にスローされます.
 */
public class SqlExecutionException extends RuntimeException {
  public SqlExecutionException(String message) {
    super(message);
  }
}
