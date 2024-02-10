package com.examination.infrastructure.repository;

import com.examination.domain.Employee;
import com.examination.infrastructure.mapper.EmployeeMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class EmployeeRepositoryImplTest {
  @InjectMocks
  EmployeeRepositoryImpl sut;

  @Mock
  EmployeeMapper employeeMapper;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全件取得をする場合() {
    when(employeeMapper.getAllEmployees())
      .thenReturn(
        List.of(
          new Employee("1", "Taro", "Yamada"),
          new Employee("2", "Jiro", "Yamada")
        )
      );

    List<Employee> expected = List.of(
      new Employee("1", "Taro", "Yamada"),
      new Employee("2", "Jiro", "Yamada")
    );

    List<Employee> actual = sut.getAllEmployees();

    assertEquals(expected, actual);
  }

  @Test
  void 個別取得をする場合() {
    when(employeeMapper.getEmployeeById("3"))
      .thenReturn(new Employee("3", "Taro", "Tanaka"));

    Employee expected = new Employee("3", "Taro", "Tanaka");

    Employee actual = sut.getEmployeeById("3");

    assertEquals(expected, actual);
  }
}
