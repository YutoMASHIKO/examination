package com.examination.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class EmployeeTest {

  private static final String LONG_NAME = "abcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdeabcdef";

  @ParameterizedTest(name = "{2} の場合")
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

  @Test
  void 従業員の名字がnullの場合() {
    assertThatThrownBy(() -> new Employee("1", "Taro", null))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("従業員の名字がnullです");
  }

  @Test
  void 従業員の名字が100文字以上の場合() {
    assertThatThrownBy(() -> new Employee("1", "Taro", LONG_NAME))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("従業員の名字が100文字を超えています");
  }

  @Test
  void 従業員の名前がnullの場合() {
    assertThatThrownBy(() -> new Employee("1", null, "Yamada"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("従業員の名前がnullです");
  }

  @Test
  void 従業員の名前が100文字以上の場合() {
    assertThatThrownBy(() -> new Employee("1", LONG_NAME, "Yamada"))
        .isInstanceOf(IllegalArgumentException.class)
        .hasMessage("従業員の名前が100文字を超えています");
  }

}
