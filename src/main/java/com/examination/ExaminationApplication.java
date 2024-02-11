package com.examination;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot アプリケーションの起動クラスです.
 * アプリケーションのエントリーポイントとなります.
 */
@SpringBootApplication
public class ExaminationApplication {
  /**
   * アプリケーションを起動します.
   *
   * @param args 起動時のコマンドライン引数
   */
  public static void main(String[] args) {
    SpringApplication.run(ExaminationApplication.class, args);
  }
}
