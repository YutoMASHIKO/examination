package com.examination;

import static org.assertj.core.api.Assertions.assertThat;

import com.examination.presentation.controller.EmployeeRestController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ExaminationApplicationTests {

  @Autowired
  private EmployeeRestController employeeRestController;

  @Test
  void contextLoads() {
    assertThat(employeeRestController).isNotNull();
  }

}
