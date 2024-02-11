package com.examination.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.examination.application.exception.EmployeeNotFoundException;
import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class DeleteEmployeeUseCaseTest {
  @InjectMocks
  DeleteEmployeeUseCase sut;

  @Mock
  private EmployeeRepository employeeRepository;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 社員を削除する場合() {
    when(employeeRepository.getEmployeeById("1"))
      .thenReturn(Optional.of(new Employee("1", "Taro", "Yamada")));

    sut.deleteEmployee("1");

    verify(employeeRepository, times(1)).deleteEmployee("1");
  }

  @Test
  void 削除したいidの社員が存在しない場合() {
    assertThrows(EmployeeNotFoundException.class, () -> sut.deleteEmployee("0"));
  }
}
