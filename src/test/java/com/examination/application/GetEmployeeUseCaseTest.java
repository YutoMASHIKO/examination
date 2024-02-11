package com.examination.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

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
  void 指定したIDの従業員情報を取得できる場合() {
    when(employeeRepository.getEmployeeById("1"))
      .thenReturn(Optional.of(new Employee("1", "Taro", "Yamada")));

    Employee expected = new Employee("1", "Taro", "Yamada");

    Employee actual = sut.getEmployeeById("1");

    assertEquals(expected, actual);
  }

  @Test
  void 指定したIDの従業員情報が取得できない場合() {
    when(employeeRepository.getEmployeeById("0")).thenReturn(Optional.empty());

    assertThrows(EmployeeNotFoundException.class, () -> sut.getEmployeeById("0"));
  }

}
