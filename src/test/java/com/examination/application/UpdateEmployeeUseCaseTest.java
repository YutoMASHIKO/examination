package com.examination.application;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import com.examination.application.data.UpdateEmployeeData;
import com.examination.application.exception.EmployeeNotFoundException;
import com.examination.domain.Employee;
import com.examination.domain.EmployeeRepository;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UpdateEmployeeUseCaseTest {
  @InjectMocks
  UpdateEmployeeUseCase sut;

  @Mock
  private EmployeeRepository employeeRepository;

  @BeforeEach
  void setup() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void 従業員の更新を行う場合() {
    when(employeeRepository.getEmployeeById("1"))
      .thenReturn(Optional.of(new Employee("1", "Taro", "Yamada")));

    UpdateEmployeeData updateEmployeeData = new UpdateEmployeeData("1", "Ichiro", "Tanaka");

    sut.update(updateEmployeeData);

    verify(employeeRepository, times(1))
      .updateEmployee(updateEmployeeData.convert(new Employee("1", "Taro", "Yamada")));
  }

  @Test
  void 従業員idが存在せず従業員更新が行えない場合() {
    when(employeeRepository.getEmployeeById("0")).thenReturn(Optional.empty());

    UpdateEmployeeData updateEmployeeData = new UpdateEmployeeData("0", "Ichiro", "Tanaka");

    assertThrows(EmployeeNotFoundException.class, () -> sut.update(updateEmployeeData));
  }
}
