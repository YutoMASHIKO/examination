package com.examination.presentation.response;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.examination.domain.Employee;
import java.util.List;
import org.junit.jupiter.api.Test;

class AllEmployeeResponseTest {

  @Test
  void 全件取得用の従業員レスポンスが生成できる() {
    List<Employee> employeeList = List.of(
        new Employee("1", "Taro", "Yamada"),
        new Employee("2", "Jiro", "Yamada")
    );

    AllEmployeeResponse expected = new AllEmployeeResponse(
        List.of(
            new EmployeeResponse("1", "Taro", "Yamada"),
            new EmployeeResponse("2", "Jiro", "Yamada")
        )
    );

    AllEmployeeResponse actual = AllEmployeeResponse.createResponse(employeeList);

    assertEquals(expected, actual);
  }

}
