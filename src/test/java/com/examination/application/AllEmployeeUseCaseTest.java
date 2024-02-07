package com.examination.application;

import com.examination.domain.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AllEmployeeUseCaseTest {
  @Autowired
  AllEmployeeUseCase sut;

  @Test
  void 全従業員の情報を取得できる() {
    List<Employee> actual = sut.findAllEmployee();

    List<Employee> expected = List.of(
      new Employee("1", "Taro", "Yamada"),
      new Employee("2", "Jiro", "Yamada")
      );

    assertEquals(expected, actual);
  }
}
