package com.examination.application.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.examination.domain.Employee;
import org.junit.jupiter.api.Test;

class UpdateEmployeeDataTest {

  @Test
  void 姓名ともに更新する場合() {
    UpdateEmployeeData sut = new UpdateEmployeeData("1", "Taro", "Tanaka");
    Employee originalEmployee = new Employee("1", "Jiro", "Yamada");

    Employee expected = new Employee("1", "Taro", "Tanaka");

    Employee actual = sut.convert(originalEmployee);

    assertEquals(expected, actual);
  }

  @Test
  void 姓のみ更新する場合() {
    UpdateEmployeeData sut = new UpdateEmployeeData("1", null, "Tanaka");
    Employee originalEmployee = new Employee("1", "Jiro", "Yamada");

    Employee expected = new Employee("1", "Jiro", "Tanaka");

    Employee actual = sut.convert(originalEmployee);

    assertEquals(expected, actual);
  }

  @Test
  void 名のみ更新する場合() {
    UpdateEmployeeData sut = new UpdateEmployeeData("1", "Ichiro", null);
    Employee originalEmployee = new Employee("1", "Jiro", "Yamada");

    Employee expected = new Employee("1", "Ichiro", "Yamada");

    Employee actual = sut.convert(originalEmployee);

    assertEquals(expected, actual);
  }
}
