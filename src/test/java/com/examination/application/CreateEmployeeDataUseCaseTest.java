package com.examination.application;

import static org.mockito.Mockito.when;

import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;

class CreateEmployeeDataUseCaseTest {
  @InjectMocks
  CreateEmployeeUseCase sut;

  @Mock
  EmployeeRepository employeeRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員の新規登録をする場合() {
    when(employeeRepository.getNextId()).thenReturn(3L);

    when(employeeRepository.createEmployee(new Employee("3", "Hanako", "Shirato")))
      .thenReturn(new Employee("3", "Hanako", "Shirato"));

    Employee expected = new Employee("3", "Hanako", "Shirato");

    Employee actual = sut.createEmployee(new InsertEmployeeData("Hanako", "Shirato"));

    assertEquals(expected, actual);
  }
}
