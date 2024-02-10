package com.examination.application;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class GetEmployeeUseCaseTest {
  @InjectMocks
  GetEmployeeUseCase sut;

  @Mock
  EmployeeRepository employeeRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 指定したIDの従業員情報を取得できる() {
    when(employeeRepository.getEmployeeById("1"))
      .thenReturn(new Employee("1", "Taro", "Yamada"));

    Employee expected = new Employee("1", "Taro", "Yamada");

    Employee actual = sut.getEmployeeById("1");

    assertEquals(expected, actual);
  }

}
