package com.examination.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class GetAllEmployeesUseCaseTest {
  @InjectMocks
  GetAllEmployeesUseCase sut;

  @Mock
  EmployeeRepository employeeRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 全従業員の情報を取得できる() {
    when(employeeRepository.getAllEmployees())
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

    List<Employee> actual = sut.findAllEmployee();

    assertEquals(expected, actual);
  }
}
