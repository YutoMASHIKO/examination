package com.examination.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class EmployeeTest {
  @ParameterizedTest(name = "{3}場合")
  @CsvSource(delimiter = '|', textBlock = """
            #         ID |                 MESSAGE                 | TESTNAME
                         |                 従業員IDが数字ではありません |従業員IDがnull
                      '' |                 従業員IDが数字ではありません |従業員IDが空文字
                       a |                 従業員IDが数字ではありません |従業員IDが文字
                       0 | 従業員IDが1から9999999999の数字ではありません |従業員IDが1未満の数
             10000000000 | 従業員IDが1から9999999999の数字ではありません |従業員IDが10000000000以上の数
            """)
  void 従業員IDがガード条件に違反する場合(String id, String message, String testName) {
    assertThatThrownBy(() -> new Employee(id, "Taro", "Yamada"))
      .isInstanceOf(IllegalArgumentException.class)
      .hasMessage(message);
  }

}
