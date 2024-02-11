package com.examination.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.examination.application.data.UpdateEmployeeData;
import com.examination.domain.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

class UpdateEmployeeUseCaseTest {
  @InjectMocks
  UpdateEmployeeUseCase sut;

  @Mock
  private EmployeeRepository employeeRepository;

  @Test
  void 従業員の更新を行う場合() {
    UpdateEmployeeData updateEmployeeData = new UpdateEmployeeData("1", "Taro", "Yamada");

//    sut.update(updateEmployeeData);

//    verify(employeeRepository, times(1)).updateEmployee(null);
  }
}
