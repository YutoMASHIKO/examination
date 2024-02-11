package com.examination.infrastructure.entity;

import com.examination.domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeEntityTest {
  @Test
  void 正しくEmployeeオブジェクトに変換できる場合() {
    EmployeeEntity sut = new EmployeeEntity("1", "Taro", "Yamada");

    Employee expected = new Employee("1", "Taro", "Yamada");

    Employee actual = sut.convert();

    assertEquals(expected, actual);
  }
}
