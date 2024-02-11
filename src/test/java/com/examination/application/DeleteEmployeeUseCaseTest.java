package com.examination.application;

import com.examination.domain.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

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
    sut.deleteEmployee("1");

    verify(employeeRepository, times(1)).deleteEmployee("1");
  }
}
