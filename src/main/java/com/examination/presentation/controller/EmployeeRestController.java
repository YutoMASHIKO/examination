package com.examination.presentation.controller;

import com.examination.domain.Employee;
import com.examination.presentation.response.EmployeeResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 従業員情報を管理するためのコントローラー.
 */
@RestController
public class EmployeeRestController {

  /**
   * すべての従業員を取得します.
   *
   * @return <{@link EmployeeResponse}>.
   */
  @GetMapping("v1/employees")
  @ResponseBody
  public List<EmployeeResponse> getAllEmployees() {
    return List.of(
      EmployeeResponse.createResponse(new Employee("1", "Taro", "Yamada")),
      EmployeeResponse.createResponse(new Employee("2", "Jiro", "Yamada"))
    );
  }

  @GetMapping("v1/employees/{id}")
  public EmployeeResponse getEmployeeById(@PathVariable String id) {
    List<EmployeeResponse> employeeResponseList = List.of(
        EmployeeResponse.createResponse(new Employee("1", "Taro", "Yamada")),
        EmployeeResponse.createResponse(new Employee("2", "Jiro", "Yamada"))
    );

    return employeeResponseList.stream()
      .filter(response -> id.equals(response.id()))
      .findFirst()
      .orElse(null);
  }
}
