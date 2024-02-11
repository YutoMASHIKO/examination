package com.examination.application.data;

import com.examination.domain.Employee;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UpdateEmployeeDataTest {

  @Test
  void 姓名ともに更新する場合() {
    UpdateEmployeeData sut = new UpdateEmployeeData("1", "Taro", "Tanaka");
    Employee originalEmployee = new Employee("1", "Jiro", "Yamada");

    Employee expected = new Employee("1", "Taro", "Tanaka");

    Employee actual = sut.convert(originalEmployee);

    assertEquals(expected, actual);
  }
}
