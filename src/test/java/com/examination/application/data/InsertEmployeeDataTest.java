package com.examination.application.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.examination.domain.Employee;
import org.junit.jupiter.api.Test;

class InsertEmployeeDataTest {

  @Test
  void Employeeオブジェクトに変換できる() {
    InsertEmployeeData sut = new InsertEmployeeData("Hanako", "Shirato");

    Employee expected = new Employee("3", "Hanako", "Shirato");

    Employee actual = sut.convert(3L);

    assertEquals(expected, actual);
  }

}
